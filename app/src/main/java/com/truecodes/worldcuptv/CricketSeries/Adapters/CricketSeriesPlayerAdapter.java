package com.truecodes.worldcuptv.CricketSeries.Adapters;

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
import com.truecodes.worldcuptv.Adapters.HomeAdapters;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeriesPlayer;
import com.truecodes.worldcuptv.MainPlayers.MainPlayersScreen;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class CricketSeriesPlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    AdManager adManager;
    private Context context;
    private ArrayList<CricketSeriesPlayer> playerArrayList;

    public CricketSeriesPlayerAdapter(ArrayList<CricketSeriesPlayer> arrayList, Context context, Activity activity) {
        this.playerArrayList = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_schedule_player, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.playerName.setText(this.playerArrayList.get(i).getPlayerName());
        if (!this.playerArrayList.get(i).getPlayerName().equals("")) {
            String lowerCase = this.playerArrayList.get(i).getPlayerName().replace("(Captain)", "").trim().replace("(wk)", "").trim().replace(" ", "-").toLowerCase();
            Picasso picasso = Picasso.get();
            picasso.load("https://raw.githubusercontent.com/Applicationslab/Asiacup/main/" + lowerCase + ".webp").into(viewHolder.playerImageView, new Callback() { // from class: com.digital.livecricketapp.CricketSeries.Adapters.CricketSeriesPlayerAdapter.1
                @Override
                public void onSuccess() {
                    viewHolder.progressBar.setVisibility(8);
                }

                @Override
                public void onError(Exception exc) {
                    viewHolder.progressBar.setVisibility(8);
                    viewHolder.playerImageView.setImageResource(R.drawable.profile);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.playerArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView playerImageView;
        TextView playerName;
        ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            this.playerImageView = view.findViewById(R.id.homeImageViewId);
            this.playerName = view.findViewById(R.id.homeTextViewId);
            this.progressBar =  view.findViewById(R.id.progressBar2);
            CricketSeriesPlayerAdapter.this.adManager = new AdManager(CricketSeriesPlayerAdapter.this.context, CricketSeriesPlayerAdapter.this.activity);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (((CricketSeriesPlayer) CricketSeriesPlayerAdapter.this.playerArrayList.get(ViewHolder.this.getAdapterPosition())).getPlayerURL().equals("")) {
                        Toast.makeText(CricketSeriesPlayerAdapter.this.context, "Sorry, Player profile not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(CricketSeriesPlayerAdapter.this.context, MainPlayersScreen.class);
                    intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ( CricketSeriesPlayerAdapter.this.playerArrayList.get(ViewHolder.this.getAdapterPosition())).getPlayerURL());
                    intent.setFlags(268435456);
                    CricketSeriesPlayerAdapter.this.context.startActivity(intent);
                    CricketSeriesPlayerAdapter.this.adManager.showInterstitial();
                }
            });
        }
    }
}