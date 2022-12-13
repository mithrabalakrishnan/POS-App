package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.navigation.Navigation;

import com.example.pos_android.contracts.UserProfileContract;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentEditProfileBinding;
import com.example.pos_android.presenter.UserProfilePresenter;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfileFragment extends BaseFragment implements UserProfileContract.View {
    private FragmentEditProfileBinding binding;
    private UserProfilePresenter presenter;
    private boolean isEditEnabled = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        changeEditFlag();
        presenter = new UserProfilePresenter(this, requireContext());
        presenter.getUserProfile();
        binding.switchEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isEditEnabled = isChecked;
                binding.switchEdit.setChecked(isEditEnabled);
                changeEditFlag();
            }
        });

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateUserProfile(
                        binding.editFirstName.getText().toString(),
                        binding.editLastName.getText().toString(),
                        binding.editEmail.getText().toString(),
                        binding.editMobile.getText().toString()
                );
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).popBackStack();
            }
        });
    }

    private void changeEditFlag() {
        if (isEditEnabled) {
            setupEditFields();
        } else {
            makeNotEditable();
        }
    }

    private void setupEditFields() {
        makeEditable(binding.editEmail);
        makeEditable(binding.editFirstName);
        makeEditable(binding.editLastName);
        makeEditable(binding.editMobile);
        binding.buttonUpdate.setVisibility(View.VISIBLE);
    }

    private void makeNotEditable() {
        makeNonEditable(binding.editEmail);
        makeNonEditable(binding.editFirstName);
        makeNonEditable(binding.editLastName);
        makeNonEditable(binding.editMobile);
        binding.buttonUpdate.setVisibility(View.GONE);
    }

    private void makeEditable(TextInputEditText view) {
        view.setEnabled(true);
    }

    private void makeNonEditable(TextInputEditText view) {
        view.setEnabled(false);
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
    public void showUserProfileResponse(UserProfileResponse response) {
        binding.editFirstName.setText(response.getData().getFirstName());
        binding.editLastName.setText(response.getData().getLastName());
        binding.editUserName.setText(response.getData().getUsername());
        binding.editEmail.setText(response.getData().getEmail());
        binding.editMobile.setText(response.getData().getPhone_no());
    }

    @Override
    public void showSuccess(String message) {
        showToast(requireContext(), message);
        Navigation.findNavController(requireView()).popBackStack();
    }
}