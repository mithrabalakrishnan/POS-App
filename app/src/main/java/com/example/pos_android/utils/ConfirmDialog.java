package com.example.pos_android.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pos_android.R;
import com.google.android.material.button.MaterialButton;

public class ConfirmDialog extends Dialog {
    private Context context;
    private ConfirmCheckoutListener clearCartListener;
    private String text;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public ConfirmDialog(@NonNull Context context, ConfirmCheckoutListener listener, String text) {
        super(context);
        this.context = context;
        this.clearCartListener = listener;
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.checkout_layout);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView textView = findViewById(R.id.tv_dialog);
        textView.setText(text);
        MaterialButton btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        MaterialButton btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCartListener.checkOut();
                cancel();
            }
        });

    }

    public interface ConfirmCheckoutListener {
        void checkOut();
    }



}
