package com.mobdeve.s13.group38.paws;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostHomeAdapter extends RecyclerView.Adapter<PostHomeViewHolder>{
    private ArrayList<Post> dataPosts;
    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public PostHomeAdapter(ArrayList<Post> dataPosts){
        this.dataPosts = dataPosts;
    }

    @NonNull
    @NotNull
    @Override
    public PostHomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        initFirebase();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_user_post, parent, false);

        PostHomeViewHolder postHomeViewHolder = new PostHomeViewHolder(itemView);

        postHomeViewHolder.getLlPost().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // FOR COMMENTS
            }
        });

        postHomeViewHolder.setLikeBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LIKE

            }
        });

        return postHomeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PostHomeViewHolder holder, int position) {
        initFirebase();
        Post currentPost = this.dataPosts.get(position);

        this.mAuth = FirebaseAuth.getInstance();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://mobdeve-paws-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        reference.child(Collections.users.name()).child(currentPost.getUser()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                holder.setIvUserImage();
                String username = snapshot.child("name").getValue().toString();
                if(!currentPost.getDescription().equals("")) {
                    holder.setTvCaptionUsername(username);
                    holder.setTvDescription(currentPost.getDescription());
                }
                else{
                    holder.getLlCaption().setVisibility(View.GONE);
                }
                holder.setTvComments(currentPost.getComments().size()-1+"");
                holder.setTvLikes(currentPost.getLikes().size()-1+"");
                holder.setTvUsername(username);
                holder.setTvTime(currentPost.getDatePosted());

                DatabaseReference getImage = reference.child("images");
                storage = FirebaseStorage.getInstance("gs://mobdeve-paws.appspot.com");
                storageReference = storage.getReference().child("images");

                Glide.with(holder.getIvPostPhoto().getContext()).load(storageReference.child(currentPost.getPhoto())).into(holder.getIvPostPhoto());
            }
//                holder.setIvPostPhoto(R.drawable.seal);
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return dataPosts.size();
    }

    private void initFirebase(){

//        this.storageReference = storage.getReference();
        this.mAuth = FirebaseAuth.getInstance();
    }

}
