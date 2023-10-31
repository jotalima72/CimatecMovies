package com.example.cimatecmovies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cimatecmovies.R;
import com.example.cimatecmovies.model.Movie;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Criar_Playlist extends AppCompatActivity {

    private EditText NomeMovie, AnoFilme;
    private Button FinalButton, AddButton;
    ArrayList<Movie> movies = new ArrayList<>();
    DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_playlist);

        NomeMovie = findViewById(R.id.NomeFilme);
        AnoFilme = findViewById(R.id.AnoFilme);
        FinalButton = findViewById(R.id.finalButton);
        AddButton = findViewById(R.id.addButton);
        Bundle bundle = getIntent().getExtras();
        String myRA = "";
        String nome = "";
        movies = new ArrayList<>();

        if(bundle.getString("myRA")!=null){
            myRA = bundle.getString("myRA");
        }
        if(bundle.getString("Nome")!=null){
            nome = bundle.getString("Nome");
        }
        final String user = myRA;
        final String userNome = nome;


        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.child(user).child("movies").removeValue();
                String nomeMovie = NomeMovie.getText().toString();
                String anoMovie = AnoFilme.getText().toString();
                if(!checkText(nomeMovie, anoMovie, true)) return;

                int ano = Integer.parseInt(anoMovie);
                Movie movie = new Movie(nomeMovie, ano);
                movies.add(movie);
                NomeMovie.setText("");
                AnoFilme.setText("");
                Toast.makeText(getApplicationContext(), "Filme adicionado com sucesso", Toast.LENGTH_SHORT).show();
                if(movies.size() > 3){
                    AddButton.setVisibility(View.GONE);
                    NomeMovie.setEnabled(false);
                    AnoFilme.setEnabled(false);
                }
            }
        });

        FinalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movies.size() >0) {
                    String nomeMovie = NomeMovie.getText().toString();
                    String anoMovie = AnoFilme.getText().toString();
                    if(!nomeMovie.isEmpty() || !anoMovie.isEmpty()) {
                        if (!checkText(nomeMovie, anoMovie, true)) return;
                        int ano = Integer.parseInt(anoMovie);
                        Movie movie = new Movie(nomeMovie, ano);
                        movies.add(movie);
                    }

                    data.child(user).child("movies").setValue(movies);
                    Toast.makeText(getApplicationContext(), "Playlist salva", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Playlist_lists.class);
                    intent.putExtra("myRA", user);
                    intent.putExtra("Nome", userNome);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Adicione um filme", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkText(String nomeMovie, String anoMovie, boolean toast){
        if(nomeMovie.isEmpty() || anoMovie.isEmpty()) {
            if(toast) Toast.makeText(getApplicationContext(), "Insira o Nome e o ano de um filme", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!anoMovie.matches("[0-9]*")) {
            if(toast) Toast.makeText(getApplicationContext(), "Escreva o Ano como um número", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}