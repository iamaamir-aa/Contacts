package com.example.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.Adapters.OtpSendHistoryRecyclerViewAdapter;
import com.example.contacts.Database.MyDatabaseHelper;
import com.example.contacts.Model.ContactHistory;

import java.util.ArrayList;

public class ContactsMessagesHistory extends Fragment {

    private RecyclerView otpSendHistoryRecyclerView;

    public ContactsMessagesHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_messages_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ArrayList<ContactHistory> contactHistories = getContactsList();

        otpSendHistoryRecyclerView = view.findViewById(R.id.otpHistoryRecyclerView);
        otpSendHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OtpSendHistoryRecyclerViewAdapter otpSendHistoryRecyclerViewAdapter = new OtpSendHistoryRecyclerViewAdapter(contactHistories);
        otpSendHistoryRecyclerView.setAdapter(otpSendHistoryRecyclerViewAdapter);

    }

    ArrayList<ContactHistory> getContactsList() {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM contacts ORDER BY timestamp DESC;";
        Cursor cursor = db.rawQuery(query, null);

        int idIndex = cursor.getColumnIndexOrThrow("id");
        int nameIndex = cursor.getColumnIndexOrThrow("name");
        int phoneIndex = cursor.getColumnIndexOrThrow("contact");
        int otpIndex = cursor.getColumnIndexOrThrow("otp");
        int timestampIndex = cursor.getColumnIndexOrThrow("timestamp");
        ArrayList<ContactHistory> contacts = new ArrayList<>();

        // Iterate through the cursor to retrieve the data
        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String phone = cursor.getString(phoneIndex);
                String otp = cursor.getString(otpIndex);
                String timestamp = cursor.getString(timestampIndex);

                contacts.add(new ContactHistory(name, phone, timestamp, otp));

            } while (cursor.moveToNext());
        }

// Close the cursor and database connection
        cursor.close();
        return contacts;
    }


}