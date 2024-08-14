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
import com.truecodes.worldcuptv.CricketSeries.Activity.CricketSeriesPlayerActivity;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeriesTeam;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CricketSeriesTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private AdManager adManager;
    private Context context;
    private ArrayList<CricketSeriesTeam> seriesTeams;

    public CricketSeriesTeamAdapter(ArrayList<CricketSeriesTeam> arrayList, Context context, Activity activity) {
        this.seriesTeams = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_series_team, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.teamName.setText(this.seriesTeams.get(i).getTeamName());
        if (!this.seriesTeams.get(i).getTeamName().equals("")) {
            String replace = this.seriesTeams.get(i).getTeamName().replace("Test Squad", "").replace("T20I Squad", "").replace("ODI Squad", "");
            Picasso picasso = Picasso.get();
            picasso.load("https://raw.githubusercontent.com/app-developer-a/round_image/main/" + replace.trim().replace(" ", "_").toLowerCase() + ".png").into(viewHolder.teamImage, new Callback() { // from class: com.digital.livecricketapp.CricketSeries.Adapters.CricketSeriesTeamAdapter.1
                @Override
                public void onSuccess() {
                    viewHolder.progressBar.setVisibility(8);
                }

                @Override
                public void onError(Exception exc) {
                    viewHolder.progressBar.setVisibility(8);
                    viewHolder.teamImage.setImageResource(R.drawable.nothing);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.seriesTeams.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private ImageView teamImage;
        private TextView teamName;

        public ViewHolder(View view) {
            super(view);
            this.teamImage = view.findViewById(R.id.teamImageView);
            this.progressBar = view.findViewById(R.id.progressBar);
            this.teamName = (TextView) view.findViewById(R.id.teamName);
            CricketSeriesTeamAdapter.this.adManager = new AdManager(CricketSeriesTeamAdapter.this.context, CricketSeriesTeamAdapter.this.activity);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if ((CricketSeriesTeamAdapter.this.seriesTeams.get(ViewHolder.this.getAdapterPosition())).getTeamURL() != null) {
                        if ((CricketSeriesTeamAdapter.this.seriesTeams.get(ViewHolder.this.getAdapterPosition())).getTeamURL().equals("")) {
                            Toast.makeText(CricketSeriesTeamAdapter.this.context, "Squads not found", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(CricketSeriesTeamAdapter.this.context, CricketSeriesPlayerActivity.class);
                        intent.putExtra("teamName", (CricketSeriesTeamAdapter.this.seriesTeams.get(ViewHolder.this.getAdapterPosition())).getTeamName());
                        intent.putExtra(ImagesContract.URL, "https://m.cricbuzz.com" + ( CricketSeriesTeamAdapter.this.seriesTeams.get(ViewHolder.this.getAdapterPosition())).getTeamURL());
                        intent.setFlags(268435456);
                        CricketSeriesTeamAdapter.this.context.startActivity(intent);
                        CricketSeriesTeamAdapter.this.adManager.showInterstitial();
                        return;
                    }
                    Toast.makeText(CricketSeriesTeamAdapter.this.context, "Squads not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}