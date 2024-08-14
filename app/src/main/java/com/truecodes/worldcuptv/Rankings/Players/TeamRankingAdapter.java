package com.truecodes.worldcuptv.Rankings.Players;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Rankings.TeamRanking;
import com.squareup.picasso.Picasso;
import com.truecodes.worldcuptv.MainPlayers.Adapters.BatsmanSummaryAdapter;

import java.util.ArrayList;

public class TeamRankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    Context context;
    ArrayList<TeamRanking> rankingModels;

    public TeamRankingAdapter(ArrayList<TeamRanking> arrayList, Context context, Activity activity) {
        this.rankingModels = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_ranking, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.teamName.setText(this.rankingModels.get(i).getTeamName());
        TextView textView = viewHolder.teamPosition;
        textView.setText("Rank: " + this.rankingModels.get(i).getPosition());
        TextView textView2 = viewHolder.teamMatch;
        textView2.setText("Matches: " + this.rankingModels.get(i).getMatches());
        TextView textView3 = viewHolder.teamPoints;
        textView3.setText("Points: " + this.rankingModels.get(i).getPoints());
        TextView textView4 = viewHolder.teamRatting;
        textView4.setText("Rating: " + this.rankingModels.get(i).getRatting());
        Picasso.get().load(this.rankingModels.get(i).getTeamImage()).into(viewHolder.teamImage);
    }

    @Override
    public int getItemCount() {
        return this.rankingModels.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView teamImage;
        TextView teamMatch;
        TextView teamName;
        TextView teamPoints;
        TextView teamPosition;
        TextView teamRatting;

        public ViewHolder(View view) {
            super(view);
            this.teamImage = view.findViewById(R.id.teamImage);
            this.teamName =view.findViewById(R.id.teamName);
            this.teamMatch = view.findViewById(R.id.teamMatch);
            this.teamPoints = view.findViewById(R.id.teamPoints);
            this.teamRatting = view.findViewById(R.id.teamRatting);
            this.teamPosition = view.findViewById(R.id.teamPosition);
        }
    }
}