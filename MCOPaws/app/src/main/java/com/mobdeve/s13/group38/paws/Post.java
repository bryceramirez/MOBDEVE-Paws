package com.mobdeve.s13.group38.paws;

import java.util.ArrayList;

public class Post {
    private int photo;
    private ArrayList<String> likes;
    private ArrayList<String> comments;
    private String datePosted;
    private String description;

    public Post(int photo, ArrayList<String> likes, ArrayList<String> comments, String datePosted, String description){
        this.photo = photo;
        this.likes = likes;
        this.comments = comments;
        this.datePosted = datePosted;
        this.description = description;
    }

    public int getPhoto() {
        return this.photo;
    }

    public ArrayList<String> getLikes(){
        return this.likes;
    }

    public ArrayList<String> getComments(){
        return this.comments;
    }

    public String getDatePosted(){
        return this.datePosted;
    }

    public String getDescription(){
        return this.description;
    }
}
