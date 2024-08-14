package com.truecodes.worldcuptv.Schedule;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.UI.BrowserScreen;
import com.google.android.gms.common.internal.ImagesContract;
import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;


public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    AdManager adManager;
    Context context;
    ArrayList<Schedule> schedules;

    public ScheduleAdapter(ArrayList<Schedule> arrayList, Context context, Activity activity) {
        this.schedules = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_schedule, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        throw new UnsupportedOperationException("Method not decompiled: com.digital.livecricketapp.Schedule.ScheduleAdapter.onBindViewHolder(com.digital.livecricketapp.Schedule.ScheduleAdapter$ViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.schedules.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        CircleImageView firstTeamImage;
        TextView firstTeamName;
        TextView location;
        TextView matchNo;
        CircleImageView secondTeamImage;
        TextView secondTeamName;

        public ViewHolder(View view) {
            super(view);
            ScheduleAdapter.this.adManager = new AdManager(ScheduleAdapter.this.context, ScheduleAdapter.this.activity);
            this.firstTeamImage = (CircleImageView) view.findViewById(R.id.firstTeamImageViewId);
            this.secondTeamImage = (CircleImageView) view.findViewById(R.id.secondImageViewId);
            this.matchNo = (TextView) view.findViewById(R.id.matchNoId);
            this.firstTeamName = (TextView) view.findViewById(R.id.firstTeamNameId);
            this.secondTeamName = (TextView) view.findViewById(R.id.secondTeamNameId);
            this.dateTextView = (TextView) view.findViewById(R.id.dateTextViewId);
            this.location = (TextView) view.findViewById(R.id.textView7);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.digital.livecricketapp.Schedule.ScheduleAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (!ScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition()).getMatchUrl().equals("")) {
                        Intent intent = new Intent(ScheduleAdapter.this.context, BrowserScreen.class);
                        intent.putExtra(ImagesContract.URL, "https://www.cricket.com" + ScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition()).getMatchUrl());
                        intent.setFlags(268435456);
                        ScheduleAdapter.this.context.startActivity(intent);
                        ScheduleAdapter.this.adManager.showInterstitial();
                        return;
                    }
                    Toast.makeText(ScheduleAdapter.this.context, "Match details not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}