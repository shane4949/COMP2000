package com.example.practice2;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EmployeeProfile extends Fragment {

    private DatabaseHelper dbHelper; // Database helper instance
    private long employeeId; // ID of the logged-in employee

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_profile, container, false);

        dbHelper = new DatabaseHelper(getContext());
        LinearLayout fieldsContainer = view.findViewById(R.id.fields_container);
        Button saveProfileButton = view.findViewById(R.id.button_save_profile);

        // Assume employeeId is passed from the login screen or stored in SharedPreferences
        employeeId = getArguments() != null ? getArguments().getLong("employeeId", -1) : -1;

        if (employeeId == -1) {
            Toast.makeText(getContext(), "Invalid employee ID", Toast.LENGTH_SHORT).show();
            return view;
        }

        // Populate fields with data from the database
        populateFieldsWithData(fieldsContainer);

        // Save Button Logic
        saveProfileButton.setOnClickListener(v -> saveProfileChanges(fieldsContainer));

        return view;
    }

    /**
     * Fetch and populate the fields with employee data from the database.
     */
    private void populateFieldsWithData(LinearLayout fieldsContainer) {
        Cursor cursor = dbHelper.getEmployeeById(employeeId);

        if (cursor != null && cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                String columnName = cursor.getColumnName(i);
                String value = cursor.getString(i);

                if (value == null || value.isEmpty()) value = "N/A";

                // Create a TextView for the field name
                TextView label = new TextView(getContext());
                label.setText(formatColumnName(columnName));
                label.setTextSize(16);
                fieldsContainer.addView(label);

                // Create an EditText for the field value
                EditText fieldValue = new EditText(getContext());
                fieldValue.setText(value);
                fieldValue.setTag(columnName); // Store the column name as a tag for saving
                fieldValue.setEnabled(false); // Disable editing by default
                fieldsContainer.addView(fieldValue);
            }
            cursor.close();
        } else {
            Toast.makeText(getContext(), "Failed to load employee data", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Save changes to the database when the Save button is clicked.
     */
    private void saveProfileChanges(LinearLayout fieldsContainer) {
        ContentValues values = new ContentValues();

        for (int i = 0; i < fieldsContainer.getChildCount(); i++) {
            View view = fieldsContainer.getChildAt(i);
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                String columnName = (String) editText.getTag();
                String value = editText.getText().toString().trim();

                // Skip "N/A" placeholder values
                if (!value.equals("N/A")) {
                    values.put(columnName, value);
                }
            }
        }

        // Update the database
        int rowsAffected = dbHelper.updateEmployee(employeeId, values);

        if (rowsAffected > 0) {
            Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Format the database column name into a user-friendly format.
     */
    private String formatColumnName(String columnName) {
        return columnName.replace("_", " ").toUpperCase();
    }
}
