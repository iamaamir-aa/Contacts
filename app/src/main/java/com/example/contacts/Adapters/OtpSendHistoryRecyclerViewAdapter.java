package com.example.contacts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.Model.ContactHistory;
import com.example.contacts.R;

import java.util.ArrayList;

public class OtpSendHistoryRecyclerViewAdapter extends RecyclerView.Adapter<OtpSendHistoryRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ContactHistory> contactHistories;

    public OtpSendHistoryRecyclerViewAdapter(ArrayList<ContactHistory> contactHistories) {
        this.contactHistories = contactHistories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_send_history, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data for the current position
        ContactHistory contactHistory = contactHistories.get(position);

        // Set the full name
        TextView fullName = holder.itemView.findViewById(R.id.fullnameTextView);
        fullName.setText(contactHistory.getName());

        // Set the sent timestamp
        TextView sentAt = holder.itemView.findViewById(R.id.sentTextView);
        sentAt.setText("Sent: " + contactHistory.getTimestamp());

        // Set the OTP
        TextView otp = holder.itemView.findViewById(R.id.otpTextView);
        otp.setText("OTP: " + contactHistory.getOtp());
    }

    @Override
    public int getItemCount() {
        return contactHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
