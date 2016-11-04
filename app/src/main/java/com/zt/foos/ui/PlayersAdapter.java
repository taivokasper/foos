package com.zt.foos.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zt.foos.model.Player;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {
    private List<Player> players = Collections.emptyList();

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null);
        return new PlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.name.setText(players.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        @BindView(android.R.id.text1)
        TextView name;

        private PlayerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
