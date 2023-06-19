package com.example.contacts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacts.Adapters.ContactListRecyclerViewAdapter;
import com.example.contacts.Model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactsList extends Fragment {
    public ContactsList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String jsonString ="[\n" +
                "  {\n" +
                "    \"firstName\": \"Aamir\",\n" +
                "    \"lastName\": \"Ansari (For Test)\",\n" +
                "    \"phoneNumber\": \"+918126275250\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"AAAAA\",\n" +
                "    \"lastName\": \"(Your provided number)\",\n" +
                "    \"phoneNumber\": \"+919810153260\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"David\",\n" +
                "    \"lastName\": \"Johnson\",\n" +
                "    \"phoneNumber\": \"555-9876\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Emily\",\n" +
                "    \"lastName\": \"Davis\",\n" +
                "    \"phoneNumber\": \"555-2468\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Michael\",\n" +
                "    \"lastName\": \"Brown\",\n" +
                "    \"phoneNumber\": \"555-1357\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Jessica\",\n" +
                "    \"lastName\": \"Miller\",\n" +
                "    \"phoneNumber\": \"555-3698\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Daniel\",\n" +
                "    \"lastName\": \"Wilson\",\n" +
                "    \"phoneNumber\": \"555-7890\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Sophia\",\n" +
                "    \"lastName\": \"Taylor\",\n" +
                "    \"phoneNumber\": \"555-2345\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Matthew\",\n" +
                "    \"lastName\": \"Anderson\",\n" +
                "    \"phoneNumber\": \"555-6789\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Olivia\",\n" +
                "    \"lastName\": \"Thomas\",\n" +
                "    \"phoneNumber\": \"555-9087\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Andrew\",\n" +
                "    \"lastName\": \"Jackson\",\n" +
                "    \"phoneNumber\": \"555-4576\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Ava\",\n" +
                "    \"lastName\": \"White\",\n" +
                "    \"phoneNumber\": \"555-7823\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"William\",\n" +
                "    \"lastName\": \"Harris\",\n" +
                "    \"phoneNumber\": \"555-4398\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Isabella\",\n" +
                "    \"lastName\": \"Martin\",\n" +
                "    \"phoneNumber\": \"555-7820\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Joseph\",\n" +
                "    \"lastName\": \"Thompson\",\n" +
                "    \"phoneNumber\": \"555-3619\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Mia\",\n" +
                "    \"lastName\": \"Garcia\",\n" +
                "    \"phoneNumber\": \"555-1987\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"James\",\n" +
                "    \"lastName\": \"Clark\",\n" +
                "    \"phoneNumber\": \"555-5421\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Charlotte\",\n" +
                "    \"lastName\": \"Lewis\",\n" +
                "    \"phoneNumber\": \"555-2798\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Benjamin\",\n" +
                "    \"lastName\": \"Lee\",\n" +
                "    \"phoneNumber\": \"555-6457\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"firstName\": \"Amelia\",\n" +
                "    \"lastName\": \"Lopez\",\n" +
                "    \"phoneNumber\": \"555-8036\"\n" +
                "  }\n" +
                "]\n";
        List<Contact> contacts=new ArrayList<>();



        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                // Get the current JSON object
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extract the values from the JSON object
                String firstName = jsonObject.getString("firstName");
                String lastName = jsonObject.getString("lastName");
                String phoneNumber = jsonObject.getString("phoneNumber");
                String fullName=firstName+" "+lastName;
                Contact contact=new Contact(fullName,phoneNumber);
                contacts.add(contact);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(contacts);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ContactListRecyclerViewAdapter contactListRecyclerViewAdapter=new ContactListRecyclerViewAdapter(contacts,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(contactListRecyclerViewAdapter);

    }
}