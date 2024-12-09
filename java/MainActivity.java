package com.example.practice2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Initially load the login fragment
        if (savedInstanceState == null) {
            loadFragment(new FragmentLogin());
        }

        // Setup bottom navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.notifications) {
                selectedFragment = new NotificationsFragment();
            } else if (item.getItemId() == R.id.settings) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });

        // Listen for fragment changes to toggle BottomNavigationView visibility
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment instanceof FragmentLogin) {
                bottomNavigationView.setVisibility(View.GONE); // Hide on login page
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE); // Show on other pages
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Add to back stack for proper navigation
                .commit();
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }
}
