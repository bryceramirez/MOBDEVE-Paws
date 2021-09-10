package com.mobdeve.s13.group38.paws;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.shape.ShapeBuilder;
import ja.burhanrashid52.photoeditor.shape.ShapeType;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;
import ja.burhanrashid52.photoeditor.ViewType;

public class PostDetailsActivity extends AppCompatActivity  {
    private ImageButton ibUndo;
    private ImageButton ibRedo;
    private ImageButton ibDraw;
    private ImageButton ibText;
    private ImageButton ibFilter;
    private ImageButton ibSticker;

    private ImageButton ibCancel;
    private ImageButton ibDone;
//    private ImageView ivPhoto;

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
    }

    private void initComponents(){
        this.ibUndo = findViewById(R.id.ib_undo_edit);
        this.ibRedo = findViewById(R.id.ib_redo_edit);
        this.ibDraw = findViewById(R.id.ib_draw_edit);
        this.ibText = findViewById(R.id.ib_text_edit);
        this.ibFilter = findViewById(R.id.ib_filter_edit);
        this.ibSticker = findViewById(R.id.ib_sticker_edit);
        this.ibCancel = findViewById(R.id.ib_cancel_edit);
        this.ibDone = findViewById(R.id.ib_done_edit);

        this.ibUndo.setOnClickListener(view->{
            mPhotoEditor.undo();
        });

        this.ibRedo.setOnClickListener(view->{
            mPhotoEditor.redo();
        });

        this.ibDraw.setOnClickListener(view->{
            this.mPhotoEditor.setBrushDrawingMode(true);
        });

        this.ibText.setOnClickListener(view->{
//            TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(this);
//            textEditorDialogFragment.setOnTextEditorListener((inputText, colorCode) -> {
//                final TextStyleBuilder styleBuilder = new TextStyleBuilder();
//                styleBuilder.withTextColor(colorCode);
//
//                mPhotoEditor.addText(inputText, styleBuilder);
//                mTxtCurrentTool.setText(R.string.label_text);
//            });
        });

        this.ibFilter.setOnClickListener(view->{
            mPhotoEditor.setFilterEffect(PhotoFilter.BRIGHTNESS);
        });

        this.ibSticker.setOnClickListener(view->{

        });

        this.ibCancel.setOnClickListener(view->{
            Intent i = new Intent(PostDetailsActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibDone.setOnClickListener(view->{
            Intent i = new Intent(PostDetailsActivity.this, PostDetailsEditedActivity.class);
            startActivity(i);
        });
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
            System.out.println("Image uri pic " + imageUri);

//            ivPhoto.setImageURI(imageUri);
            this.initPhotoEditor();
        }
    }


    private void initPhotoEditor(){

        this.mPhotoEditorView = findViewById(R.id.photoEditorView);
        this.mPhotoEditorView.getSource().setImageURI(imageUri);
        System.out.println("Image uri photoeditor " + imageUri);

        //Use custom font using latest support library
//        Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);

        //loading font from asset
        Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

        this.mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true)
                .setClipSourceImage(true)
//                .setDefaultTextTypeface(mTextRobotoTf)
                .setDefaultEmojiTypeface(mEmojiTypeFace)
                .build();
//        this.mPhotoEditor.setBrushDrawingMode(true);

//        mPhotoEditor.addEmoji("U+1F600");
//        this.mPhotoEditorView.setup
    }
}