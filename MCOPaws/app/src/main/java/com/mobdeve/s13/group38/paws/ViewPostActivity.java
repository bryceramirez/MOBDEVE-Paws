package com.mobdeve.s13.group38.paws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private ImageButton ibEdit;
    private LinearLayout llCaption;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private String description;
    private String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        this.initFirebase();
        this.initComponents();
        Intent i = getIntent();


        String username = i.getStringExtra("USERNAME");
        this.description = i.getStringExtra("DESCRIPTION");
//        i.putExtra("COMMENTS", currentPos.getComments());
        String likes = i.getStringExtra("LIKES");
        String time = i.getStringExtra("TIME");
        this.photo = i.getStringExtra("PHOTO");
        String user = i.getStringExtra("USER");
        String profilepic = i.getStringExtra("PROFILEPIC");

        storage = FirebaseStorage.getInstance("gs://mobdeve-paws.appspot.com");
        storageReference = storage.getReference().child("images");

        Glide.with(this).load(storageReference.child(this.photo)).into(ivPostPhoto);

        if (user.equals(mAuth.getCurrentUser().getUid()))
            ibEdit.setVisibility(View.VISIBLE);

        if(!profilepic.equals("none"))
            Glide.with(this).load(storageReference.child(profilepic)).into(ivUserImage);
        else
            ivUserImage.setImageResource(R.drawable.paw);

        tvUsername.setText(username);
        tvDatePosted.setText(time);
        tvLikes.setText(likes);
         if(!description.equals("")){
            tvCaptionDescription.setText(this.description);
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
        this.ibEdit = findViewById(R.id.ib_edit_view);

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

        this.ibEdit.setOnClickListener(view->{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(ViewPostActivity.this);
            edittext.setText(description);
            alert.setTitle("Edit your description");

            alert.setView(edittext);

            alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //What ever you want to do with the value
                    String YouEditTextValue = edittext.getText().toString().trim();
                    database.getReference(Collections.posts.name())
                            .child(photo)
                            .child("description")
                            .setValue(YouEditTextValue).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ViewPostActivity.this, "Description changed", Toast.LENGTH_SHORT).show();
                                tvCaptionDescription.setText(YouEditTextValue);
                            }
                            else{
                                Toast.makeText(ViewPostActivity.this, "Failed to change description", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });

            alert.show();
        });

        ivUserImage.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), ProfileActivity.class);

            i.putExtra("USER", user);

            view.getContext().startActivity(i);
        });

        tvUsername.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), ProfileActivity.class);

            i.putExtra("USER", user);

            view.getContext().startActivity(i);
        });
        // INITIALIZE RECYCLERVIEW HERE
    }
    private void initFirebase(){
        this.storage = FirebaseStorage.getInstance("gs://mobdeve-paws.appspot.com");
        this.storageReference = storage.getReference();
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://mobdeve-paws-default-rtdb.asia-southeast1.firebasedatabase.app/");
        this.databaseReference = database.getReference(Collections.posts.name());
    }
}