package com.example.cimatecmovies.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cimatecmovies.R;
import com.example.cimatecmovies.adapter.PlaylistAdapter;
import com.example.cimatecmovies.model.Movie;
import com.example.cimatecmovies.model.Playlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Playlist_lists extends AppCompatActivity {
    DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");
    private RecyclerView playlistsRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_lists);
        Bundle bundle = getIntent().getExtras();
        String myRA = "";
        if(bundle.getString("myRA")!=null){
            myRA = bundle.getString("myRA");
        }
        else{
            System.out.println("chamar tela de nome");
        }
        final String myRaFinal = myRA;
        playlistsRV = findViewById(R.id.playlistsRV);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Playlist> lista = new ArrayList<>();
                for (DataSnapshot sp : snapshot.getChildren()) {
                    Playlist temp = new Playlist();
                    for (DataSnapshot snapMovie : sp.child("movies").getChildren()) {
                        temp.setMovies(snapMovie.getValue(Movie.class));
                    }for (DataSnapshot snapLiked : sp.child("likedPlaylists").getChildren()) {
                        temp.setLikedPlaylists(snapLiked.getValue(String.class));
                    }
                    temp.setId(sp.getKey());
                    temp.setCreatorsName(sp.child("creatorsName").getValue(String.class));
                    temp.setLikes(sp.child("likes").getValue(int.class));
                    lista.add(temp);
                }
                loadPlaylists(lista, myRaFinal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadPlaylists(List<Playlist> lista, String myRA){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        playlistsRV.setLayoutManager(manager);
        playlistsRV.addItemDecoration(new DividerItemDecoration(
                getApplicationContext(),
                LinearLayout.VERTICAL)
        );
        PlaylistAdapter adapter = new PlaylistAdapter(lista, myRA);
        playlistsRV.setAdapter(adapter);
    }
}