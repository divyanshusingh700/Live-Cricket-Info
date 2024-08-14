package com.truecodes.worldcuptv.TeamAndPlayer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.truecodes.worldcuptv.Schedule.ScheduleAdapter;

import java.util.ArrayList;

public class PlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    Context context;
    ArrayList<Players> players;

    public PlayersAdapter(ArrayList<Players> arrayList, Context context, Activity activity) {
        this.players = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_team, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = ( ViewHolder) vHolder;
        viewHolder.textView.setText(this.players.get(i).getPlayerName());
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(this.players.get(i).playerImage).into(viewHolder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility( View.GONE);
            }

            @Override
            public void onError(Exception exc) {
                viewHolder.imageView.setImageResource(R.drawable.question);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.players.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imageView =   view.findViewById(R.id.homeImageViewId);
            this.textView =   view.findViewById(R.id.homeTextViewId);
            this.progressBar =  view.findViewById(R.id.progressBar);
        }
    }
}