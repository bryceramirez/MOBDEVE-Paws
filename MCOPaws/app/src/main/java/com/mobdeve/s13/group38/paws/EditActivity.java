package com.mobdeve.s13.group38.paws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.initComponents();
    }

    private void initComponents(){
        this.btnEdit = findViewById(R.id.btn_edit);

        this.btnEdit.setOnClickListener(view->{
            Intent i = new Intent(EditActivity.this, ProfileActivity.class);
            startActivity(i);
            finish();
        });
    }
}