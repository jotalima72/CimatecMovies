package com.example.cimatecmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cimatecmovies.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

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
                OnCompleteListener<DataSnapshot> listener = new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        String user = "";
                        if (task.isSuccessful()){
                            DataSnapshot dataRes = task.getResult();
                            for (DataSnapshot sp : dataRes.getChildren()) {
                                String result = sp.getKey();
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
                                Intent intent = new Intent(getApplicationContext(), Playlist_lists.class);
                                intent.putExtra("myRA", user);
                                startActivity(intent);
                            }
                        }
                        else{
                            System.out.println("Task deu merda");
                        }
                    }
                };
                data.get().addOnCompleteListener(listener);
            }
        });
//        playlist.setMovies(movie);
//        ref.child("users").child("018664138").setValue(playlist);
//        getData();
    }

}



