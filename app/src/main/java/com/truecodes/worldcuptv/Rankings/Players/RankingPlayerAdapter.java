package com.truecodes.worldcuptv.Rankings.Players;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.squareup.picasso.Picasso;
import com.truecodes.worldcuptv.MainPlayers.Adapters.BatsmanSummaryAdapter;

import java.util.ArrayList;


public class RankingPlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<RankingPlayer> rankingPlayers;

    public RankingPlayerAdapter(ArrayList<RankingPlayer> arrayList, Context context) {
        this.rankingPlayers = arrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_ranking_player, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.playerName.setText(rankingPlayers.get(i).getTeamName());
        TextView textView = viewHolder.playerPosition;
        textView.setText("Rank: " + rankingPlayers.get(i).getPosition());
        TextView textView2 = viewHolder.playerRatting;
        textView2.setText("Rating: " + rankingPlayers.get(i).getRatting());
        Picasso.get().load(rankingPlayers.get(i).getTeamImage()).into(viewHolder.playerImage);
    }

    @Override
    public int getItemCount() {
        return rankingPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView playerImage;
        TextView playerName;
        TextView playerPosition;
        TextView playerRatting;

        public ViewHolder(View view) {
            super(view);
            this.playerImage =  view.findViewById(R.id.teamImage);
            this.playerName =  view.findViewById(R.id.teamName);
            this.playerRatting = view.findViewById(R.id.rating);
            this.playerPosition = view.findViewById(R.id.teamPosition);
        }
    }
}