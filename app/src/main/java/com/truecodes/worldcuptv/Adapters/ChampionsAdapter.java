package com.truecodes.worldcuptv.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;


public class ChampionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] championsList;
    int[] championsTeamImage;
    String[] championsTeamName;
    String[] runnersUpList;

    public ChampionsAdapter(int[] iArr, String[] strArr, String[] strArr2, String[] strArr3) {
        this.championsTeamImage = iArr;
        this.championsTeamName = strArr;
        this.championsList = strArr2;
        this.runnersUpList = strArr3;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_champions, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vHolder = (ViewHolder) viewHolder;
        vHolder.championsTeamImage.setImageResource(championsTeamImage[i]);
        vHolder.championsTeamName.setText( championsTeamName[i]);
        vHolder.championsList.setText( championsList[i]);
        vHolder.runnersUpList.setText( runnersUpList[i]);
    }

    @Override
    public int getItemCount() {
        return championsList.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView championsList;
        ImageView championsTeamImage;
        TextView championsTeamName;
        TextView runnersUpList;

        public ViewHolder(View view) {
            super(view);
            this.championsTeamImage = view.findViewById(R.id.championsTeamImage);
            this.championsTeamName = view.findViewById(R.id.championsTeamName);
            this.championsList = view.findViewById(R.id.championsList);
            this.runnersUpList = view.findViewById(R.id.runnersUpList);
        }
    }
}