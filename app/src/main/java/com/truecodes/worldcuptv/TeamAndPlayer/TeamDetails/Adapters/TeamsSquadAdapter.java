package com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.MainPlayers.MainPlayersScreen;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleAdapter;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models.TeamsSquad;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class TeamsSquadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    AdManager adManager;
    Context context;
    private ArrayList<TeamsSquad> squads;

    public TeamsSquadAdapter(ArrayList<TeamsSquad> arrayList, Context context, Activity activity) {
        this.squads = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_team, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
         ViewHolder viewHolder = ( ViewHolder) vHolder;
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.textView.setText(this.squads.get(i).getPlayerName());
        if (!this.squads.get(i).getPlayerImage().equals("")) {
            Picasso picasso = Picasso.get();
            picasso.load("https://www.cricbuzz.com" + this.squads.get(i).getPlayerImage().replace("75x75", "192x192")).into(viewHolder.imageView, new Callback() { // from class: com.digital.livecricketapp.TeamAndPlayer.TeamsDetails.Adapters.TeamsSquadAdapter.1
                @Override
                public void onSuccess() {
                    viewHolder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception exc) {
                    viewHolder.progressBar.setVisibility(View.GONE);
                    viewHolder.imageView.setImageResource(R.drawable.profile);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.squads.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            TeamsSquadAdapter.this.adManager = new AdManager(TeamsSquadAdapter.this.context, TeamsSquadAdapter.this.activity);
            this.imageView = (ImageView) view.findViewById(R.id.homeImageViewId);
            this.textView = (TextView) view.findViewById(R.id.homeTextViewId);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (!((TeamsSquad) TeamsSquadAdapter.this.squads.get(ViewHolder.this.getAdapterPosition())).getPlayerUrl().equals("")) {
                        Intent intent = new Intent(TeamsSquadAdapter.this.context, MainPlayersScreen.class);
                        intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((TeamsSquad) TeamsSquadAdapter.this.squads.get(ViewHolder.this.getAdapterPosition())).getPlayerUrl());
                        intent.setFlags(268435456);
                        TeamsSquadAdapter.this.context.startActivity(intent);
                        TeamsSquadAdapter.this.adManager.showInterstitial();
                        return;
                    }
                    Toast.makeText(TeamsSquadAdapter.this.context, "Sorry, Player profile not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}