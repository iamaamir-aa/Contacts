package com.example.contacts.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.contacts.ContactsList;
import com.example.contacts.ContactsMessagesHistory;

public class ViewPagerMainAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 2;
    public ViewPagerMainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // Create a fragment based on the position
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ContactsList();
            case 1:
                return new ContactsMessagesHistory();
        }
        return null;
    }
    // Get the number of pages in the ViewPager
    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }


}
