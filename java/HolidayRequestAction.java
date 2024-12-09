package com.example.practice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HolidayRequestAction extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_request_action, container, false);

        // Bind Buttons
        Button acceptButton = view.findViewById(R.id.button_accept);
        Button rejectButton = view.findViewById(R.id.button_reject);

        // Handle Accept Action
        acceptButton.setOnClickListener(v -> {
            // TODO: Logic to approve the holiday request
            Toast.makeText(getContext(), "Holiday Request Approved", Toast.LENGTH_SHORT).show();
        });

        // Handle Reject Action
        rejectButton.setOnClickListener(v -> {
            // TODO: Logic to reject the holiday request
            Toast.makeText(getContext(), "Holiday Request Rejected", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
