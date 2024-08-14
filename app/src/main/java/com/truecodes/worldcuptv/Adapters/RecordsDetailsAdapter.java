package com.truecodes.worldcuptv.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.UI.Model.RecordsDetails;
import java.util.ArrayList;

public class RecordsDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<RecordsDetails> recordsDetails;
    private String scoreTitle;
    private String secondScoreTitle;

    public RecordsDetailsAdapter(ArrayList<RecordsDetails> arrayList, String str, String str2) {
        this.recordsDetails = arrayList;
        this.scoreTitle = str;
        this.secondScoreTitle = str2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_record_details, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.teamName.setText(recordsDetails.get(i).getName());
        TextView textView = viewHolder.teamScore;
        textView.setText(scoreTitle + ": " + recordsDetails.get(i).getScore());
        TextView textView2 = viewHolder.teamRunRate;
        textView2.setText(secondScoreTitle + ": " + recordsDetails.get(i).getSecondScore());
    }

    @Override
    public int getItemCount() {
        return this.recordsDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView teamName;
        TextView teamRunRate;
        TextView teamScore;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.championsTeamImage);
            teamName = (TextView) view.findViewById(R.id.championsTeamName);
            teamScore = (TextView) view.findViewById(R.id.textView2);
            teamRunRate = (TextView) view.findViewById(R.id.textView4);
        }
    }
}