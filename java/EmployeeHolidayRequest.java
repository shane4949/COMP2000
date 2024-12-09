package com.example.practice2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeHolidayRequest extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_employee_holiday_request, container, false);

        // Bind UI components
        EditText firstNameField = view.findViewById(R.id.edit_first_name);
        EditText lastNameField = view.findViewById(R.id.edit_last_name);
        EditText startDateField = view.findViewById(R.id.edit_start_date);
        EditText endDateField = view.findViewById(R.id.edit_end_date);
        EditText reasonField = view.findViewById(R.id.edit_reason);

        Button submitButton = view.findViewById(R.id.button_submit);
        Button cancelButton = view.findViewById(R.id.button_cancel);

        // Handle submit button click
        submitButton.setOnClickListener(v -> {
            String firstName = firstNameField.getText().toString().trim();
            String lastName = lastNameField.getText().toString().trim();
            String startDate = startDateField.getText().toString().trim();
            String endDate = endDateField.getText().toString().trim();
            String reason = reasonField.getText().toString().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || reason.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Submit the holiday request (logic to be implemented)
                Toast.makeText(getContext(), "Holiday request submitted!", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle cancel button click
        cancelButton.setOnClickListener(v -> {
            // Navigate back to the previous screen
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}
