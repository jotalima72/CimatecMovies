package com.example.cimatecmovies.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cimatecmovies.R;
import com.example.cimatecmovies.model.Movie;
import com.example.cimatecmovies.model.Playlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    Playlist playlist = new Playlist("antonho");
    Movie movie = new Movie("O voo do passaro que nao sabia voar", 1925);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playlist.setMovies(movie);
        ref.child("users").child("018664138").setValue(playlist);
        getData();
    }
    public void getData(){
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Playlist> p = new ArrayList<>();
                for (DataSnapshot sp: snapshot.getChildren()) {
                Playlist temp = new Playlist();
                    for(DataSnapshot snapMovie : sp.child("movies").getChildren()){
                        temp.setMovies(snapMovie.getValue(Movie.class));
                    }
                    temp.setCreatorsName(sp.child("creatorsName").getValue(String.class));
                    temp.setLikes(sp.child("likes").getValue(int.class));
                    p.add(temp);
                }
                p.forEach(pl -> System.out.println(pl.toString()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
