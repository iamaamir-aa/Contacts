package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.contacts.Model.Contact;

public class ContactInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        // Get the contact object from the intent
        Contact contact = getIntent().getParcelableExtra("contact");

        // Set the contact's name
        TextView contactName = findViewById(R.id.contactInfoNameTextView);
        contactName.setText(contact.getName());

        // Set the contact's number
        TextView contactNum = findViewById(R.id.contactInfoNumTextView);
        contactNum.setText(contact.getNumber());

        // Handle "Send Message" button click
        Button sendMessageButton = findViewById(R.id.send_message_button);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the SendNewMessage activity
                Intent intent = new Intent(ContactInfoActivity.this, SendNewMessage.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });
    }
}
