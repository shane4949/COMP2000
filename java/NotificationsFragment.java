package com.example.practice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Bind RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy notifications
        List<Notification> notifications = getDummyNotifications();

        // Set up Adapter
        NotificationAdapter adapter = new NotificationAdapter(notifications);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Notification> getDummyNotifications() {
        List<Notification> notifications = new ArrayList<>();

        // Holiday Request Notification
        notifications.add(new Notification(
                Notification.HOLIDAY_REQUEST, // Type
                "Holiday Request", // Title
                "John Doe has requested a holiday.", // Message
                "Just now", // Timestamp
                false // Is Read
        ));

        // Profile Update Notification
        notifications.add(new Notification(
                Notification.PROFILE_UPDATE, // Type
                "Profile Updated", // Title
                "Your profile has been updated.", // Message
                "10 minutes ago", // Timestamp
                true // Is Read
        ));

        return notifications;
    }

}
