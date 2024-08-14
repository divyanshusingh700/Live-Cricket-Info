package com.truecodes.worldcuptv.MainPlayers.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.Adapters.HomeAdapters;
import com.truecodes.worldcuptv.MainPlayers.Models.BatsmanSummary;
import com.truecodes.worldcuptv.R;
import java.util.ArrayList;

public class BatsmanSummaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BatsmanSummary> batsmanSummaries;

    public BatsmanSummaryAdapter(ArrayList<BatsmanSummary> arrayList) {
        this.batsmanSummaries = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_career_stats, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.name.setText(this.batsmanSummaries.get(i).getName());
        viewHolder.match.setText(this.batsmanSummaries.get(i).getMatch());
        viewHolder.inngs.setText(this.batsmanSummaries.get(i).getInngs());
        viewHolder.runs.setText(this.batsmanSummaries.get(i).getRuns());
        viewHolder.hs.setText(this.batsmanSummaries.get(i).getHs());
    }

    @Override
    public int getItemCount() {
        return this.batsmanSummaries.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hs;
        private TextView inngs;
        private TextView match;
        private TextView name;
        private TextView runs;

        public ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.name);
            this.match = view.findViewById(R.id.match);
            this.inngs = view.findViewById(R.id.inngs);
            this.runs = view.findViewById(R.id.runs);
            this.hs = view.findViewById(R.id.hs);
        }
    }
}