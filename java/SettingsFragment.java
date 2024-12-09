package com.example.practice2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Bind views
        Button changePasswordButton = view.findViewById(R.id.button_change_password);
        Button deleteAccountButton = view.findViewById(R.id.button_delete_account);
        Switch darkModeSwitch = view.findViewById(R.id.switch_dark_mode);
        Switch notificationsSwitch = view.findViewById(R.id.switch_notifications);
        Button logoutButton = view.findViewById(R.id.button_logout);

        // Set up click listeners
        changePasswordButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Change Password Clicked", Toast.LENGTH_SHORT).show());

        deleteAccountButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Delete Account Clicked", Toast.LENGTH_SHORT).show());

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
                // Implement dark mode enabling here
            } else {
                Toast.makeText(getContext(), "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
                // Implement dark mode disabling here
            }
        });

        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getContext(), "Notifications Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle Logout button
        logoutButton.setOnClickListener(v -> handleLogout());


        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });


        return view;
    }

    private void handleLogout() {
        // Clear SharedPreferences to remove saved user session
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        // Show a toast message
        Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Navigate back to the login fragment
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new FragmentLogin());
        transaction.commit();
    }
}
