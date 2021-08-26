package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ViewPostActivity extends AppCompatActivity {
    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        this.initComponents();
    }

    private void initComponents(){
        this.ibHome = findViewById(R.id.btn_home_view);
        this.ibAdd = findViewById(R.id.btn_add_view);
        this.ibProfile = findViewById(R.id.btn_profile_view);

        this.ibHome.setOnClickListener(view->{
            Intent i = new Intent(ViewPostActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibAdd.setOnClickListener(view->{
            Intent i = new Intent(ViewPostActivity.this, PostDetailsActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(ViewPostActivity.this, ProfileActivity.class);
            startActivity(i);
        });
    }
}