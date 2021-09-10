package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class PostDetailsActivity extends AppCompatActivity {
    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        this.initComponents();
    }

    private void initComponents(){
        this.ibHome = findViewById(R.id.btn_home_post);
        this.ibAdd = findViewById(R.id.btn_add_post);
        this.ibProfile = findViewById(R.id.btn_profile_post);

        this.ibHome.setOnClickListener(view->{
            Intent i = new Intent(PostDetailsActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibAdd.setOnClickListener(view->{
            Intent i = new Intent(PostDetailsActivity.this, PostDetailsActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(PostDetailsActivity.this, ProfileActivity.class);
            startActivity(i);
        });
    }
}