package com.truecodes.worldcuptv.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.TeamsDetailsScreen;
import com.google.android.gms.common.internal.ImagesContract;

public class TeamAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    AdManager adManager;
    int[] asiaCupIconLis;
    String[] asiaCupItemList;
    Context context;
    String[] regular_team_url;

    public TeamAdapters(String[] strArr, String[] strArr2, int[] iArr, Context context, Activity activity) {
        this.asiaCupItemList = strArr;
        this.regular_team_url = strArr2;
        this.asiaCupIconLis = iArr;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_team, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.imageView.setImageResource(asiaCupIconLis[i]);
        viewHolder.textView.setText(asiaCupItemList[i]);
    }

    @Override
    public int getItemCount() {
        return asiaCupItemList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(final View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.homeImageViewId);
            textView = (TextView) view.findViewById(R.id.homeTextViewId);
            adManager = new AdManager(context, activity);
            view.setOnClickListener(view2 -> {
                Intent intent = new Intent(view.getContext(), TeamsDetailsScreen.class);
                intent.putExtra(ImagesContract.URL, regular_team_url[getAdapterPosition()]);
                intent.putExtra("team", asiaCupItemList[getAdapterPosition()]);
                view.getContext().startActivity(intent);
                adManager.showInterstitial();
            });
        }
    }
}