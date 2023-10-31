package com.example.cimatecmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private TextView nomeView;

    private Button addPlaylistButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_lists);
        Bundle bundle = getIntent().getExtras();
        String myRA = "";
        String nome = "";
        if(bundle.getString("myRA")!=null){
            myRA = bundle.getString("myRA");
        }
        if(bundle.getString("Nome")!=null){
            nome = bundle.getString("Nome");
        }
        final String myRaFinal = myRA;
        final String nomeFinal = nome;
        playlistsRV = findViewById(R.id.playlistsRV);
        nomeView = findViewById(R.id.nomeView);
        addPlaylistButton = findViewById(R.id.addPlaylistButton);
        nomeView.setText(nome);

        addPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Criar_Playlist.class);
                intent.putExtra("myRA", myRaFinal);
                intent.putExtra("Nome", nomeFinal);
                startActivity(intent);
            }
        });
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