package com.example.pos_android.view.user;

import android.os.Bundle;
import android.speech.tts.Voice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.pos_android.R;
import com.example.pos_android.databinding.FragmentOrderSuccessBinding;

public class OrderSuccessFragment extends Fragment {
    private FragmentOrderSuccessBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false);
        binding.btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.action_orderSuccessFragment_to_homeFragment);
            }
        });
        return binding.getRoot();
    }
}