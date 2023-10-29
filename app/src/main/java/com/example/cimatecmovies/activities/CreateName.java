package com.example.cimatecmovies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cimatecmovies.R;
import com.example.cimatecmovies.model.Playlist;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateName extends AppCompatActivity {
    private EditText nameText;
    private Button NameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_name);
        nameText = findViewById(R.id.nomeText);
        NameButton = findViewById(R.id.nomeButton);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");
        NameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nameText.getText().toString();
                if(!nome.isEmpty()){
                    Bundle bundle = getIntent().getExtras();
                    String myRA = "";
                    if(bundle.getString("myRA")!=null){
                        myRA = bundle.getString("myRA");
                    }
                    Playlist playlist = new Playlist(nome);
                    data.child(myRA).setValue(playlist);
                    Intent intent = new Intent(getApplicationContext(), Playlist_lists.class);
                    intent.putExtra("myRA", myRA);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Insira o seu Nome de usuário", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}