package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;
import com.example.pos_android.R;
import com.example.pos_android.adapter.AddFoodAdapter;
import com.example.pos_android.adapter.CategoryListingAdapter;
import com.example.pos_android.contracts.UserHomeContract;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.model.TableInfoModel;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.food.CategoryDetailResponse;
import com.example.pos_android.data.model.food.foodCategoryResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.data.room.CartDatabase;
import com.example.pos_android.data.room.entity.Cart;
import com.example.pos_android.databinding.FragmentFoodListBinding;
import com.example.pos_android.presenter.UserHomePresenter;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.utils.onCategoryItemClick;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FoodListFragment extends BaseFragment implements UserHomeContract.View, OnItemClickListener, onCategoryItemClick {
    AddFoodAdapter popularAdapter;
    AddFoodAdapter recentAdapter;
    AddFoodAdapter categoryFoodAdapter;
    CategoryListingAdapter categoryListingAdapter;
    UserHomePresenter presenter;
    ArrayList<FoodModel> popularArrayList = new ArrayList<>();
    ArrayList<FoodModel> recommendedList = new ArrayList<>();
    ArrayList<FoodModel> categoryList = new ArrayList<>();
    ArrayList<String> categories = new ArrayList<>();
    private FragmentFoodListBinding binding;
    private SessionManager sessionManager;
    private CartDatabase db;
    private TableInfoModel tableInfoModel;
    private AlanButton alanButton;
    private AlanCallback alanCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFoodListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        categoryShimmer();
        categoryItemShimmer();

        tableInfoModel = FoodListFragmentArgs.fromBundle(getArguments()).getTableInfo();
        sessionManager = new SessionManager(requireContext());
        String json = TableInfoModel.convertToJson(tableInfoModel);
        sessionManager.setIsFoodAdded(json);
        db = CartDatabase.getAppDatabase(requireContext());
        presenter = new UserHomePresenter(FoodListFragment.this, requireContext());
        presenter.getHomeDetails();
        presenter.getCategory();

        binding.btnContinue.setOnClickListener(v -> {
//            if (sessionManager.getIsFoodAdded()) {
            FoodListFragmentDirections.ActionFoodListFragmentToSummaryFragment directions =
                    FoodListFragmentDirections.actionFoodListFragmentToSummaryFragment(tableInfoModel);
            Navigation.findNavController(requireView()).navigate(directions);
/*            } else
                showSnackBar(binding.getRoot(), "Please add food first");*/
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).popBackStack();
            }
        });

        alanButton = getActivity().findViewById(R.id.alan_button);

        alanCallback = new AlanCallback() {
            /// Handle commands from Alan Studio
            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
                    JSONObject data = command.getJSONObject("data");
                    String commandName = data.getString("commandName");
                    //based on commandName we can perform different tasks
                    executeCommand(commandName, data);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                    showToast(requireContext(), e.getMessage());
                }
            }
        };
        alanButton.registerCallback(alanCallback);
    }

    private void categoryShimmer() {
        binding.titleCategory.setVisibility(View.VISIBLE);
        binding.layoutCategoryShimmer.startShimmer();
        binding.layoutCategoryShimmer.setVisibility(View.VISIBLE);
        binding.rvCategories.setVisibility(View.GONE);
    }

    private void categoryHideShimmer() {
        binding.layoutCategoryShimmer.stopShimmer();
        binding.layoutCategoryShimmer.setVisibility(View.GONE);
        binding.rvCategories.setVisibility(View.VISIBLE);
    }


    private void categoryItemShimmer() {
        binding.layoutCategoryItemShimmer.startShimmer();
        binding.layoutCategoryItemShimmer.setVisibility(View.VISIBLE);
        binding.rvCategoriesItems.setVisibility(View.GONE);
    }

    private void categoryItemHideShimmer() {
        binding.layoutCategoryItemShimmer.stopShimmer();
        binding.layoutCategoryItemShimmer.setVisibility(View.GONE);
        binding.rvCategoriesItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        showLoadingDialog(requireContext());
    }

    @Override
    public void hideProgressBar() {
        hideLoadingDialog();
    }

    @Override
    public void showApiErrorWarning(String string) {
        hideLoadingDialog();
        if (string.equalsIgnoreCase("HTTP 401 ")) {
            SessionManager sessionManager = new SessionManager(requireContext());
            sessionManager.clear();
            showToast(requireContext(), "Session expired");
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finishAffinity();
        } else
            showToast(requireContext(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        hideLoadingDialog();
        showToast(requireContext(), message);
    }


    @Override
    public void showUserResponse(UserHomeResponse response) {
        // showToast(requireContext(), response.getMessage());
        popularArrayList.clear();
        recommendedList.clear();
        for (UserHomeResponse.PopularFood food : response.getData().getPopularFoods()) {
            popularArrayList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice(), food.getIncrediance()));
            recommendedList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice(), food.getIncrediance()));
        }
        recentAdapter = new AddFoodAdapter(recommendedList, requireContext(), this);
        binding.rvRecommended.setAdapter(recentAdapter);
        binding.rvRecommended.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false));

        popularAdapter = new AddFoodAdapter(popularArrayList, requireContext(), this);
        binding.rvPopular.setAdapter(popularAdapter);
        binding.rvPopular.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false));

    }

    @Override
    public void showCategoryResponse(foodCategoryResponse foodCategoryResponse) {
        categoryHideShimmer();

        categories.clear();
        categories.addAll(foodCategoryResponse.data.foodCategory);
        binding.titleCategory.setVisibility(View.VISIBLE);
        binding.rvCategories.setVisibility(View.VISIBLE);
        categoryListingAdapter = new CategoryListingAdapter(foodCategoryResponse.data.foodCategory, requireContext(), this, -1);
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        binding.rvCategories.setAdapter(categoryListingAdapter);
    }

    @Override
    public void showCategoryItemsResponse(CategoryDetailResponse categoryDetailResponse) {
        categoryItemHideShimmer();
        categoryList.clear();
        for (UserHomeResponse.PopularFood food : categoryDetailResponse.getData()) {
            categoryList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice(), food.getIncrediance()));
        }
        categoryFoodAdapter = new AddFoodAdapter(categoryList, requireContext(), "category", this);
        binding.rvCategoriesItems.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvCategoriesItems.setAdapter(categoryFoodAdapter);
    }

    @Override
    public void onApiError() {
        categoryItemHideShimmer();
    }

    @Override
    public void onItemClick(Integer position, String from, Boolean isView) {

        sessionManager.setIsCouponSelected(false);
        try {
            FoodModel model;
            if (isView) {
                if (Objects.equals(from, "category"))
                    model = categoryList.get(position);
                else
                    model = popularArrayList.get(position);

                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                Bundle args = new Bundle();
                args.putSerializable("data",  model);
                bottomSheet.setArguments(args);
                bottomSheet.show(requireActivity().getSupportFragmentManager(),
                        "ModalBottomSheet");
            } else {
                if (Objects.equals(from, "category"))
                    model = categoryList.get(position);
                else
                    model = popularArrayList.get(position);

                Cart cart = new Cart();
                cart.food = model;
                cart.food.setFoodId(model.getFoodId());
                cart.userId = sessionManager.getUserName();
                cart.status = 0;
                cart.quantity = 1;
                if (!db.orderDao().isFoodIsExist(sessionManager.getUserName(), model)) {
                    db.orderDao().insert(cart);
                    showSnackBar(binding.getRoot(), "Added to cart successfully");
                    // sessionManager.setIsFoodAdded(true);
                } else {
                    showSnackBar(binding.getRoot(), "Food already in the cart");
                }
            }
        } catch (Exception e) {
            Log.d("Food Exception", "onItemClick: " + e.toString());
        }
    }

    @Override
    public void onCategoryClick(Integer position, String from) {
        categoryListingAdapter.updatePosition(position);
        categoryListingAdapter.notifyDataSetChanged();
        presenter.getCategoryItems(categories.get(position));
        categoryItemShimmer();
    }

    @Override
    public void onStop() {
        super.onStop();
        alanButton.removeCallback(alanCallback);
    }

    private void executeCommand(String commandName, JSONObject data) {
        switch (commandName) {
            case "choose_category": {
                try {
                    String title = data.getString("title");
                    for (int i = 0; i < categories.size(); i++) {
                        if (categories.get(i).equals(title)) {
                            categoryListingAdapter.updatePosition(i);
                            categoryListingAdapter.notifyDataSetChanged();
                            presenter.getCategoryItems(categories.get(i));
                            categoryItemShimmer();
                        }
                    }
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry, I'm unable to do action");
                    alanButton.deactivate();
                }
                alanButton.deactivate();
                break;
            }
            case "choose_food": {
                try {
                    String title = data.getString("title");
                    for (int i = 0; i < popularArrayList.size(); i++) {
                        if (popularArrayList.get(i).getName().equalsIgnoreCase(title)) {
                            addFoodThroughVoice(i);
                        }
                    }
                    alanButton.deactivate();

                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry, I'm unable to do action");
                }
                alanButton.deactivate();
                break;
            }

            case "continue": {
                FoodListFragmentDirections.ActionFoodListFragmentToSummaryFragment directions =
                        FoodListFragmentDirections.actionFoodListFragmentToSummaryFragment(tableInfoModel);
                Navigation.findNavController(requireView()).navigate(directions);
                alanButton.deactivate();
                break;
            }
            case "back": {

                try {
                    String title = data.getString("title");
                    showToast(requireContext(), title);
                    if (title.equals("go back") || title.equals("Back") || title.equals("cancel")) {
                        Navigation.findNavController(binding.getRoot()).popBackStack();
                    }

                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry, I'm unable to do action");
                }
                alanButton.deactivate();
                break;
            }
            default: {
                break;
            }
        }

    }

    private void addFoodThroughVoice(int position) {
        sessionManager.setIsCouponSelected(false);
        try {
            FoodModel model;
            model = popularArrayList.get(position);
            Cart cart = new Cart();
            cart.food = model;
            cart.food.setFoodId(model.getFoodId());
            cart.userId = sessionManager.getUserName();
            cart.status = 0;
            cart.quantity = 1;
            if (!db.orderDao().isFoodIsExist(sessionManager.getUserName(), model)) {
                db.orderDao().insert(cart);
                showSnackBar(binding.getRoot(), "Added to cart successfully");
                // sessionManager.setIsFoodAdded(true);
            } else {
                showSnackBar(binding.getRoot(), "Food already in the cart");
            }
        } catch (Exception e) {
            Log.d("Food Exception", "onItemClick: " + e.toString());
        }
    }
}