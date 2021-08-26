package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;
    private ImageButton ibSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.initComponents();
    }

    private void initComponents(){
        this.ibHome = findViewById(R.id.btn_home_home);
        this.ibAdd = findViewById(R.id.btn_add_home);
        this.ibProfile = findViewById(R.id.btn_profile_home);
        this.ibSearch = findViewById(R.id.btn_home_search);

        this.ibHome.setOnClickListener(view->{
            Intent i = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibAdd.setOnClickListener(view->{
            Intent i = new Intent(HomeActivity.this, PostDetailsActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(i);
        });

        this.ibSearch.setOnClickListener(view->{
            Intent i = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(i);
        });
    }
}