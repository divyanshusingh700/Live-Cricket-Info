package com.truecodes.worldcuptv.CricketSeries.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.Adapters.HomeAdapters;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeries;
import com.truecodes.worldcuptv.CricketSeries.SeriesDetails.CricketSeriesDetailsActivity;
import com.truecodes.worldcuptv.CricketSeries.SeriesDetails.CricketSeriesDetailsTournamentActivity;
import com.truecodes.worldcuptv.R;
import java.util.ArrayList;

public class CricketSeriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    Context context;
    ArrayList<CricketSeries> cricketSeries;

    public CricketSeriesAdapter(ArrayList<CricketSeries> arrayList, Context context, Activity activity) {
        this.cricketSeries = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_series_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.seriesName.setText( cricketSeries.get(i).getSeriesName());
        viewHolder.seriesDate.setText( cricketSeries.get(i).getSeriesDate());
    }

    @Override
    public int getItemCount() {
        return  cricketSeries.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView seriesDate;
        private TextView seriesName;

        public ViewHolder(final View view) {
            super(view);
            this.seriesName = view.findViewById(R.id.seriesName);
            this.seriesDate =  view.findViewById(R.id.seriesDate);
            view.setOnClickListener(view2 -> {
                if (cricketSeries.get(getAdapterPosition()).getSeriesDetails() != null) {
                    if (!cricketSeries.get(getAdapterPosition()).getSeriesDetails().equals("")) {
                        if (cricketSeries.get(getAdapterPosition()).getSeriesName().contains("tour of")) {
                            Intent intent = new Intent(view.getContext(), CricketSeriesDetailsActivity.class);
                            intent.putExtra("seriesUrl", "https://www.cricbuzz.com" + cricketSeries.get(getAdapterPosition()).getSeriesDetails());
                            context.startActivity(intent);
                            return;
                        }
                        Intent intent2 = new Intent(view.getContext(), CricketSeriesDetailsTournamentActivity.class);
                        intent2.putExtra("seriesUrl", "https://www.cricbuzz.com" + cricketSeries.get(getAdapterPosition()).getSeriesDetails());
                        context.startActivity(intent2);
                        return;
                    }
                    Toast.makeText(context, "Series details not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "Series details not found", Toast.LENGTH_SHORT).show();
            });
        }
    }
}