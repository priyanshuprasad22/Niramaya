package com.example.niramaya_health;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_professional extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;

    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional2);

        tabLayout=findViewById(R.id.tool_bar);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Patients"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming\nSessions"));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPager viewPager = findViewById(R.id.view_pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        setNavigation();


    }

    public void setNavigation() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.nav_account)
        {
            Intent intent=new Intent(Activity_professional.this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.nav_logout)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(Activity_professional.this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.nav_complete_profile)
        {
            Intent intent=new Intent(Activity_professional.this,Profile_doctor.class);
            startActivity(intent);
            return true;
        }

        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}