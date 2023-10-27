package com.example.cimatecmovies.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cimatecmovies.DAO.PlaylistDAO;
import com.example.cimatecmovies.R;
import com.example.cimatecmovies.model.Movie;
import com.example.cimatecmovies.model.Playlist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private EditText raText;
    private Button raButton;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        raText = findViewById(R.id.raText);
        raButton = findViewById(R.id.raButton);

        raButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ra = raText.getText().toString();
                PlaylistDAO dbPlaylists = new PlaylistDAO();
                OnCompleteListener<DataSnapshot> listener = new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String user = "";
                        if (task.isSuccessful()){
                            DataSnapshot dataRes = task.getResult();
                            for (DataSnapshot sp : dataRes.getChildren()) {
                                String result = sp.getKey();
                                System.out.println(result);
                                if(result.equals(ra)){
                                    user = result;
                                    break;
                                }
                            }
                            if(user.isEmpty()){
                                System.out.println("chamar tela de nome");
                            }
                            else {
                                System.out.println("chamar lista de playlists");
                            }
                        }


                    }
                };
                data.get().addOnCompleteListener(listener);

//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }
        });
//        playlist.setMovies(movie);
//        ref.child("users").child("018664138").setValue(playlist);
//        getData();
    }

}



