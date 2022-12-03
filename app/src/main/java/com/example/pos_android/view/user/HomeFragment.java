package com.example.pos_android.view.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.pos_android.R;
import com.example.pos_android.adapter.FoodAdapter;
import com.example.pos_android.contracts.UserHomeContract;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentHomeBinding;
import com.example.pos_android.presenter.UserHomePresenter;
import com.example.pos_android.utils.VoiceCommandUtil;
import com.example.pos_android.utils.VoiceDialog;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements UserHomeContract.View, VoiceDialog.OnVoiceReceivedListener {
    private static final int RECORD_REQUEST_CODE = 100;
    FoodAdapter popularAdapter;
    FoodAdapter recentAdapter;
    UserHomePresenter presenter;
    List<SlideModel> slideModels = new ArrayList<>();
    ArrayList<FoodModel> popularArrayList = new ArrayList<>();
    ArrayList<FoodModel> recentArray = new ArrayList<>();
    private FragmentHomeBinding binding;
    VoiceCommandUtil voiceCommandUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData();

        binding.imageSlider.setImageList(slideModels);

        binding.recentRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false));
        recentAdapter = new FoodAdapter(recentArray, false, requireContext());
        binding.recentRecyclerview.setAdapter(recentAdapter);
    }

    private void initData() {

        presenter = new UserHomePresenter(HomeFragment.this, requireContext());
        presenter.getHomeDetails();

        slideModels.clear();
        slideModels.add(new SlideModel(R.drawable.poster, ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://c8.alamy.com/comp/2AAMYCB/set-of-poster-offer-delicious-fast-food-2AAMYCB.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://thumbs.dreamstime.com/z/fast-food-best-offer-banner-template-restaurant-cafe-design-element-poster-invitation-voucher-flyer-coupon-brochure-vector-153266434.jpg", ScaleTypes.FIT));

        recentArray.clear();
        recentArray.add(new FoodModel("Biriyani", R.drawable.dm1, "biriyaniii"));
        recentArray.add(new FoodModel("Mandhi", R.drawable.dm2, "delicious food"));
        recentArray.add(new FoodModel("Curry", R.drawable.dm3, "Fish curry"));
        recentArray.add(new FoodModel("Breakfast", R.drawable.dm4, "Break fast combo"));
        recentArray.add(new FoodModel("Meals", R.drawable.dm1, "Good meals"));

        binding.ivBooking.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_homeFragment_to_tableReservationFragment);
        });

        binding.ivMic.setOnClickListener(v -> {
            checkPermissions();
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
        hideLoadingDialog();
        if (string.equals("HTTP 401 ")) {
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
        for (UserHomeResponse.PopularFood food : response.getData().getPopularFoods()) {
            popularArrayList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice()
            ));
        }
        popularAdapter = new FoodAdapter(popularArrayList, requireContext());
        binding.popularRecyclerview.setAdapter(popularAdapter);
        binding.popularRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false));

    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    RECORD_REQUEST_CODE);
        } else {
            VoiceDialog voiceDialog = new VoiceDialog(requireContext(), this);
            voiceDialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                VoiceDialog voiceDialog = new VoiceDialog(requireContext(), this);
                voiceDialog.show();
            } else
                showSnackBar(requireView(), "Please give access to microphone");
        }
    }

    @Override
    public void onReceiveText(String value) {
        showToast(requireContext(), value);
        Log.d("Voice command", "onReceiveText: "+value);
        if (voiceCommandUtil == null)
            voiceCommandUtil = new VoiceCommandUtil(value.toUpperCase(), getActivity().findViewById(R.id.bottomNavigationView));
        else
            voiceCommandUtil.performCommand(value.toUpperCase());
    }
}