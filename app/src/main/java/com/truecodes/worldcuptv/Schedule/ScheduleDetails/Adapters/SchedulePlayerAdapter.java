package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters;



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
import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.MainPlayers.MainPlayersScreen;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.SchedulePlayer;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class SchedulePlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    AdManager adManager;
    Context context;
    private ArrayList<SchedulePlayer> players;

    public SchedulePlayerAdapter(ArrayList<SchedulePlayer> arrayList, Context context, Activity activity) {
        this.players = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_schedule_player, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.textView.setText(this.players.get(i).getPlayerName());
        if (!this.players.get(i).getPlayerName().equals("")) {
            String lowerCase = this.players.get(i).getPlayerName().replace("(c)", "").trim().replace("(wk)", "").trim().replace(" ", "-").toLowerCase();
            Picasso picasso = Picasso.get();
            picasso.load("https://raw.githubusercontent.com/app-developer-a/cricket_player_image/main/" + lowerCase + ".webp").into(viewHolder.imageView, new Callback() { // from class: com.digital.livecricketapp.Schedule.ScheduleDetails.Adapters.SchedulePlayerAdapter.1
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
        return this.players.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            SchedulePlayerAdapter.this.adManager = new AdManager(SchedulePlayerAdapter.this.context, SchedulePlayerAdapter.this.activity);
            this.imageView = (ImageView) view.findViewById(R.id.homeImageViewId);
            this.textView = (TextView) view.findViewById(R.id.homeTextViewId);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (!((SchedulePlayer) SchedulePlayerAdapter.this.players.get(ViewHolder.this.getAdapterPosition())).getPlayerURL().equals("")) {
                        Intent intent = new Intent(SchedulePlayerAdapter.this.context, MainPlayersScreen.class);
                        intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((SchedulePlayer) SchedulePlayerAdapter.this.players.get(ViewHolder.this.getAdapterPosition())).getPlayerURL());
                        intent.setFlags(268435456);
                        SchedulePlayerAdapter.this.context.startActivity(intent);
                        SchedulePlayerAdapter.this.adManager.showInterstitial();
                        return;
                    }
                    Toast.makeText(SchedulePlayerAdapter.this.context, "Sorry, Player profile not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}