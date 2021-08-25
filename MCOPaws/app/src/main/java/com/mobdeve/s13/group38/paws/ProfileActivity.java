package com.mobdeve.s13.group38.paws;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class ProfileActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvAgeGender;
    private TextView tvBreed;
    private TextView tvDescription;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;

    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;
    private ImageButton ibLogout;
    private ImageButton ibEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initComponents();
        initFirebase();
    }

    private void initComponents(){
        this.tvName = findViewById(R.id.tv_name_profile);
        this.tvAgeGender = findViewById(R.id.tv_age_gender_profile);
        this.tvBreed = findViewById(R.id.tv_breed_profile);
        this.tvDescription = findViewById(R.id.tv_description_profile);

        this.ibHome = findViewById(R.id.btn_home_profile);
        this.ibAdd = findViewById(R.id.btn_add_profile);
        this.ibProfile = findViewById(R.id.btn_profile_profile);
        this.ibLogout = findViewById(R.id.ib_logout);
        this.ibEdit = findViewById(R.id.ib_edit);

        this.ibHome.setOnClickListener(view->{
            Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(i);
        });

        this.ibAdd.setOnClickListener(view->{
            Intent i = new Intent(ProfileActivity.this, PostDetailsActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(i);
        });

        this.ibEdit.setOnClickListener(view->{
            Intent i = new Intent(ProfileActivity.this, EditActivity.class);
            startActivity(i);
        });

        this.ibLogout.setOnClickListener(view->{
            this.mAuth.signOut();
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://mobdeve-paws-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(Collections.users.name());

        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String birthday = snapshot.child("birthday").getValue().toString();
                String gender = snapshot.child("gender").getValue().toString();
                String breed = snapshot.child("breed").getValue().toString();
                String description = snapshot.child("description").getValue().toString();

                try {
                    Date birthdayConv = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(birthday);
                    Calendar c = Calendar.getInstance();
                    c.setTime(birthdayConv);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int date = c.get(Calendar.DATE);
                    LocalDate l1 = LocalDate.of(year, month, date);
                    LocalDate now1 = LocalDate.now();

                    Period diff = Period.between(l1, now1);
                    System.out.println(l1+ ", "+ now1 + ", "+diff.getYears());
                    String ageGender = diff.getYears() + " years, " + gender;
                    tvAgeGender.setText(ageGender);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tvName.setText(name);
                tvBreed.setText(breed);
                tvDescription.setText(description);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}