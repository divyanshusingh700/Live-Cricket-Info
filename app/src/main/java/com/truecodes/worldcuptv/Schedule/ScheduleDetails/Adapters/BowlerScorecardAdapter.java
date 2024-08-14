package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.ScorecardBowler;
import java.util.ArrayList;


public class BowlerScorecardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ScorecardBowler> bowlers;

    public BowlerScorecardAdapter(ArrayList<ScorecardBowler> arrayList) {
        this.bowlers = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_bowler_scorecard, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.playerName.setText(this.bowlers.get(i).getPlayerName());
        viewHolder.playerR.setText(this.bowlers.get(i).getPlayerR());
        viewHolder.playerB.setText(this.bowlers.get(i).getPlayerB());
        viewHolder.playerF.setText(this.bowlers.get(i).getPlayerF());
        viewHolder.playerS.setText(this.bowlers.get(i).getPlayerS());
        viewHolder.playerSR.setText(this.bowlers.get(i).getPlayerSR());
    }

    @Override
    public int getItemCount() {
        return this.bowlers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView playerB;
        private TextView playerF;
        private TextView playerName;
        private TextView playerR;
        private TextView playerS;
        private TextView playerSR;

        public ViewHolder(View view) {
            super(view);
            this.playerName = (TextView) view.findViewById(R.id.playerName);
            this.playerR = (TextView) view.findViewById(R.id.playerRuns);
            this.playerB = (TextView) view.findViewById(R.id.playerBall);
            this.playerF = (TextView) view.findViewById(R.id.playerFore);
            this.playerS = (TextView) view.findViewById(R.id.playerSix);
            this.playerSR = (TextView) view.findViewById(R.id.playerSR);
        }
    }
}