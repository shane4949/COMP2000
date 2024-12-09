package com.example.practice2;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class InputFieldAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_TITLE = 0;
    private static final int VIEW_TYPE_FIELD = 1;
    private static final int VIEW_TYPE_DIVIDER = 2;

    private final List<InputField> items;

    public InputFieldAdapter(List<InputField> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isTitle()) {
            return VIEW_TYPE_TITLE;
        } else if (items.get(position).isDivider()) {
            return VIEW_TYPE_DIVIDER;
        } else {
            return VIEW_TYPE_FIELD;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TITLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_title, parent, false);
            return new TitleViewHolder(view);
        } else if (viewType == VIEW_TYPE_DIVIDER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_divider, parent, false);
            return new DividerViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_input_field, parent, false);
            return new FieldViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InputField item = items.get(position);

        if (holder instanceof FieldViewHolder) {
            FieldViewHolder fieldHolder = (FieldViewHolder) holder;
            fieldHolder.inputField.setHint(item.getHint()); // Set hint dynamically

            // Configure input type based on the InputField's type
            switch (item.getInputType()) {
                case "phone":
                    fieldHolder.inputField.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
                    break;

                case "date":
                    fieldHolder.inputField.setFocusable(false);
                    fieldHolder.inputField.setOnClickListener(v -> {
                        // Show DatePickerDialog for date fields
                        Calendar calendar = Calendar.getInstance();
                        DatePickerDialog datePicker = new DatePickerDialog(
                                fieldHolder.itemView.getContext(),
                                (view, year, month, dayOfMonth) -> {
                                    String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                                    fieldHolder.inputField.setText(formattedDate);
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                        datePicker.show();
                    });
                    break;

                case "numberDecimal":
                    fieldHolder.inputField.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    break;

                default: // For regular text fields
                    fieldHolder.inputField.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
                    break;
            }
        } else if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).title.setText(item.getName()); // Set title text for titles
        }
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.section_title);
        }
    }

    static class FieldViewHolder extends RecyclerView.ViewHolder {
        EditText inputField;

        public FieldViewHolder(@NonNull View itemView) {
            super(itemView);
            inputField = itemView.findViewById(R.id.input_field);
        }
    }

    static class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
