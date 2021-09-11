package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewPostActivity extends AppCompatActivity {
    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;
    private String user;

    private ImageView ivUserImage;
    private TextView tvUsername;
    private TextView tvDatePosted;
    private ImageView ivPostPhoto;
    private TextView tvLikes;
    private TextView tvCaptionUsername;
    private TextView tvCaptionDescription;
    private LinearLayout llCaption;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        this.initComponents();
        Intent i = getIntent();


        String username = i.getStringExtra("USERNAME");
        String description = i.getStringExtra("DESCRIPTION");
//        i.putExtra("COMMENTS", currentPos.getComments());
        String likes = i.getStringExtra("LIKES");
        String time = i.getStringExtra("TIME");
        String photo = i.getStringExtra("PHOTO");

        storage = FirebaseStorage.getInstance("gs://mobdeve-paws.appspot.com");
        storageReference = storage.getReference().child("images");

        Glide.with(this).load(storageReference.child(photo)).into(ivPostPhoto);

        ivUserImage.setImageResource(R.drawable.seal);
        tvUsername.setText(username);
        tvDatePosted.setText(time);
        tvLikes.setText(likes);
         if(!description.equals("")){
            tvCaptionDescription.setText(description);
            tvCaptionUsername.setText(username);
         }
         else{
            llCaption.setVisibility(View.GONE);
         }
    }

    private void initComponents(){
        this.ibHome = findViewById(R.id.btn_home_view);
        this.ibAdd = findViewById(R.id.btn_add_view);
        this.ibProfile = findViewById(R.id.btn_profile_view);

        this.ivUserImage = findViewById(R.id.iv_home_user_image_view);
        this.tvUsername = findViewById(R.id.tv_post_username_view);
        this.tvDatePosted = findViewById(R.id.tv_post_time_view);
        this.ivPostPhoto = findViewById(R.id.iv_post_photo_view);
        this.tvLikes = findViewById(R.id.tv_post_like_view);
        this.tvCaptionUsername = findViewById(R.id.tv_post_caption_username_view);
        this.tvCaptionDescription = findViewById(R.id.tv_post_caption_view);
        this.llCaption = findViewById(R.id.ll_caption_view);

        this.ibHome.setOnClickListener(view->{
            Intent i = new Intent(ViewPostActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibAdd.setOnClickListener(view->{
            Intent i = new Intent(ViewPostActivity.this, EditImageActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(ViewPostActivity.this, ProfileActivity.class);
            startActivity(i);
        });

        // INITIALIZE RECYCLERVIEW HERE
    }
}