package com.example.practice2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<Notification> notificationList;

    public NotificationAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);

        // Set common fields
        holder.title.setText(notification.getTitle());
        holder.message.setText(notification.getMessage());
        holder.timestamp.setText(notification.getTimestamp());

        // Different behavior based on notification type
        holder.itemView.setOnClickListener(v -> {
            FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (notification.getType() == Notification.HOLIDAY_REQUEST) {
                // Navigate to HolidayRequestActionFragment for holiday requests
                transaction.replace(R.id.fragment_container, new HolidayRequestAction());
            } else if (notification.getType() == Notification.PROFILE_UPDATE) {
                // Navigate to ProfileDetailsFragment for profile updates
                transaction.replace(R.id.fragment_container, new ProfileDetailsFragment());
            }

            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView title, message, timestamp;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notification_title);
            message = itemView.findViewById(R.id.notification_message);
            timestamp = itemView.findViewById(R.id.notification_timestamp);
        }
    }
}
