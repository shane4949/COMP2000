package com.example.practice2;

import android.database.Cursor;
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


public class AdminDashboard extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false);

        Button viewHolidayRequestsButton = view.findViewById(R.id.btn_view_holiday_requests);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_employees);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch employee records from the database and set up the adapter
        List<Employee> employees = getAllEmployeesFromDatabase();
        EmployeeAdapter adapter = new EmployeeAdapter(employees, employee -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            UserProfile userProfileFragment = new UserProfile();

            // Pass the employee ID using a Bundle
            Bundle bundle = new Bundle();
            bundle.putLong("employeeId", employee.getId());
            userProfileFragment.setArguments(bundle);

            transaction.replace(R.id.fragment_container, userProfileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        recyclerView.setAdapter(adapter);

        // Initialize FloatingActionButton
        FloatingActionButton fab = view.findViewById(R.id.fab_add_employee);
        fab.setOnClickListener(v -> {
            // Navigate to the Add Employee Fragment
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, new AddNewEmployee());
            transaction.addToBackStack(null); // Allows navigating back
            transaction.commit();
        });

        viewHolidayRequestsButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, new HolidayRequestsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }


    // Fetch employee data from the database
        private List<Employee> getAllEmployeesFromDatabase() {
        List<Employee> employees = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor cursor = dbHelper.getAllEmployees();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRSTNAME));
                String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LASTNAME));
                String position = "Employee"; // Placeholder
                int profileImageResId = R.drawable.profile; // Placeholder image

                employees.add(new Employee(id, firstName + " " + lastName, position, profileImageResId));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return employees;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the employee list
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view_employees);
        List<Employee> employees = getAllEmployeesFromDatabase();
        EmployeeAdapter adapter = new EmployeeAdapter(employees, employee -> {
            // Navigate to UserProfileFragment with employee data
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            UserProfile userProfileFragment = new UserProfile();

            // Pass the employee ID using Bundle
            Bundle bundle = new Bundle();
            bundle.putLong("employeeId", employee.getId());
            userProfileFragment.setArguments(bundle);

            transaction.replace(R.id.fragment_container, userProfileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        recyclerView.setAdapter(adapter);
    }



}
