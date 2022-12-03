package com.example.pos_android.view.user;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pos_android.R;
import com.example.pos_android.adapter.SummaryAdapter;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.model.TableInfoModel;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.data.room.CartDatabase;
import com.example.pos_android.data.room.entity.Cart;
import com.example.pos_android.databinding.FragmentSummaryBinding;
import com.example.pos_android.utils.ConfirmDialog;
import com.example.pos_android.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class SummaryFragment extends BaseFragment implements SummaryAdapter.onCartItemClickListener, ConfirmDialog.ConfirmCheckoutListener {
    private FragmentSummaryBinding binding;
    private SessionManager sessionManager;
    private ArrayList<FoodModel> foodList = new ArrayList<>();
    private ArrayList<FoodModel> oldFoodList = new ArrayList<>();
    private List<Cart> cartList = new ArrayList<>();
    private SummaryAdapter summaryAdapter;
    private CartDatabase db;
    private float total = 0;
    private String formattedString;
    private TableInfoModel tableInfoModel;
    private ConfirmDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    db.orderDao().deleteAll();
                }
                return false;
            }
        });
        if(sessionManager.getIsCouponSelected()){
            binding.layoutCoupon.setVisibility(View.GONE);
            binding.txtCoupon.setText("Applied "+sessionManager.getCouponPercent()+"% Off");
            binding.layoutCouponItem.setVisibility(View.VISIBLE);
        }else{
           binding.layoutCoupon.setVisibility(View.VISIBLE);
           binding.layoutCouponItem.setVisibility(View.GONE);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setupObserver();
        setGrandTotal();
    }


    private void initView() {
        // tableInfoModel = SummaryFragmentArgs.fromBundle(getArguments()).getTableSummary();
        sessionManager = new SessionManager(requireContext());
        tableInfoModel = TableInfoModel.convertToTableInfo(sessionManager.getIsFoodAdded());
        db = CartDatabase.getAppDatabase(requireContext());
        dialog = new ConfirmDialog(requireContext(), this, "Are you sure to checkout ?");

        setupCartRecyclerView();

        binding.tvGuestCount.setText("Guests : " + tableInfoModel.getGuestCount());
        binding.tvTableType.setText("Table : " + tableInfoModel.getTableCategory());
        binding.tvTableTime.setText("Date : " + tableInfoModel.getDate() + " | " + "Time : " + tableInfoModel.getTime());

        if(sessionManager.getIsCouponSelected()){
            binding.layoutCoupon.setVisibility(View.GONE);
            binding.txtCoupon.setText("Applied "+sessionManager.getCouponPercent()+"% Off");
            binding.layoutCouponItem.setVisibility(View.VISIBLE);
        }else{
            binding.layoutCoupon.setVisibility(View.VISIBLE);
            binding.layoutCouponItem.setVisibility(View.GONE);
        }
        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    private void setupObserver() {
        showLoadingDialog(requireContext());
        cartList.clear();
        cartList = db.orderDao().getOrderByUserId(sessionManager.getUserName());
        Log.d("cart", cartList.toString());
        for (Cart item : cartList) {
            foodList.add(
                    new FoodModel(
                            item.food.getFoodId(),
                            item.food.getName(),
                            item.food.getImageUrl(),
                            item.food.getPrice(),
                            item.quantity
                    )
            );
        }
        oldFoodList.addAll(foodList);
        updateRecyclerView();
    }

    private void setupCartRecyclerView() {
        summaryAdapter = new SummaryAdapter(requireContext(), foodList, this);
        binding.recyclerFoodItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerFoodItems.setHasFixedSize(true);
        binding.recyclerFoodItems.setAdapter(summaryAdapter);
    }

    private void updateRecyclerView() {
        hideLoadingDialog();
        if (foodList != null && !foodList.isEmpty()) {
            summaryAdapter.notifyDataSetChanged();
            binding.recyclerFoodItems.setVisibility(View.VISIBLE);
            binding.layoutTotal.setVisibility(View.VISIBLE);
            binding.tvTable.setVisibility(View.VISIBLE);
            binding.cardTable.setVisibility(View.VISIBLE);
            binding.layoutTotal.setVisibility(View.VISIBLE);
            binding.ivNoCart.setVisibility(View.GONE);
            binding.buttonUpdate.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerFoodItems.setVisibility(View.GONE);
            showSnackBar(binding.getRoot(), getString(R.string.no_items));
            binding.recyclerFoodItems.setVisibility(View.GONE);
            binding.layoutTotal.setVisibility(View.GONE);
            binding.ivNoCart.setVisibility(View.VISIBLE);
            binding.buttonUpdate.setVisibility(View.GONE);
            binding.tvTable.setVisibility(View.GONE);
            binding.cardTable.setVisibility(View.GONE);

        }
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onQuantityAdd(int position) {
        FoodModel model = foodList.get(position);
        model.setQuantity(model.getQuantity() + 1);
        summaryAdapter.notifyDataSetChanged();
        setGrandTotal();
    }

    private void setGrandTotal() {
        total = 0;
        for (int i = 0; i < foodList.size(); i++) {
            for (int j = 1; j <= foodList.get(i).getQuantity(); j++) {
                total += Float.parseFloat(foodList.get(i).getPrice());
            }
        }
        formattedString = String.format("%.02f", total);
        binding.textTotal.setText("£" + String.valueOf(formattedString));
        Log.d("foodList", foodList.toString());
    }

    @Override
    public void onQuantitySub(int position) {
        FoodModel model = foodList.get(position);
        model.setQuantity(model.getQuantity() - 1);
        Log.d("foodList", foodList.toString());
        summaryAdapter.notifyDataSetChanged();
        setGrandTotal();
    }

    @Override
    public void onDeleteItem(int position) {
        Cart model = cartList.get(position);
        db.orderDao().deleteCartById(model.orderId);
        showToast(requireContext(), "Deleted Successfully");
        refreshCurrentFragment();
        // setupObserver();
    }

    @Override
    public void checkOut() {
        List<Integer> foodId = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        List<Integer> total = new ArrayList<>();

        for (FoodModel model : foodList) {
            int totalPrice = model.getQuantity() * Integer.parseInt(model.getPrice());
            foodId.add(Integer.parseInt(model.getFoodId()));
            quantity.add(model.getQuantity());
            total.add(totalPrice);
        }


        OrderInfoModel orderInfoModel = new OrderInfoModel(
                foodId, quantity, total, tableInfoModel.getTime(), tableInfoModel.getDate(),
                tableInfoModel
        );

        SummaryFragmentDirections.ActionSummaryFragmentToPaymentFragment directions =
                SummaryFragmentDirections.actionSummaryFragmentToPaymentFragment(orderInfoModel);

        Navigation.findNavController(requireView()).navigate(directions);
    }

    private void refreshCurrentFragment() {
        NavController navController = Navigation.findNavController(requireView());
        int id = navController.getCurrentDestination().getId();
        navController.popBackStack(id, true);
        navController.navigate(id);
    }

}