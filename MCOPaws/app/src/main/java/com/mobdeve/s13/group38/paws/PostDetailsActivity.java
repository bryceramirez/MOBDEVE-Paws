package com.mobdeve.s13.group38.paws;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class PostDetailsActivity extends AppCompatActivity {
    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;
    private ImageView ivPhoto;

    private Uri imageUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    PhotoEditorView mPhotoEditorView;
    PhotoEditor mPhotoEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        this.initComponents();
        this.initFirebase();
        this.choosePicture();
        this.initPhotoEditor();

    }

    private void initComponents(){
//        this.ibHome = findViewById(R.id.btn_home_post);
//        this.ibAdd = findViewById(R.id.btn_add_post);
//        this.ibProfile = findViewById(R.id.btn_profile_post);
//        this.ivPhoto = findViewById(R.id.iv_photo_post);

//        this.ibHome.setOnClickListener(view->{
////            uploadPicture();
//            Intent i = new Intent(PostDetailsActivity.this, HomeActivity.class);
//            startActivity(i);
//        });
//
//        this.ibAdd.setOnClickListener(view->{
//            Intent i = new Intent(PostDetailsActivity.this, PostDetailsActivity.class);
//            startActivity(i);
//        });
//
//        this.ibProfile.setOnClickListener(view->{
//            Intent i = new Intent(PostDetailsActivity.this, ProfileActivity.class);
//            startActivity(i);
//        });
    }

    private void initFirebase(){
        storage = FirebaseStorage.getInstance("gs://mobdeve-paws.appspot.com");
        storageReference = storage.getReference();
    }

    private void choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            this.imageUri = data.getData();

//            ivPhoto.setImageURI(imageUri);
        }
    }


    private void initPhotoEditor(){
        this.mPhotoEditorView = findViewById(R.id.photoEditorView);
        this.mPhotoEditorView.getSource().setImageURI(imageUri);

        //Use custom font using latest support library
//        Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);

        //loading font from asset
//        Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

        this.mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .setClipSourceImage(true)
//                .setDefaultTextTypeface(mTextRobotoTf)
//                .setDefaultEmojiTypeface(mEmojiTypeFace)
                .build();

        this.mPhotoEditor.setBrushDrawingMode(true);

    }
}