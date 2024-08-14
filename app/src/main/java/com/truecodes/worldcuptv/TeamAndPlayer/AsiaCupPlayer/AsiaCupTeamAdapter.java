package com.truecodes.worldcuptv.TeamAndPlayer.AsiaCupPlayer;



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
import com.google.android.gms.common.internal.ImagesContract;
import com.truecodes.worldcuptv.Schedule.ScheduleAdapter;


public class AsiaCupTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    AdManager adManager;
    int[] asiaCupIconLis;
    String[] asiaCupItemList;
    String[] asiaCupSquad;
    Context context;
    String[] regular_team_url;

    public AsiaCupTeamAdapter(String[] strArr, String[] strArr2, String[] strArr3, int[] iArr, Context context, Activity activity) {
        this.asiaCupItemList = strArr;
        this.regular_team_url = strArr2;
        this.asiaCupSquad = strArr3;
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
        ViewHolder viewHolder = ( ViewHolder) vHolder;
        viewHolder.imageView.setImageResource(this.asiaCupIconLis[i]);
        viewHolder.textView.setText(this.asiaCupItemList[i]);
    }

    @Override
    public int getItemCount() {
        return this.asiaCupItemList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(final View view) {
            super(view);
            this.imageView =  view.findViewById(R.id.homeImageViewId);
            this.textView =   view.findViewById(R.id.homeTextViewId);
            AsiaCupTeamAdapter.this.adManager = new AdManager(AsiaCupTeamAdapter.this.context, AsiaCupTeamAdapter.this.activity);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    Intent intent = new Intent(view.getContext(), AsiaCupTeamDetailsScreen.class);
                    intent.putExtra(ImagesContract.URL, AsiaCupTeamAdapter.this.regular_team_url[ViewHolder.this.getAdapterPosition()]);
                    intent.putExtra("team", AsiaCupTeamAdapter.this.asiaCupItemList[ViewHolder.this.getAdapterPosition()]);
                    intent.putExtra("squad", AsiaCupTeamAdapter.this.asiaCupSquad[ViewHolder.this.getAdapterPosition()]);
                    view.getContext().startActivity(intent);
                    AsiaCupTeamAdapter.this.adManager.showInterstitial();
                }
            });
        }
    }
}