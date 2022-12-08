package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.adapter.AddFoodAdapter;
import com.example.pos_android.adapter.CategoryFoodAdapter;
import com.example.pos_android.adapter.CategoryListingAdapter;
import com.example.pos_android.contracts.UserHomeContract;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.model.TableInfoModel;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.food.CategoryDetailResponse;
import com.example.pos_android.data.model.food.CategoryModel;
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

import java.util.ArrayList;

public class FoodListFragment extends BaseFragment implements UserHomeContract.View, OnItemClickListener, onCategoryItemClick {
    AddFoodAdapter popularAdapter;
    AddFoodAdapter recentAdapter;
    CategoryFoodAdapter categoryFoodAdapter;
    CategoryListingAdapter categoryListingAdapter;
    UserHomePresenter presenter;
    ArrayList<FoodModel> popularArrayList = new ArrayList<>();
    ArrayList<FoodModel> recommendedList = new ArrayList<>();
    private FragmentFoodListBinding binding;
    private SessionManager sessionManager;
    private CartDatabase db;
    private TableInfoModel tableInfoModel;

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
        tableInfoModel = FoodListFragmentArgs.fromBundle(getArguments()).getTableInfo();
        sessionManager = new SessionManager(requireContext());
        String json = TableInfoModel.convertToJson(tableInfoModel);
        sessionManager.setIsFoodAdded(json);
        db = CartDatabase.getAppDatabase(requireContext());
        presenter = new UserHomePresenter(FoodListFragment.this, requireContext());
        presenter.getHomeDetails();

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
        showToast(requireContext(), message);
    }


    @Override
    public void showUserResponse(UserHomeResponse response) {
        // showToast(requireContext(), response.getMessage());
        popularArrayList.clear();
        recommendedList.clear();
        for (UserHomeResponse.PopularFood food : response.getData().getPopularFoods()) {
            popularArrayList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice()));
            recommendedList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice()));
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
        binding.titleCategory.setVisibility(View.VISIBLE);
        binding.rvCategories.setVisibility(View.VISIBLE);
        categoryListingAdapter =new CategoryListingAdapter(foodCategoryResponse.data.foodCategory,requireContext(),this);
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        binding.rvCategories.setAdapter(categoryListingAdapter);
    }

    @Override
    public void showCategoryItemsResponse(CategoryDetailResponse categoryDetailResponse) {
        categoryFoodAdapter = new CategoryFoodAdapter(categoryDetailResponse.data,requireContext(),this);
        binding.rvCategoriesItems.setLayoutManager(new GridLayoutManager(requireContext(),2));
        binding.rvCategoriesItems
    }

    @Override
    public void onItemClick(Integer position, String from) {

        sessionManager.setIsCouponSelected(false);
        try {
            FoodModel model = popularArrayList.get(position);
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
            e.printStackTrace();
        }
    }

    @Override
    public void onCategoryClick(Integer position, String from) {
        presenter.getCategoryItems(new CategoryModel(from));
    }
}