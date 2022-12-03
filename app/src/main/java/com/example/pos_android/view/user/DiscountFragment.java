package com.example.pos_android.view.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pos_android.R;
import com.example.pos_android.adapter.CouponsAdapter;
import com.example.pos_android.data.model.CouponsData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentDiscountBinding;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.view.BaseFragment;


public class DiscountFragment extends BaseFragment implements OnItemClickListener {
    private FragmentDiscountBinding binding;
    private SessionManager sessionManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDiscountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        sessionManager = new SessionManager(requireContext());
        CouponsData[] CouponsData = new CouponsData[]{
                new CouponsData("MASTERCARD", "Get up to 20% off \nValid until 01 February 2022", "20% OFF", R.drawable.mastercard,20),
                new CouponsData("FRIDAY50", "Get up to 50% off \nValid until 05 January 2022", "50% OFF", R.drawable.black_friday,50),
                new CouponsData("MBK40", "Get up to 20 € \nValid until 20 AUGUST 2022", "20 €", R.drawable.offer,20),
                new CouponsData("DAYOFF25", "Get up to 25% off \nValid until 01 February 2022", "25% OFF", R.drawable.shopping_bag,25),

        };


        CouponsAdapter adapter = new CouponsAdapter(CouponsData,requireContext(),this);
        binding.recyclerViewCoupon.setHasFixedSize(true);
        binding.recyclerViewCoupon.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewCoupon.setAdapter(adapter);
    }


    @Override
    public void onItemClick(Integer position, String from) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_couponFragment_to_summaryFragment);
    }
}