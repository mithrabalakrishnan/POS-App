package com.example.pos_android.view.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.pos_android.contracts.UserProfileContract;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.databinding.FragmentEditProfileBinding;
import com.example.pos_android.presenter.UserProfilePresenter;
import com.example.pos_android.view.BaseFragment;
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
        makeEditable(binding.editUserName);
        makeEditable(binding.editEmail);
        makeEditable(binding.editFirstName);
        makeEditable(binding.editLastName);
        makeEditable(binding.editMobile);
        binding.buttonUpdate.setVisibility(View.VISIBLE);
    }

    private void makeNotEditable() {
        makeNonEditable(binding.editUserName);
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
}