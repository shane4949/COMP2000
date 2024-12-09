package com.example.practice2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HolidayRequestsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday_request, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_holiday_requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the holiday requests list and set the adapter
        List<HolidayRequest> holidayRequests = getDummyHolidayRequests();
        HolidayRequestAdapter adapter = new HolidayRequestAdapter(holidayRequests);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<HolidayRequest> getDummyHolidayRequests() {
        List<HolidayRequest> holidayRequests = new ArrayList<>();

        // Add dummy holiday requests
        holidayRequests.add(new HolidayRequest(
                "John",                     // First Name
                "Doe",                      // Last Name
                "2024-12-10",               // Holiday Start
                "2024-12-15",               // Holiday End
                R.drawable.profile,         // Profile Image
                5,                          // Total Days
                "Vacation with family",     // Reason
                "attachment_url_example"    // Attachment URL
        ));

        holidayRequests.add(new HolidayRequest(
                "Jane",                     // First Name
                "Smith",                    // Last Name
                "2024-12-20",               // Holiday Start
                "2024-12-25",               // Holiday End
                R.drawable.profile,         // Profile Image
                6,                          // Total Days
                "Christmas holidays",       // Reason
                "attachment_url_example"    // Attachment URL
        ));

        return holidayRequests;
    }
}
