package com.truecodes.worldcuptv.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class ScheduleAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] dateList;
    int[] firstTeam;
    String[] firstTeamName;
    String[] matchNo;
    int[] secondTeam;
    String[] secondTeamName;

    public ScheduleAdapters(int[] iArr, int[] iArr2, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
        this.firstTeam = iArr;
        this.secondTeam = iArr2;
        this.firstTeamName = strArr;
        this.secondTeamName = strArr2;
        this.matchNo = strArr3;
        this.dateList = strArr4;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_schedule, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.firstTeamImage.setImageResource( firstTeam[i]);
        viewHolder.secondTeamImage.setImageResource( secondTeam[i]);
        viewHolder.matchNo.setText(matchNo[i]);
        viewHolder.firstTeamName.setText(firstTeamName[i]);
        viewHolder.secondTeamName.setText(secondTeamName[i]);
        viewHolder.dateTextView.setText( dateList[i]);
    }

    @Override
    public int getItemCount() {
        return this.firstTeam.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        CircleImageView firstTeamImage;
        TextView firstTeamName;
        TextView matchNo;
        CircleImageView secondTeamImage;
        TextView secondTeamName;

        public ViewHolder(View view) {
            super(view);
             firstTeamImage = (CircleImageView) view.findViewById(R.id.firstTeamImageViewId);
             secondTeamImage = (CircleImageView) view.findViewById(R.id.secondImageViewId);
             matchNo = (TextView) view.findViewById(R.id.matchNoId);
             firstTeamName = (TextView) view.findViewById(R.id.firstTeamNameId);
             secondTeamName = (TextView) view.findViewById(R.id.secondTeamNameId);
             dateTextView = (TextView) view.findViewById(R.id.dateTextViewId);
        }
    }
}