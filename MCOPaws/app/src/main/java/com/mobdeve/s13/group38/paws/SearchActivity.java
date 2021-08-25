package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {
    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initComponents();
    }

    private void initComponents(){
        this.ibHome = findViewById(R.id.btn_home_search);
        this.ibAdd = findViewById(R.id.btn_add_search);
        this.ibProfile = findViewById(R.id.btn_profile_search);

        this.ibHome.setOnClickListener(view->{
            Intent i = new Intent(SearchActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibAdd.setOnClickListener(view->{
            Intent i = new Intent(SearchActivity.this, PostDetailsActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(i);
        });
    }
}