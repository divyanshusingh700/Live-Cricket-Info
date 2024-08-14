package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.ManOfTheMatch;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;


public class ManOfTheMatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private Context context;
    private ArrayList<ManOfTheMatch> manOfTheMatches;

    public ManOfTheMatchAdapter(ArrayList<ManOfTheMatch> arrayList, Context context, Activity activity) {
        this.manOfTheMatches = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_player_of_match, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.title.setText(this.manOfTheMatches.get(i).getTitle());
        viewHolder.playerName.setText(this.manOfTheMatches.get(i).getPlayerName());
        String lowerCase = this.manOfTheMatches.get(i).getPlayerName().replace("(c)", "").trim().replace("(wk)", "").trim().replace(" ", "-").toLowerCase();
        Picasso picasso = Picasso.get();
        picasso.load("https://raw.githubusercontent.com/Applicationslab/cricket_player_image/main/" + lowerCase + ".webp").into(viewHolder.playerImage, new Callback() { // from class: com.digital.livecricketapp.Schedule.ScheduleDetails.Adapters.ManOfTheMatchAdapter.1
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(8);
            }

            @Override
            public void onError(Exception exc) {
                viewHolder.progressBar.setVisibility(8);
                viewHolder.playerImage.setImageResource(R.drawable.profile);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.manOfTheMatches.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView playerImage;
        private TextView playerName;
        private ProgressBar progressBar;
        private TextView title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.manOfTheMatchTitle);
            this.playerName = (TextView) view.findViewById(R.id.playerName);
            this.playerImage = (CircleImageView) view.findViewById(R.id.imageView2);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar3);
        }
    }
}