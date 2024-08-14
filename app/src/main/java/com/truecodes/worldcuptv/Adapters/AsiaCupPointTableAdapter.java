package com.truecodes.worldcuptv.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Models.AsiaCupPointTable;
import com.truecodes.worldcuptv.R;
import java.util.ArrayList;

public class AsiaCupPointTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<AsiaCupPointTable> pointTables;

    public AsiaCupPointTableAdapter(ArrayList<AsiaCupPointTable> arrayList) {
        this.pointTables = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_point_table, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vHolder = (ViewHolder) viewHolder;
        vHolder.teamName.setText(pointTables.get(i).getTeamName());
        vHolder.match.setText(pointTables.get(i).getMatch());
        vHolder.win.setText(pointTables.get(i).getWin());
        vHolder.lose.setText(pointTables.get(i).getLose());
        vHolder.tied.setText(pointTables.get(i).getTied());
        
        vHolder.points.setText(pointTables.get(i).getPoints());
        vHolder.nrr.setText(pointTables.get(i).getNrr());
    }

    @Override
    public int getItemCount() {
        return this.pointTables.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lose;
        private TextView match;
        private TextView nrr;
        private TextView points;
        private TextView teamName;
        private TextView tied;
        private TextView win;

        public ViewHolder(View view) {
            super(view);
            teamName = view.findViewById(R.id.teamName);
            match = view.findViewById(R.id.match);
            win =  view.findViewById(R.id.win);
            lose =  view.findViewById(R.id.lose);
            tied = view.findViewById(R.id.tied);
            points = view.findViewById(R.id.points);
            nrr = view.findViewById(R.id.nrr);
        }
    }
}