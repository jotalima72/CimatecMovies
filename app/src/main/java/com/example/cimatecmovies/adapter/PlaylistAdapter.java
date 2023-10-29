package com.example.cimatecmovies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cimatecmovies.R;
import com.example.cimatecmovies.model.Movie;
import com.example.cimatecmovies.model.Playlist;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.MyViewHolder> {
    private List<Playlist> playlists;

    private String myRA;
    private Playlist myPlaylist;

    public PlaylistAdapter(List<Playlist> lista, String myRA){
        this.playlists = lista;
        this.myRA = myRA;
        playlists.forEach(p -> {
            if (p.getId().equals(myRA)){
                this.myPlaylist = p;
            }
        });
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item_list, parent, false);
        return new MyViewHolder(ItemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Playlist playlist = playlists.get(position);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("users");

        holder.creatorsName.setText(playlist.getCreatorsName());
        holder.likes.setText(playlist.getLikes() + " curtidas");
        holder.moviesRV.setText(playlist.getMovies());
        if(myPlaylist.getLikedPlaylists().contains(playlist.getId())){
            holder.likeButton.setVisibility(View.GONE);
            holder.likedButton.setVisibility(View.VISIBLE);
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.child(playlist.getId()).child("likes").setValue(playlist.getLikes()+1);
                myPlaylist.setLikedPlaylists(playlist.getId());
                data.child(myRA).child("likedPlaylists").setValue(myPlaylist.getLikedPlaylists());
            }
        });
        holder.likedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.child(playlist.getId()).child("likes").setValue(playlist.getLikes()-1);
                myPlaylist.removeLikedPlaylists(playlist.getId());
                data.child(myRA).child("likedPlaylists").setValue(myPlaylist.getLikedPlaylists());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.playlists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView creatorsName, likes, moviesRV;
        private ImageButton likeButton, likedButton;
        private ImageView im;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            creatorsName = itemView.findViewById(R.id.title);
            likes = itemView.findViewById(R.id.likes);
            moviesRV = itemView.findViewById(R.id.moviesList);
            likeButton = itemView.findViewById(R.id.likeButton);
            likedButton = itemView.findViewById(R.id.likedButton);
        }
    }
}


