package com.tranhuuphuoc.tranhuuphuoc_2123110236;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerOptionAdapter extends RecyclerView.Adapter<CustomerOptionAdapter.ViewHolder> {

    private final List<String> options;
    private final OnOptionClickListener listener;

    public interface OnOptionClickListener {
        void onOptionClick(String option);
    }

    public CustomerOptionAdapter(List<String> options, OnOptionClickListener listener) {
        this.options = options;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String option = options.get(position);
        holder.txtOption.setText(option);
        holder.itemView.setOnClickListener(v -> listener.onOptionClick(option));
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOption = itemView.findViewById(R.id.txtOption);
        }
    }
}
