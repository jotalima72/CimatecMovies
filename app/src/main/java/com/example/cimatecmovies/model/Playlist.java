package com.example.cimatecmovies.model;

import java.util.ArrayList;

public class Playlist {
    public Playlist(String creatorsName) {
        CreatorsName = creatorsName;
    }

    private String CreatorsName;
    private ArrayList<Movie> movies = new ArrayList<>();
    private int likes = 0;

    public String getCreatorsName() {
        return CreatorsName;
    }

    public void setCreatorsName(String creatorsName) {
        CreatorsName = creatorsName;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
