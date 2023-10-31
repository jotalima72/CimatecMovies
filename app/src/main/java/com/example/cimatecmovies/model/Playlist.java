package com.example.cimatecmovies.model;

import java.util.ArrayList;

public class Playlist {
    public Playlist() {
    }

    @Override
    public String toString() {
        String mvs = "";
        for ( Movie movie: movies
             ) {
            mvs = movie.toString() + "\n";
        }
        return "id:" + id  +
                "\nPlaylist de " + CreatorsName +
                ", likes=" + likes +
                "\nMovies: \n" +
                mvs
                ;
    }

    public Playlist(String creatorsName) {
        CreatorsName = creatorsName;
        likes = 0;
    }
    private String id;
    private String CreatorsName;
    private ArrayList<Movie> movies = new ArrayList<>();
    private int likes = 0;

    private ArrayList<String> likedPlaylists = new ArrayList<>();

    public ArrayList<String> getLikedPlaylists() {
        return likedPlaylists;
    }

    public void setLikedPlaylists(String likedPlaylist) {
        if(this.likedPlaylists == null){
            this.likedPlaylists = new ArrayList<>();
        }
        this.likedPlaylists.add(likedPlaylist);
    }

    public void removeLikedPlaylists(String dislikedPlaylist){
        this.likedPlaylists.remove(dislikedPlaylist);
    }

    public String getCreatorsName() {
        return CreatorsName;
    }

    public void setCreatorsName(String creatorsName) {
        CreatorsName = creatorsName;
    }

    public String getMovies() {
        String linhaMovies = "";
        for(Movie mov: this.movies) {
            linhaMovies += mov.toString();
        }
        return linhaMovies;
    }

    public int countMovies(){
        return this.movies.size();
    }

    public void setMovies(Movie movie) {
        this.movies.add(movie);
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
