package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.ScorecardBatsman;
import java.util.ArrayList;


public class BatsmanScorecardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ScorecardBatsman> batsmen;

    public BatsmanScorecardAdapter(ArrayList<ScorecardBatsman> arrayList) {
        this.batsmen = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_batsman_scorecard, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        String playerName = this.batsmen.get(i).getPlayerName();
        if (!playerName.contains("Extras")) {
            if (!playerName.contains("Did not Bat")) {
                if (playerName.contains("Total")) {
                    ViewGroup.LayoutParams layoutParams = viewHolder.playerR.getLayoutParams();
                    layoutParams.width = -2;
                    viewHolder.playerR.setLayoutParams(layoutParams);
                    ViewGroup.LayoutParams layoutParams2 = viewHolder.playerB.getLayoutParams();
                    layoutParams2.width = -2;
                    viewHolder.playerB.setLayoutParams(layoutParams2);
                    viewHolder.playerName.setText(playerName);
                    viewHolder.playerB.setText(this.batsmen.get(i).getPlayerR());
                    viewHolder.playerR.setText(this.batsmen.get(i).getPlayerStatus());
                    viewHolder.playerStatus.setVisibility(8);
                    viewHolder.playerF.setVisibility(8);
                    viewHolder.playerS.setVisibility(8);
                    viewHolder.playerSR.setVisibility(8);
                    return;
                }
                viewHolder.playerName.setVisibility(0);
                viewHolder.playerStatus.setVisibility(0);
                viewHolder.playerR.setVisibility(0);
                viewHolder.playerB.setVisibility(0);
                viewHolder.playerF.setVisibility(0);
                viewHolder.playerS.setVisibility(0);
                viewHolder.playerSR.setVisibility(0);
                viewHolder.playerName.setText(this.batsmen.get(i).getPlayerName());
                viewHolder.playerStatus.setText(this.batsmen.get(i).getPlayerStatus());
                viewHolder.playerR.setText(this.batsmen.get(i).getPlayerR());
                viewHolder.playerB.setText(this.batsmen.get(i).getPlayerB());
                viewHolder.playerF.setText(this.batsmen.get(i).getPlayerF());
                viewHolder.playerS.setText(this.batsmen.get(i).getPlayerS());
                viewHolder.playerSR.setText(this.batsmen.get(i).getPlayerSR());
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = viewHolder.playerR.getLayoutParams();
            layoutParams3.width = -2;
            viewHolder.playerR.setLayoutParams(layoutParams3);
            ViewGroup.LayoutParams layoutParams4 = viewHolder.playerB.getLayoutParams();
            layoutParams4.width = -2;
            viewHolder.playerB.setLayoutParams(layoutParams4);
            viewHolder.playerName.setText(playerName);
            viewHolder.playerB.setText(this.batsmen.get(i).getPlayerR());
            viewHolder.playerR.setText(this.batsmen.get(i).getPlayerStatus());
            viewHolder.playerStatus.setVisibility(8);
            viewHolder.playerF.setVisibility(8);
            viewHolder.playerS.setVisibility(8);
            viewHolder.playerSR.setVisibility(8);
            return;
        }
        ViewGroup.LayoutParams layoutParams5 = viewHolder.playerR.getLayoutParams();
        layoutParams5.width = -2;
        viewHolder.playerR.setLayoutParams(layoutParams5);
        ViewGroup.LayoutParams layoutParams6 = viewHolder.playerB.getLayoutParams();
        layoutParams6.width = -2;
        viewHolder.playerB.setLayoutParams(layoutParams6);
        viewHolder.playerName.setText(playerName);
        viewHolder.playerB.setText(this.batsmen.get(i).getPlayerR());
        viewHolder.playerR.setText(this.batsmen.get(i).getPlayerStatus());
        viewHolder.playerStatus.setVisibility(8);
        viewHolder.playerF.setVisibility(8);
        viewHolder.playerS.setVisibility(8);
        viewHolder.playerSR.setVisibility(8);
    }

    @Override
    public int getItemCount() {
        return this.batsmen.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView playerB;
        private TextView playerF;
        private TextView playerName;
        private TextView playerR;
        private TextView playerS;
        private TextView playerSR;
        private TextView playerStatus;

        public ViewHolder(View view) {
            super(view);
            this.playerName = (TextView) view.findViewById(R.id.playerName);
            this.playerStatus = (TextView) view.findViewById(R.id.playerStatus);
            this.playerR = (TextView) view.findViewById(R.id.playerRuns);
            this.playerB = (TextView) view.findViewById(R.id.playerBall);
            this.playerF = (TextView) view.findViewById(R.id.playerFore);
            this.playerS = (TextView) view.findViewById(R.id.playerSix);
            this.playerSR = (TextView) view.findViewById(R.id.playerSR);
        }
    }
}