package com.truecodes.worldcuptv.Schedule.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.AsiaCupSchedule;
import com.truecodes.worldcuptv.Schedule.LiveScheduleDetails.LiveScheduleDetailsScreen;
import com.truecodes.worldcuptv.Schedule.UpcomingScheduleDetails.UpcomingScheduleDetailsScreen;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class AsiaCupScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] IntentTwoTeam;
    private Activity activity;
    private Context context;
    private ArrayList<AsiaCupSchedule> schedules;

    public AsiaCupScheduleAdapter(ArrayList<AsiaCupSchedule> arrayList, Context context, Activity activity) {
        this.schedules = arrayList;
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
        if (!this.schedules.get(i).getMatchStatus().equals("")) {
            viewHolder.dateTextView.setText(this.schedules.get(i).getMatchStatus());
        } else if (!this.schedules.get(i).getMatchRunning().equals("")) {
            viewHolder.dateTextView.setText(this.schedules.get(i).getMatchRunning());
        } else if (!this.schedules.get(i).getDate().equals("")) {
            TextView textView = viewHolder.dateTextView;
            textView.setText("Start at: " + getDateTime(Long.valueOf(this.schedules.get(i).getDate())));
        }
        if (this.schedules.get(i).getTeam().contains(",")) {
            String[] split = this.schedules.get(i).getTeam().split(",");
            viewHolder.matchNo.setText(split[1]);
            if (split[0].contains("vs")) {
                final String[] split2 = split[0].split("vs");
                viewHolder.firstTeamName.setText(split2[0]);
                viewHolder.secondTeamName.setText(split2[1]);
                Picasso picasso = Picasso.get();
                picasso.load("https://raw.githubusercontent.com/app-developer-a/round_image/main/" + split2[0].trim().replace(" ", "_").toLowerCase() + ".png").into(viewHolder.firstTeamImage, new Callback() { // from class: com.digital.livecricketapp.Schedule.Adapters.AsiaCupScheduleAdapter.1
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception exc) {
                        viewHolder.firstTeamImage.setImageResource(R.drawable.question);
                    }
                });
                Picasso picasso2 = Picasso.get();
                picasso2.load("https://raw.githubusercontent.com/app-developer-a/round_image/main/" + split2[1].trim().toLowerCase().replace(" ", "_") + ".png").into(viewHolder.secondTeamImage, new Callback() { // from class: com.digital.livecricketapp.Schedule.Adapters.AsiaCupScheduleAdapter.2
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception exc) {
                        viewHolder.secondTeamImage.setImageResource(R.drawable.question);
                        Log.d("mvsijgs", "onError: https://raw.githubusercontent.com/app-developer-a/round_image/main/" + split2[1].trim().toLowerCase().replace(" ", "_") + ".png");
                    }
                });
            }
        }
        if (this.schedules.get(i).getVenue().contains(",")) {
            String[] split3 = this.schedules.get(i).getVenue().split(",");
            TextView textView2 = viewHolder.location;
            textView2.setText("Location: " + split3[1]);
            return;
        }
        TextView textView3 = viewHolder.location;
        textView3.setText("Venue " + this.schedules.get(i).getVenue());
    }

    @Override
    public int getItemCount() {
        return this.schedules.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        CircleImageView firstTeamImage;
        TextView firstTeamName;
        TextView location;
        TextView matchNo;
        CircleImageView secondTeamImage;
        TextView secondTeamName;

        public ViewHolder(View view) {
            super(view);
            this.firstTeamImage = (CircleImageView) view.findViewById(R.id.firstTeamImageViewId);
            this.secondTeamImage = (CircleImageView) view.findViewById(R.id.secondImageViewId);
            this.matchNo = (TextView) view.findViewById(R.id.matchNoId);
            this.firstTeamName = (TextView) view.findViewById(R.id.firstTeamNameId);
            this.secondTeamName = (TextView) view.findViewById(R.id.secondTeamNameId);
            this.dateTextView = (TextView) view.findViewById(R.id.dateTextViewId);
            this.location = (TextView) view.findViewById(R.id.textView7);
            view.setOnClickListener(view2 -> {
                if (!((AsiaCupSchedule) schedules.get(getAdapterPosition())).getUrl().equals("")) {
                    if (!((AsiaCupSchedule) schedules.get(getAdapterPosition())).getMatchStatus().equals("")) {
                        if (((AsiaCupSchedule) schedules.get(getAdapterPosition())).getTeam().contains(",")) {
                            String[] split = ((AsiaCupSchedule) schedules.get(getAdapterPosition())).getTeam().split(",");
                            if (split[0].contains("vs")) {
                                IntentTwoTeam = split[0].split("vs");
                            }
                        }
                        Intent intent = new Intent(context, LiveScheduleDetailsScreen.class);
                        intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((AsiaCupSchedule) AsiaCupScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition())).getUrl());
                        intent.putExtra("teamAName", IntentTwoTeam[0]);
                        intent.putExtra("teamBName", IntentTwoTeam[1]);
                        intent.setFlags(268435456);
                        context.startActivity(intent);
                        return;
                    } else if (!( schedules.get(getAdapterPosition())).getMatchRunning().equals("")) {
                        if (( schedules.get(getAdapterPosition())).getTeam().contains(",")) {
                            String[] split2 = ( schedules.get(getAdapterPosition())).getTeam().split(",");
                            if (split2[0].contains("vs")) {
                                IntentTwoTeam = split2[0].split("vs");
                            }
                        }
                        Intent LiveScheduleDetailsIntent = new Intent(context, LiveScheduleDetailsScreen.class);
                        LiveScheduleDetailsIntent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((AsiaCupSchedule) AsiaCupScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition())).getUrl());
                        LiveScheduleDetailsIntent.putExtra("teamAName", IntentTwoTeam[0]);
                        LiveScheduleDetailsIntent.putExtra("teamBName", IntentTwoTeam[1]);
                        LiveScheduleDetailsIntent.setFlags(268435456);
                        context.startActivity(LiveScheduleDetailsIntent);
                        return;
                    } else {
                        if (( schedules.get(getAdapterPosition())).getTeam().contains(",")) {
                            String[] split3 = ( schedules.get(getAdapterPosition())).getTeam().split(",");
                            if (split3[0].contains("vs")) {
                                IntentTwoTeam = split3[0].split("vs");
                            }
                        }
                        Intent UpcomingScheduleDetailsIntent = new Intent(context, UpcomingScheduleDetailsScreen.class);
                        UpcomingScheduleDetailsIntent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" +
                                ((AsiaCupSchedule) schedules.get(getAdapterPosition())).getUrl());
                        UpcomingScheduleDetailsIntent.putExtra("teamAName", IntentTwoTeam[0]);
                        UpcomingScheduleDetailsIntent.putExtra("teamBName", IntentTwoTeam[1]);
                        UpcomingScheduleDetailsIntent.setFlags(268435456);
                        context.startActivity(UpcomingScheduleDetailsIntent);
                        return;
                    }
                }
                Toast.makeText(context, "Match details not found", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private int setUpImage(String str) {
        return str.contains("India")
                ? R.drawable.india : str.contains("Ireland")
                ? R.drawable.ireland : str.contains("Pakistan")
                ? R.drawable.pakistan : str.contains("Australia")
                ? R.drawable.australia : str.contains("Sri Lanka")
                ? R.drawable.sri_lanka : str.contains("Bangladesh")
                ? R.drawable.bangladesh : str.contains("England")
                ? R.drawable.england : str.contains("West Indies")
                ? R.drawable.west_indies : str.contains("South Africa")
                ? R.drawable.south_africa : str.contains("Zimbabwe")
                ? R.drawable.zimbabwe : str.contains("New Zealand")
                ? R.drawable.new_zealand : str.contains("Afghanistan")
                ? R.drawable.afghanistan : str.contains("Italy")
                ? R.drawable.italy : str.contains("Botswana")
                ? R.drawable.botswana : str.contains("Belgium")
                ? R.drawable.belgium : str.contains("Iran")
                ? R.drawable.iran : str.contains("Denmark")
                ? R.drawable.denmark : str.contains("Singapore")
                ? R.drawable.singapore : str.contains("Namibia")
                ? R.drawable.namibia : str.contains("Uganda")
                ? R.drawable.uganda : str.contains("Malaysia")
                ? R.drawable.malaysia : str.contains("Nepal")
                ? R.drawable.nepal : str.contains("Germany")
                ? R.drawable.germany : str.contains("Canada")
                ? R.drawable.canada : str.contains("Bermuda")
                ? R.drawable.bermuda : str.contains("Netherlands")
                ? R.drawable.netherlands : str.contains("United Arab Emirates")
                ? R.drawable.united_arab_emirates : str.contains("Hong Kong")
                ? R.drawable.hong_kong : str.contains("Kenya")
                ? R.drawable.kenya : str.contains("United States")
                ? R.drawable.united_states : str.contains("Scotland")
                ? R.drawable.scotland : str.contains("Fiji")
                ? R.drawable.fiji : str.contains("Papua New Guinea")
                ? R.drawable.papua_new_guinea : str.contains("Kuwait")
                ? R.drawable.kuwait : str.contains("Vanuatu")
                ? R.drawable.vanuatu : str.contains("Oman")
                ? R.drawable.oman : str.contains("Jersey")
                ? R.drawable.jersey : str.contains("IND")
                ? R.drawable.india : str.contains("IRE")
                ? R.drawable.ireland : str.contains("PAK")
                ? R.drawable.pakistan : str.contains("AUS")
                ? R.drawable.australia : str.contains("SL")
                ? R.drawable.sri_lanka : str.contains("BAN")
                ? R.drawable.bangladesh : str.contains("ENG")
                ? R.drawable.england : str.contains("WI")
                ? R.drawable.west_indies : str.contains("RSA")
                ? R.drawable.south_africa : str.contains("ZIM")
                ? R.drawable.zimbabwe : str.contains("NZ")
                ? R.drawable.new_zealand : str.contains("AFG")
                ? R.drawable.afghanistan : str.contains("ITA")
                ? R.drawable.italy : str.contains("BW")
                ? R.drawable.botswana : str.contains("BEL")
                ? R.drawable.belgium : str.contains("IRN")
                ? R.drawable.iran : str.contains("DEN")
                ? R.drawable.denmark : str.contains("SIN")
                ? R.drawable.singapore : str.contains("NAM")
                ? R.drawable.namibia : str.contains("UGA")
                ? R.drawable.uganda : str.contains("MLY") ? R.drawable.malaysia : str.contains("NEP")
                ? R.drawable.nepal : str.contains("GER") ? R.drawable.germany : str.contains("CAN")
                ? R.drawable.canada : str.contains("BER") ? R.drawable.bermuda : str.contains("NED")
                ? R.drawable.netherlands : str.contains("UAE") ? R.drawable.united_arab_emirates : str.contains("HK")
                ? R.drawable.hong_kong : str.contains("KEN") ? R.drawable.kenya : str.contains("USA")
                ? R.drawable.united_states : str.contains("SCO") ? R.drawable.scotland : str.contains("FIJI")
                ? R.drawable.fiji : str.contains("PNG") ? R.drawable.papua_new_guinea : str.contains("KUW")
                ? R.drawable.kuwait : str.contains("VAN") ? R.drawable.vanuatu : str.contains("OMAN")
                ? R.drawable.oman : str.contains("JER") ? R.drawable.jersey : R.drawable.question;
    }

    private String getDateTime(Long l) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(l.longValue());
        return DateFormat.format("dd MMM, hh:mm a", calendar).toString();
    }
}