package com.example.practice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class HolidayRequestAdapter extends RecyclerView.Adapter<HolidayRequestAdapter.HolidayRequestViewHolder> {

    private List<HolidayRequest> holidayRequests;

    public HolidayRequestAdapter(List<HolidayRequest> holidayRequests) {
        this.holidayRequests = holidayRequests;
    }

    @NonNull
    @Override
    public HolidayRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_holiday_request, parent, false);
        return new HolidayRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayRequestViewHolder holder, int position) {
        HolidayRequest request = holidayRequests.get(position);

        holder.profileImage.setImageResource(request.getProfileImage());
        holder.employeeName.setText(request.getFirstName() + " " + request.getLastName());
        holder.holidayStart.setText("Start: " + request.getHolidayStart());
        holder.holidayEnd.setText("End: " + request.getHolidayEnd());

        // Set up item click listener to navigate to details fragment
        holder.itemView.setOnClickListener(v -> {
            FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            HolidayRequestDetailsFragment detailsFragment = new HolidayRequestDetailsFragment();
            Bundle args = new Bundle();
            args.putSerializable("holidayRequest", (Serializable) request); // Pass the HolidayRequest object
            detailsFragment.setArguments(args);

            transaction.replace(R.id.fragment_container, detailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // Accept button click
        holder.acceptButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Accepted holiday request for " + request.getFirstName(), Toast.LENGTH_SHORT).show();
        });

        // Reject button click
        holder.rejectButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Rejected holiday request for " + request.getFirstName(), Toast.LENGTH_SHORT).show();
        });
    }



    @Override
    public int getItemCount() {
        return holidayRequests.size();
    }

    public static class HolidayRequestViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView employeeName, holidayStart, holidayEnd;
        Button acceptButton, rejectButton;

        public HolidayRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            employeeName = itemView.findViewById(R.id.employee_name);
            holidayStart = itemView.findViewById(R.id.holiday_start);
            holidayEnd = itemView.findViewById(R.id.holiday_end);
            acceptButton = itemView.findViewById(R.id.button_accept);
            rejectButton = itemView.findViewById(R.id.button_reject);
        }
    }
}
