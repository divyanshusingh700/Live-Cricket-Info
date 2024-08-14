package com.truecodes.worldcuptv.TeamAndPlayer.AsiaCupPlayer.Players;

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
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models.TeamsSquad;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.truecodes.worldcuptv.Schedule.ScheduleAdapter;

import java.util.ArrayList;

public class AsiaCupPlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    AdManager adManager;
    Context context;
    ArrayList<TeamsSquad> squads;

    public AsiaCupPlayerAdapter(ArrayList<TeamsSquad> arrayList, Context context, Activity activity) {
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
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.textView.setText(squads.get(i).getPlayerName());
        if (!this.squads.get(i).getPlayerName().equals("")) {
            String lowerCase = this.squads.get(i).getPlayerName().replace("(Captain)", "").trim().replace("(wk)", "").trim().replace(" ", "-").toLowerCase();
            Picasso picasso = Picasso.get();
            picasso.load("https://raw.githubusercontent.com/app-developer-a/cricket_player_image/main/" + lowerCase + ".webp").into(viewHolder.imageView, new Callback() { // from class: com.digital.livecricketapp.TeamAndPlayer.AsiaCupPlayer.Players.AsiaCupPlayerAdapter.1
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
            this.imageView =   view.findViewById(R.id.homeImageViewId);
            this.textView =   view.findViewById(R.id.homeTextViewId);
            this.progressBar =   view.findViewById(R.id.progressBar);
            AsiaCupPlayerAdapter.this.adManager = new AdManager(AsiaCupPlayerAdapter.this.context, AsiaCupPlayerAdapter.this.activity);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (!AsiaCupPlayerAdapter.this.squads.get(ViewHolder.this.getAdapterPosition()).getPlayerUrl().equals("")) {
                        Intent intent = new Intent(AsiaCupPlayerAdapter.this.context, MainPlayersScreen.class);
                        intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + AsiaCupPlayerAdapter.this.squads.get(ViewHolder.this.getAdapterPosition()).getPlayerUrl());
                        intent.setFlags(268435456);
                        AsiaCupPlayerAdapter.this.context.startActivity(intent);
                        AsiaCupPlayerAdapter.this.adManager.showInterstitial();
                        return;
                    }
                    Toast.makeText(AsiaCupPlayerAdapter.this.context, "Sorry, Player profile not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}