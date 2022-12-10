package com.example.pos_android.view.user;

import static android.content.Context.KEYGUARD_SERVICE;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.core.app.ActivityCompat;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.pos_android.R;
import com.example.pos_android.contracts.UserProfileContract;
import com.example.pos_android.data.model.UserProfileData;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentProfileBinding;
import com.example.pos_android.presenter.UserProfilePresenter;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class ProfileFragment extends BaseFragment implements UserProfileContract.View {
    private FragmentProfileBinding binding;
    private SessionManager sessionManager;
    private UserProfilePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
        sessionManager = new SessionManager(requireContext());
        presenter = new UserProfilePresenter(this, requireContext());
        presenter.getUserProfile();
        loadUserImage();

        binding.tvUserName.setText(sessionManager.getUserName());

        if (sessionManager.getIsAuthentication()) {
            binding.simpleSwitch.setChecked(true);
        } else {
            binding.simpleSwitch.setChecked(false);
        }

        binding.simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    boolean checkSupport = checkBiometricSupport();
                    if (checkSupport) {

                        sessionManager.setIsAuthentication(true);
                        binding.simpleSwitch.setChecked(true);
                    } else {
                        sessionManager.setIsAuthentication(false);
                        binding.simpleSwitch.setChecked(false);
                    }
                }
                sessionManager.setIsAuthentication(false);
            }
        });

        binding.settingsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.action_profileFragment_to_editProfileFragment);
            }
        });
    }

    private void loadUserImage() {
        if (sessionManager.getProfileImageUrl().equals("")) {
            String randomText = generateRandomString();
            String url = "https://avatars.dicebear.com/api/male/" + randomText + ".png";
            sessionManager.setProfileUrl(url);

            Glide.with(this)
                    .load(sessionManager.getProfileImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .fitCenter()
                    .into(binding.ivUser);
        } else {
            Glide.with(this)
                    .load(sessionManager.getProfileImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .fitCenter()
                    .into(binding.ivUser);
        }
    }

    private void showLogoutDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("Logout");
        builder.setIcon(R.drawable.app_main);
        builder.setMessage("Are you sure want to Logout ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sessionManager.clear();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finishAffinity();
            dialogInterface.dismiss();
        }).setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss()).show();

    }

    private String generateRandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int length = 5;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
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
        showToast(requireContext(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(requireContext(), message);
    }


    @Override
    public void showUserProfileResponse(UserProfileResponse response) {
        UserProfileData mData = response.getData();
        binding.tvUserName.setText(String.format("%s%s%s", mData.firstName, " ", mData.lastName));
    }

    private Boolean checkBiometricSupport() {

        KeyguardManager keyguardManager =
                (KeyguardManager) requireContext().getSystemService(KEYGUARD_SERVICE);

        PackageManager packageManager = requireContext().getPackageManager();

        if (!keyguardManager.isKeyguardSecure()) {
            showToast(requireContext(), "Lock screen security not enabled in Settings");

            return false;
        }

        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.USE_BIOMETRIC) !=
                PackageManager.PERMISSION_GRANTED) {
            showToast(requireContext(), "Fingerprint authentication permission not enabled");

            return false;
        }

        packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);

        return true;
    }
}