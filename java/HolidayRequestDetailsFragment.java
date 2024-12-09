package com.example.practice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HolidayRequestDetailsFragment extends Fragment {

    private HolidayRequest holidayRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_request_details, container, false);

        // Retrieve the HolidayRequest object passed from the RecyclerView
        if (getArguments() != null) {
            holidayRequest = (HolidayRequest) getArguments().getSerializable("holidayRequest");
        }

        // Bind views
        ImageView profileImage = view.findViewById(R.id.profile_image);
        EditText firstName = view.findViewById(R.id.edit_first_name);
        EditText lastName = view.findViewById(R.id.edit_last_name);
        EditText startDate = view.findViewById(R.id.edit_start_date);
        EditText endDate = view.findViewById(R.id.edit_end_date);
        TextView totalDays = view.findViewById(R.id.text_total_days);
        EditText reason = view.findViewById(R.id.edit_reason);
        TextView attachment = view.findViewById(R.id.text_attachment);
        Button acceptButton = view.findViewById(R.id.button_accept);
        Button rejectButton = view.findViewById(R.id.button_reject);

        // Populate views with data
        if (holidayRequest != null) {
            profileImage.setImageResource(holidayRequest.getProfileImage());
            firstName.setText(holidayRequest.getFirstName());
            lastName.setText(holidayRequest.getLastName());
            startDate.setText(holidayRequest.getHolidayStart());
            endDate.setText(holidayRequest.getHolidayEnd());
            totalDays.setText("Total Days: " + holidayRequest.getTotalDays());
            reason.setText(holidayRequest.getReason());
            attachment.setText("Attachment: " + holidayRequest.getAttachmentUrl());
        }

        // Handle Accept button click
        acceptButton.setOnClickListener(v -> {
            // Logic for approving the holiday request
        });

        // Handle Reject button click
        rejectButton.setOnClickListener(v -> {
            // Logic for rejecting the holiday request
        });

        return view;
    }
}
