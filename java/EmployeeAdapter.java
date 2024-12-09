package com.example.practice2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;
    private OnEmployeeClickListener listener;

    public EmployeeAdapter(List<Employee> employees, OnEmployeeClickListener listener) {
        this.employees = employees;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.name.setText(employee.getName());
        holder.position.setText(employee.getPosition());
        holder.profileImage.setImageResource(employee.getProfileImageResId());

        holder.itemView.setOnClickListener(v -> listener.onEmployeeClick(employee));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name, position;
        ImageView profileImage;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.employee_name);
            position = itemView.findViewById(R.id.employee_position);
            profileImage = itemView.findViewById(R.id.employee_image);
        }
    }

    public interface OnEmployeeClickListener {
        void onEmployeeClick(Employee employee);
    }
}
