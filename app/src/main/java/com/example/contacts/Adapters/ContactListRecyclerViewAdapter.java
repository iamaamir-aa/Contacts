package com.example.contacts.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.Model.Contact;
import com.example.contacts.ContactInfoActivity;
import com.example.contacts.R;

import java.util.List;

public class ContactListRecyclerViewAdapter extends RecyclerView.Adapter<ContactListRecyclerViewAdapter.ViewHolder> {

    private List<Contact> contacts;
    private Activity activity;

    public ContactListRecyclerViewAdapter(List<Contact> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data for the current position
        Contact contact = contacts.get(position);

        // Set the single letter of the contact's name
        TextView singleLetterTV = holder.itemView.findViewById(R.id.singleLetterTextView);
        singleLetterTV.setText(String.valueOf(contact.getName().charAt(0)));

        // Set the contact's name
        TextView contactTextView = holder.itemView.findViewById(R.id.contactTextView);
        contactTextView.setText(contact.getName());

        // Handle item click event
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the contact at the clicked position
                Contact clickedContact = contacts.get(holder.getAdapterPosition());

                // Start the contact info activity
                Intent intent = new Intent(activity, ContactInfoActivity.class);
                intent.putExtra("contact", clickedContact);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
