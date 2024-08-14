package com.truecodes.worldcuptv.Schedule.Adapters;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.Models.Upcomings;
import com.truecodes.worldcuptv.Schedule.UpcomingScheduleDetails.UpcomingScheduleDetailsScreen;
import com.google.android.gms.common.internal.ImagesContract;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
public class UpcomingScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] IntentTwoTeam;
    private Activity activity;
    private Context context;
    private ArrayList<Upcomings> schedules;

    public UpcomingScheduleAdapter(ArrayList<Upcomings> arrayList, Context context, Activity activity) {
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
        viewHolder.matchNo.setText(this.schedules.get(i).getMatchNo());
        if (!this.schedules.get(i).getDate().equals("")) {
            String trim = this.schedules.get(i).getDate().replace(" |date: 'MMM d')", "").replace("(", "").trim();
            if (trim.length() == 13) {
                TextView textView = viewHolder.dateTextView;
                textView.setText("Start at: " + getDateTime(Long.valueOf(trim)));
            } else {
                viewHolder.dateTextView.setText("Date not found");
            }
        }
        if (this.schedules.get(i).getTeam().contains(",")) {
            String[] split = this.schedules.get(i).getTeam().split(",");
            if (split[0].contains("vs")) {
                String[] split2 = split[0].split("vs");
                viewHolder.firstTeamName.setText(split2[0]);
                viewHolder.secondTeamName.setText(split2[1]);
                viewHolder.firstTeamImage.setImageResource(setUpImage(split2[0].trim()));
                viewHolder.secondTeamImage.setImageResource(setUpImage(split2[1].trim()));
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (!((Upcomings) UpcomingScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition())).getUrl().equals("")) {
                        if (((Upcomings) UpcomingScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition())).getTeam().contains(",")) {
                            String[] split = ((Upcomings) UpcomingScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition())).getTeam().split(",");
                            if (split[0].contains("vs")) {
                                UpcomingScheduleAdapter.this.IntentTwoTeam = split[0].split("vs");
                            }
                        }
                        Intent intent = new Intent(UpcomingScheduleAdapter.this.context, UpcomingScheduleDetailsScreen.class);
                        intent.putExtra(ImagesContract.URL, "https://www.cricbuzz.com" + ((Upcomings) UpcomingScheduleAdapter.this.schedules.get(ViewHolder.this.getAdapterPosition())).getUrl());
                        intent.putExtra("teamAName", UpcomingScheduleAdapter.this.IntentTwoTeam[0]);
                        intent.putExtra("teamBName", UpcomingScheduleAdapter.this.IntentTwoTeam[1]);
                        intent.setFlags(268435456);
                        UpcomingScheduleAdapter.this.context.startActivity(intent);
                        return;
                    }
                    Toast.makeText(UpcomingScheduleAdapter.this.context, "Match details not found", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private int setUpImage(String str) {
        return str.contains("India") ? R.drawable.india : str.contains("Ireland") ? R.drawable.ireland : str.contains("Pakistan") ? R.drawable.pakistan : str.contains("Australia") ? R.drawable.australia : str.contains("Sri Lanka") ? R.drawable.sri_lanka : str.contains("Bangladesh") ? R.drawable.bangladesh : str.contains("England") ? R.drawable.england : str.contains("West Indies") ? R.drawable.west_indies : str.contains("South Africa") ? R.drawable.south_africa : str.contains("Zimbabwe") ? R.drawable.zimbabwe : str.contains("New Zealand") ? R.drawable.new_zealand : str.contains("Afghanistan") ? R.drawable.afghanistan : str.contains("Italy") ? R.drawable.italy : str.contains("Botswana") ? R.drawable.botswana : str.contains("Belgium") ? R.drawable.belgium : str.contains("Iran") ? R.drawable.iran : str.contains("Denmark") ? R.drawable.denmark : str.contains("Singapore") ? R.drawable.singapore : str.contains("Namibia") ? R.drawable.namibia : str.contains("Uganda") ? R.drawable.uganda : str.contains("Malaysia") ? R.drawable.malaysia : str.contains("Nepal") ? R.drawable.nepal : str.contains("Germany") ? R.drawable.germany : str.contains("Canada") ? R.drawable.canada : str.contains("Bermuda") ? R.drawable.bermuda : str.contains("Netherlands") ? R.drawable.netherlands : str.contains("United Arab Emirates") ? R.drawable.united_arab_emirates : str.contains("Hong Kong") ? R.drawable.hong_kong : str.contains("Kenya") ? R.drawable.kenya : str.contains("United States") ? R.drawable.united_states : str.contains("Scotland") ? R.drawable.scotland : str.contains("Fiji") ? R.drawable.fiji : str.contains("Papua New Guinea") ? R.drawable.papua_new_guinea : str.contains("Kuwait") ? R.drawable.kuwait : str.contains("Vanuatu") ? R.drawable.vanuatu : str.contains("Oman") ? R.drawable.oman : str.contains("Jersey") ? R.drawable.jersey : str.contains("IND") ? R.drawable.india : str.contains("IRE") ? R.drawable.ireland : str.contains("PAK") ? R.drawable.pakistan : str.contains("AUS") ? R.drawable.australia : str.contains("SL") ? R.drawable.sri_lanka : str.contains("BAN") ? R.drawable.bangladesh : str.contains("ENG") ? R.drawable.england : str.contains("WI") ? R.drawable.west_indies : str.contains("RSA") ? R.drawable.south_africa : str.contains("ZIM") ? R.drawable.zimbabwe : str.contains("NZ") ? R.drawable.new_zealand : str.contains("AFG") ? R.drawable.afghanistan : str.contains("ITA") ? R.drawable.italy : str.contains("BW") ? R.drawable.botswana : str.contains("BEL") ? R.drawable.belgium : str.contains("IRN") ? R.drawable.iran : str.contains("DEN") ? R.drawable.denmark : str.contains("SIN") ? R.drawable.singapore : str.contains("NAM") ? R.drawable.namibia : str.contains("UGA") ? R.drawable.uganda : str.contains("MLY") ? R.drawable.malaysia : str.contains("NEP") ? R.drawable.nepal : str.contains("GER") ? R.drawable.germany : str.contains("CAN") ? R.drawable.canada : str.contains("BER") ? R.drawable.bermuda : str.contains("NED") ? R.drawable.netherlands : str.contains("UAE") ? R.drawable.united_arab_emirates : str.contains("HK") ? R.drawable.hong_kong : str.contains("KEN") ? R.drawable.kenya : str.contains("USA") ? R.drawable.united_states : str.contains("SCO") ? R.drawable.scotland : str.contains("FIJI") ? R.drawable.fiji : str.contains("PNG") ? R.drawable.papua_new_guinea : str.contains("KUW") ? R.drawable.kuwait : str.contains("VAN") ? R.drawable.vanuatu : str.contains("OMAN") ? R.drawable.oman : str.contains("JER") ? R.drawable.jersey : R.drawable.question;
    }

    private String getDateTime(Long l) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(l.longValue());
        return DateFormat.format("dd MMM, hh:mm a", calendar).toString();
    }
}