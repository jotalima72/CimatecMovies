package com.example.cimatecmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cimatecmovies.model.Movie;
import com.example.cimatecmovies.model.Playlist;

import java.util.ArrayList;
import java.util.List;

import com.example.cimatecmovies.R;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.MyViewHolder> {
    private List<Playlist> playlists;

    public PlaylistAdapter(List<Playlist> lista){
        this.playlists = lista;
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

        holder.creatorsName.setText(playlist.getCreatorsName());
        holder.likes.setText(playlist.getLikes() + " curtidas");
        ArrayAdapter<Movie> adapter = new ArrayAdapter<>(
                holder.moviesRV.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                playlist.getMovies()
        );
        holder.moviesRV.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return this.playlists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView creatorsName, likes;
        private ListView moviesRV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            creatorsName = itemView.findViewById(R.id.title);
            likes = itemView.findViewById(R.id.likes);
            moviesRV = itemView.findViewById(R.id.moviesList);
        }
    }
}


