package com.example.practice2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDashboard extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_dashboard, container, false);


        // Bind the "View Profile" button
        Button viewProfileButton = view.findViewById(R.id.view_profile_button);
        // Bind the "Request Leave" button
        Button requestLeaveButton = view.findViewById(R.id.request_leave_button);

        // Set up the click listener
        requestLeaveButton.setOnClickListener(v -> navigateToHolidayRequestPage());

        // Set up click listener for the button
        viewProfileButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, new EmployeeProfile());
            transaction.addToBackStack(null); // Add to back stack for navigation
            transaction.commit();
        });




        return view;
    }

    private void navigateToHolidayRequestPage() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new EmployeeHolidayRequest()); // Replace with your HolidayRequest fragment class
        transaction.addToBackStack(null); // Add to back stack for navigation
        transaction.commit();
    }
}
