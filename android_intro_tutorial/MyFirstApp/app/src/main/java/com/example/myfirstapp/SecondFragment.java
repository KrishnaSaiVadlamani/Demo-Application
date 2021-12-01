package com.example.myfirstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfirstapp.databinding.FragmentSecondBinding;

import java.util.concurrent.ThreadLocalRandom;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private TextView randomViewHeader;

    private TextView randomViewText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        randomViewHeader = binding.textviewHeader;
        randomViewText = binding.textviewRandom;
        return binding.getRoot();

    }

    private void setRandomViewHeader(TextView randomViewHeader, int limit){
        String headerStringFormat = randomViewHeader.getText().toString();
        String header = String.format(headerStringFormat,limit);
        randomViewHeader.setText(header);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer limit = this.getArguments().getInt("currentCount");
        setRandomViewText(randomViewText,limit);
        setRandomViewHeader(randomViewHeader,limit);
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    private void setRandomViewText(TextView randomViewText, int limit){
        Integer randomInteger = ThreadLocalRandom.current().nextInt(0,limit);
        randomViewText.setText(String.valueOf(randomInteger));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}