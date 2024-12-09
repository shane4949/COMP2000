package com.example.practice2;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class UserProfile extends Fragment {

    private long employeeId; // To store the employee ID
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        dbHelper = new DatabaseHelper(getContext());

        ImageView profileImage = view.findViewById(R.id.profile_image);
        Button editButton = view.findViewById(R.id.button_edit);
        Button deleteButton = view.findViewById(R.id.button_delete);
        LinearLayout fieldsContainer = view.findViewById(R.id.fields_container);

        // Get the employee ID passed from the AdminDashboard
        employeeId = getArguments() != null ? getArguments().getLong("employeeId", -1) : -1;

        if (employeeId != -1) {
            populateFieldsWithData(fieldsContainer);
        } else {
            Toast.makeText(getContext(), "Invalid employee ID", Toast.LENGTH_SHORT).show();
            return view;
        }

        // Edit Button Functionality
        editButton.setOnClickListener(v -> enableEditing(fieldsContainer, editButton));

        // Delete Button Functionality
        deleteButton.setOnClickListener(v -> showDeleteConfirmationDialog());

        return view;
    }

    private void populateFieldsWithData(LinearLayout fieldsContainer) {
        Cursor cursor = dbHelper.getEmployeeById(employeeId);

        if (cursor != null && cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                String columnName = cursor.getColumnName(i);
                String value = cursor.getString(i);

                if (value == null || value.isEmpty()) value = "N/A";

                // Create a field view
                LinearLayout fieldLayout = new LinearLayout(getContext());
                fieldLayout.setOrientation(LinearLayout.VERTICAL);
                fieldLayout.setPadding(16, 16, 16, 16);

                TextView label = new TextView(getContext());
                label.setText(columnName + ":");
                label.setTextSize(16);
                fieldLayout.addView(label);

                EditText fieldValue = new EditText(getContext());
                fieldValue.setText(value);
                fieldValue.setEnabled(false); // Disable editing by default
                fieldValue.setTag(columnName); // Save column name as tag for updates
                fieldLayout.addView(fieldValue);

                fieldsContainer.addView(fieldLayout);
            }
            cursor.close();
        } else {
            Toast.makeText(getContext(), "No data found for employee", Toast.LENGTH_SHORT).show();
        }
    }

    private void enableEditing(LinearLayout fieldsContainer, Button editButton) {
        boolean isEditing = editButton.getText().toString().equals("Edit");

        for (int i = 0; i < fieldsContainer.getChildCount(); i++) {
            View fieldLayout = fieldsContainer.getChildAt(i);

            if (fieldLayout instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) fieldLayout;

                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    View child = linearLayout.getChildAt(j);
                    if (child instanceof EditText) {
                        ((EditText) child).setEnabled(isEditing); // Enable/Disable editing
                    }
                }
            }
        }

        if (isEditing) {
            editButton.setText("Save");
        } else {
            saveUpdates(fieldsContainer);
            editButton.setText("Edit");
        }
    }

    private void saveUpdates(LinearLayout fieldsContainer) {
        ContentValues values = new ContentValues();
        long employeeId = requireArguments().getLong("employeeId"); // Get the employee ID from arguments

        for (int i = 0; i < fieldsContainer.getChildCount(); i++) {
            View fieldLayout = fieldsContainer.getChildAt(i);

            if (fieldLayout instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) fieldLayout;

                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    View child = linearLayout.getChildAt(j);
                    if (child instanceof EditText) {
                        EditText field = (EditText) child;
                        String columnName = (String) field.getTag(); // Database column name
                        String value = field.getText().toString().trim();

                        if (value.isEmpty()) {
                            // Skip optional fields
                            if (columnName.equals(DatabaseHelper.COLUMN_MIDDLENAME) ||
                                    columnName.equals(DatabaseHelper.COLUMN_ADDRESS_LINE_2) ||
                                    columnName.equals(DatabaseHelper.COLUMN_WORK_PHONE) ||
                                    columnName.equals(DatabaseHelper.COLUMN_WORK_EMAIL) ||
                                    columnName.equals(DatabaseHelper.COLUMN_EMERGENCY_CONTACT_EMAIL)) {
                                continue;
                            } else {
                                field.setError("This field is required!");
                                return; // Exit early if a mandatory field is empty
                            }
                        }

                        values.put(columnName, value);
                    }
                }
            }
        }

        // Update the database
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        int rowsAffected = dbHelper.updateEmployee(employeeId, values);

        if (rowsAffected > 0) {
            Toast.makeText(getContext(), "Employee updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to update employee", Toast.LENGTH_SHORT).show();
        }
    }

    

    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Employee")
                .setMessage("Are you sure you want to delete this employee?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    int rowsDeleted = dbHelper.deleteEmployee(employeeId);
                    if (rowsDeleted > 0) {
                        Toast.makeText(getContext(), "Employee deleted successfully", Toast.LENGTH_SHORT).show();
                        navigateBackToAdminDashboard();
                    } else {
                        Toast.makeText(getContext(), "Failed to delete employee", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void navigateBackToAdminDashboard() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack(); // Go back to AdminDashboard
    }
}
