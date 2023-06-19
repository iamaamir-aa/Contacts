package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.contacts.Adapters.ViewPagerMainAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
TabLayout tab_layout;
ViewPager2 view_pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_layout=findViewById(R.id.tab_layout);
        view_pager=findViewById(R.id.view_pager);


        ViewPagerMainAdapter viewPagerMainAdapter=new ViewPagerMainAdapter(this);

        view_pager.setAdapter(viewPagerMainAdapter);


        new TabLayoutMediator(tab_layout, view_pager,
                (tab, position) -> {
                    // Set the tab titles manually based on the position of the pages
                    if (position == 0) {
                        tab.setText("Contacts");
                    } else if (position == 1) {
                        tab.setText("History");
                    }
                }
        ).attach();





    }
}