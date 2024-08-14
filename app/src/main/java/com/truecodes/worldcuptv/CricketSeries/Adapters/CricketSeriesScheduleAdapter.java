package com.truecodes.worldcuptv.CricketSeries.Adapters;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.Adapters.HomeAdapters;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeriesSchedule;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.LiveScheduleDetails.LiveScheduleDetailsScreen;
import com.truecodes.worldcuptv.Schedule.UpcomingScheduleDetails.UpcomingScheduleDetailsScreen;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class CricketSeriesScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] IntentTwoTeam;
    private Activity activity;
    private AdManager adManager;
    private Context context;
    private ArrayList<CricketSeriesSchedule> cricketSeriesSchedules;

    public CricketSeriesScheduleAdapter(ArrayList<CricketSeriesSchedule> arrayList, Context context, Activity activity) {
        this.cricketSeriesSchedules = arrayList;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_schedule, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.firstProgressBar.setVisibility(0);
        viewHolder.secondProgressBar.setVisibility(0);
        if (!this.cricketSeriesSchedules.get(i).getMatchRunning().equals("")) {
            viewHolder.dateTextView.setText(this.cricketSeriesSchedules.get(i).getMatchRunning());
        } else if (!this.cricketSeriesSchedules.get(i).getMatchStatus().equals("")) {
            viewHolder.dateTextView.setText(this.cricketSeriesSchedules.get(i).getMatchStatus());
        } else if (!this.cricketSeriesSchedules.get(i).getDate().equals("") && this.cricketSeriesSchedules.get(i).getDate().length() == 13) {
            TextView textView = viewHolder.dateTextView;
            textView.setText("Start at: " + getDateTime(Long.valueOf(this.cricketSeriesSchedules.get(i).getDate())));
        }
        if (this.cricketSeriesSchedules.get(i).getTeams().contains(",")) {
            String[] split = this.cricketSeriesSchedules.get(i).getTeams().split(",");
            if (split[0].contains("vs")) {
                String[] split2 = split[0].split("vs");
                viewHolder.firstTeamName.setText(split2[0].replace("United Arab Emirates", "UAE").trim());
                viewHolder.secondTeamName.setText(split2[1].replace("United Arab Emirates", "UAE").trim());
                Picasso picasso = Picasso.get();
                picasso.load("https://raw.githubusercontent.com/app-developer-a/round_image/main/" + split2[0].trim().replace(" ", "_").toLowerCase() + ".png").into(viewHolder.firstTeamImage, new Callback() { // from class: com.digital.livecricketapp.CricketSeries.Adapters.CricketSeriesScheduleAdapter.1
                    @Override
                    public void onSuccess() {
                        viewHolder.firstProgressBar.setVisibility(8);
                    }

                    @Override
                    public void onError(Exception exc) {
                        viewHolder.firstProgressBar.setVisibility(8);
                        viewHolder.firstTeamImage.setImageResource(R.drawable.nothing);
                    }
                });
                Picasso picasso2 = Picasso.get();
                picasso2.load("https://raw.githubusercontent.com/app-developer-a/round_image/main/" + split2[1].trim().toLowerCase().replace(" ", "_") + ".png").into(viewHolder.secondTeamImage, new Callback() { // from class: com.digital.livecricketapp.CricketSeries.Adapters.CricketSeriesScheduleAdapter.2
                    @Override
                    public void onSuccess() {
                        viewHolder.secondProgressBar.setVisibility(8);
                    }

                    @Override
                    public void onError(Exception exc) {
                        viewHolder.secondProgressBar.setVisibility(8);
                        viewHolder.secondTeamImage.setImageResource(R.drawable.nothing);
                    }
                });
            }
            if (split.length == 2) {
                viewHolder.matchNo.setText(split[1]);
            }
            if (this.cricketSeriesSchedules.get(i).getVenue().contains(",")) {
                String[] split3 = this.cricketSeriesSchedules.get(i).getVenue().split(",");
                TextView textView2 = viewHolder.location;
                textView2.setText("Location: " + split3[1]);
                return;
            }
            TextView textView3 = viewHolder.location;
            textView3.setText("Venue " + this.cricketSeriesSchedules.get(i).getVenue());
        }
    }

    @Override
    public int getItemCount() {
        return this.cricketSeriesSchedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        ProgressBar firstProgressBar;
        CircleImageView firstTeamImage;
        TextView firstTeamName;
        TextView location;
        TextView matchNo;
        ProgressBar secondProgressBar;
        CircleImageView secondTeamImage;
        TextView secondTeamName;

        public ViewHolder(View view) {
            super(view);
            this.firstTeamImage =   view.findViewById(R.id.firstTeamImageViewId);
            this.secondTeamImage =   view.findViewById(R.id.secondImageViewId);
            this.matchNo =  view.findViewById(R.id.matchNoId);
            this.firstTeamName =  view.findViewById(R.id.firstTeamNameId);
            this.secondTeamName = view.findViewById(R.id.secondTeamNameId);
            this.dateTextView = view.findViewById(R.id.dateTextViewId);
            this.location = view.findViewById(R.id.textView7);
            this.firstProgressBar = view.findViewById(R.id.firstProgressBar);
            this.secondProgressBar = view.findViewById(R.id.secondProgressBar);
            CricketSeriesScheduleAdapter.this.adManager = new AdManager(CricketSeriesScheduleAdapter.this.context, CricketSeriesScheduleAdapter.this.activity);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (!(  CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getUrl().equals("")) {
                        if (!(  CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getMatchStatus().equals("")) {
                            if ((  CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getTeams().contains(",")) {
                                String[] split = (  CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getTeams().split(",");
                                if (split[0].contains("vs")) {
                                    CricketSeriesScheduleAdapter.this.IntentTwoTeam = split[0].split("vs");
                                }
                            }
                            Intent intent = new Intent(CricketSeriesScheduleAdapter.this.context, LiveScheduleDetailsScreen.class);
                            intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((CricketSeriesSchedule) CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getUrl());
                            intent.putExtra("teamAName", CricketSeriesScheduleAdapter.this.IntentTwoTeam[0]);
                            intent.putExtra("teamBName", CricketSeriesScheduleAdapter.this.IntentTwoTeam[1]);
                            intent.setFlags(268435456);
                            CricketSeriesScheduleAdapter.this.context.startActivity(intent);
                            CricketSeriesScheduleAdapter.this.adManager.showInterstitial();
                            return;
                        } else if (!( CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getMatchRunning().equals("")) {
                            if (( CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getTeams().contains(",")) {
                                String[] split2 = ( CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getTeams().split(",");
                                if (split2[0].contains("vs")) {
                                    CricketSeriesScheduleAdapter.this.IntentTwoTeam = split2[0].split("vs");
                                }
                            }
                            Intent intent2 = new Intent(CricketSeriesScheduleAdapter.this.context, LiveScheduleDetailsScreen.class);
                            intent2.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + (  CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getUrl());
                            intent2.putExtra("teamAName", CricketSeriesScheduleAdapter.this.IntentTwoTeam[0]);
                            intent2.putExtra("teamBName", CricketSeriesScheduleAdapter.this.IntentTwoTeam[1]);
                            intent2.setFlags(268435456);
                            CricketSeriesScheduleAdapter.this.context.startActivity(intent2);
                            CricketSeriesScheduleAdapter.this.adManager.showInterstitial();
                            return;
                        } else {
                            if (( CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getTeams().contains(",")) {
                                String[] split3 = ( CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getTeams().split(",");
                                if (split3[0].contains("vs")) {
                                    CricketSeriesScheduleAdapter.this.IntentTwoTeam = split3[0].split("vs");
                                }
                            }
                            Intent intent3 = new Intent(CricketSeriesScheduleAdapter.this.context, UpcomingScheduleDetailsScreen.class);
                            intent3.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((CricketSeriesSchedule) CricketSeriesScheduleAdapter.this.cricketSeriesSchedules.get(ViewHolder.this.getAdapterPosition())).getUrl());
                            intent3.putExtra("teamAName", CricketSeriesScheduleAdapter.this.IntentTwoTeam[0]);
                            intent3.putExtra("teamBName", CricketSeriesScheduleAdapter.this.IntentTwoTeam[1]);
                            intent3.setFlags(268435456);
                            CricketSeriesScheduleAdapter.this.context.startActivity(intent3);
                            CricketSeriesScheduleAdapter.this.adManager.showInterstitial();
                            return;
                        }
                    }
                    Toast.makeText(CricketSeriesScheduleAdapter.this.context, "Match details not found", 0).show();
                }
            });
        }
    }

    private String getDateTime(Long l) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(l.longValue());
        return DateFormat.format("dd MMM, hh:mm a", calendar).toString();
    }
}