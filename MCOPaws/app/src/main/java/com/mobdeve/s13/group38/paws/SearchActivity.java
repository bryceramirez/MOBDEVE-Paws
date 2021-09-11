package com.mobdeve.s13.group38.paws;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    private ImageButton ibHome;
    private ImageButton ibAdd;
    private ImageButton ibProfile;
    private ArrayList<Post> posts = new ArrayList<>();
    private PostSearchAdapter postSearchAdapter;
    private RecyclerView rvPostSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initComponents();
        this.initFirebase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        posts = new ArrayList<>();
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
            Intent i = new Intent(SearchActivity.this, EditImageActivity.class);
            startActivity(i);
        });

        this.ibProfile.setOnClickListener(view->{
            Intent i = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(i);
        });
    }

    private void initFirebase(){
        DatabaseReference reference = FirebaseDatabase.getInstance("https://mobdeve-paws-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        reference.child(Collections.posts.name()).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                posts = new ArrayList<>();
                for(DataSnapshot ds: snapshot.getChildren()){
                    String user = ds.child("user").getValue().toString();

                    String photo = ds.child("photo").getValue().toString();

                    ArrayList<String> likes = new ArrayList<String>();
                    for (DataSnapshot dsLikes: ds.child("likes").getChildren())
                        likes.add(dsLikes.getValue().toString());
                    ArrayList<String> comments = new ArrayList<String>();
                    for (DataSnapshot dsComments: ds.child("comments").getChildren())
                        comments.add(dsComments.getValue().toString());

                    String datePosted = ds.child("datePosted").getValue().toString();

                    String description = ds.child("description").getValue().toString();
                    posts.add(new Post(user, photo, likes, comments, datePosted, description));

                }
                System.out.println(posts);

                Comparator<Post> compareById = (Post o1, Post o2) ->  o2.getLikes().size() - o1.getLikes().size();

                posts.sort(compareById);

                ArrayList<Post> trending_posts = new ArrayList<>();
                for(int i =0; i<3 ; i++)
                    trending_posts.add(posts.get(i));

//                System.out.println(trending_posts);

                rvPostSearch = findViewById(R.id.rv_posts_search);
                rvPostSearch.setLayoutManager(new GridLayoutManager(SearchActivity.this, 3));

                postSearchAdapter = new PostSearchAdapter(trending_posts);
                rvPostSearch.setAdapter(postSearchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}