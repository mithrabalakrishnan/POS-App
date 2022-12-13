package com.example.pos_android.view.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pos_android.R;
import com.example.pos_android.contracts.AddFoodContract;
import com.example.pos_android.data.model.ImagePickerResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityAddFoodBinding;
import com.example.pos_android.presenter.AddFoodPresenter;
import com.example.pos_android.utils.Validation;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

import java.io.File;

public class AddFoodActivity extends BaseActivity implements AddFoodContract.View {
    int SELECT_PICTURE = 200;
    int EXTERNAL_STORAGE_PERMISSION_CODE = 300;
    File imageFile;
    boolean hasPermission = false;
    String imagePath;
    private ActivityAddFoodBinding binding;
    private AddFoodPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new AddFoodPresenter(this, this);
        checkPermissions();
        binding.coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasPermission)
                    imageChooser();
            }
        });
        binding.iconBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.btnAdd.setOnClickListener(v -> {
            validateFields();
        });
    }

    private void validateFields() {
        if (imagePath == null) {
            showSnackBar(binding.getRoot(), "Please choose image");
        } else if (!Validation.isNotNullOrEmpty(binding.txtName.getText().toString())) {
            binding.textLayoutName.setError("Please enter name");
        } else if (!Validation.isNotNullOrEmpty(binding.txtCategory.getText().toString())) {
            binding.txtLayoutCategory.setError("Please enter category");
            binding.textLayoutName.setError(null);
        } else if (!Validation.isNotNullOrEmpty(binding.txtPrice.getText().toString())) {
            binding.txtPrice.setError("Please enter price");
            binding.txtLayoutCategory.setError(null);
        } else {
            presenter.addFood(imagePath, binding.txtName.getText().toString(),
                    binding.txtCategory.getText().toString(),
                    binding.txtPrice.getText().toString()
            );
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(AddFoodActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            hasPermission = true;
        } else {
            ActivityCompat.requestPermissions(AddFoodActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_PERMISSION_CODE);
        }
    }

    void imageChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                imageFile = new File(getRealPathFromURI(selectedImageUri));

                if (imageFile != null)
                    presenter.addImageUrl(imageFile);

                // MultipartBody.Part multipartBody =MultipartBody.Part.createFormData("file",file.getName(),requestFile);

                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    binding.coverImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Checking whether user granted the permission or not.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Showing the toast message
           // showToast(AddFoodActivity.this, "Permission granted");
            hasPermission = true;
        } else {
            hasPermission = false;
          //  showToast(AddFoodActivity.this, "Permission denied");
        }
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentURI, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    @Override
    public void showAddFoodSuccessResponse(String message) {
        showToast(AddFoodActivity.this, message);
        onBackPressed();
    }

    @Override
    public void showImageSuccess(ImagePickerResponse response) {
        imagePath = response.getData().getFileDownloadUri();
        //showToast(AddFoodActivity.this, response.getMessage());
    }

    @Override
    public void showProgressBar() {
        showLoadingDialog(AddFoodActivity.this);
    }

    @Override
    public void hideProgressBar() {
        hideLoadingDialog();
    }

    @Override
    public void showApiErrorWarning(String string) {
        hideLoadingDialog();
        if (string.equalsIgnoreCase(getResources().getString(R.string.unauthorized))) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.clear();
            showToast(this, "Session expired");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        } else
            showToast(this, string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(AddFoodActivity.this, message);
    }
}