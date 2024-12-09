package com.example.practice2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentLogin extends Fragment {

    public FragmentLogin() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize UI components
        EditText emailField = view.findViewById(R.id.email);
        EditText passwordField = view.findViewById(R.id.password);
        SwitchCompat roleSwitch = view.findViewById(R.id.switch2);
        Button loginButton = view.findViewById(R.id.button);

        // Handle login button click
        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            boolean isEmployee = roleSwitch.isChecked(); // True for Employee, False for Admin

            // Perform validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Store the user role in SharedPreferences
            saveUserRole(isEmployee ? "employee" : "admin");

            // Navigate to the appropriate dashboard
            if (isEmployee) {
                navigateToFragment(new EmployeeDashboard());
            } else {
                navigateToFragment(new AdminDashboard());
            }
        });

        return view;
    }

    private void saveUserRole(String role) {
        // Save the role to SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_role", role);
        editor.apply();

        Toast.makeText(getContext(), "Role saved: " + role, Toast.LENGTH_SHORT).show();
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Optional: Add to back stack
        transaction.commit();
    }
}
