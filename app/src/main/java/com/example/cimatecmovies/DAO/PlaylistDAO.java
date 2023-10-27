package com.example.cimatecmovies.DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cimatecmovies.model.Movie;
import com.example.cimatecmovies.model.Playlist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class PlaylistDAO {
    DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");
    ArrayList<Playlist> playlists = new ArrayList<>();

    public PlaylistDAO() {
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlists = new ArrayList<>();
                for (DataSnapshot sp : snapshot.getChildren()) {
                    Playlist temp = new Playlist();
                    for (DataSnapshot snapMovie : sp.child("movies").getChildren()) {
                        temp.setMovies(snapMovie.getValue(Movie.class));
                    }
                    temp.setCreatorsName(sp.child("creatorsName").getValue(String.class));
                    temp.setLikes(sp.child("likes").getValue(int.class));
                    playlists.add(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }

    private void postPlaylist(Playlist playlist, String ra){
        data.child(ra).setValue(playlist);
    }

    public boolean raExists(String ra){

        return false;
    }
}
