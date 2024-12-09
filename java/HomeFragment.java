package com.example.practice2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Get the saved user role from SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        String userRole = sharedPreferences.getString("user_role", "employee"); // Default to "employee"

        // Determine the destination fragment
        Fragment targetFragment;
        if ("admin".equals(userRole)) {
            targetFragment = new AdminDashboard();
        } else {
            targetFragment = new EmployeeDashboard();
        }

        // Replace the current fragment with the correct dashboard
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container.getId(), targetFragment);
        transaction.commit();

        return view;
    }
}
