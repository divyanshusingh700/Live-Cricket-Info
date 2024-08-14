package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters.ManOfTheMatchAdapter;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.ManOfTheMatch;
import com.google.android.gms.common.internal.ImagesContract;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class MatchInfoFragment extends Fragment {
    private String capacityHead;
    private LinearLayout capacityLayout;
    private String capacityText;
    private String cityHead;
    private LinearLayout cityLayout;
    private String cityText;
    private String dateHead;
    private LinearLayout dateLayout;
    private String dateText;
    private CircleImageView firstImageView;
    private String firstScore;
    private TextView firstScoreTextView;
    private CircleImageView firstTeamImageViewId;
    private String firstTeamName;
    private TextView firstTeamNameId;
    private TextView firstTeamNameTextView;
    private ConstraintLayout mainScorecardCardView;
    private ManOfTheMatchAdapter manOfTheMatchAdapter;
    private RelativeLayout manOfTheMatchLayout;
    private ProgressBar manOfTheMatchProgressBar;
    private RecyclerView manOfTheMatchRecyclerView;
    private ArrayList<ManOfTheMatch> manOfTheMatches = new ArrayList<>();
    private TextView matchCapacityHead;
    private TextView matchCapacityText;
    private TextView matchCityHead;
    private TextView matchCityText;
    private TextView matchDateHead;
    private TextView matchDateText;
    private CardView matchInfoLayout;
    private LinearLayout matchLayout;
    private TextView matchRefereeHead;
    private LinearLayout matchRefereeLayout;
    private TextView matchRefereeText;
    private TextView matchStadiumHead;
    private TextView matchStadiumText;
    private TextView matchThirdUmpireHead;
    private TextView matchThirdUmpireText;
    private TextView matchTimeHead;
    private TextView matchTimeText;
    private TextView matchTitleHead;
    private TextView matchTitleText;
    private TextView matchTossHead;
    private TextView matchTossText;
    private TextView matchUmpireText;
    private TextView matchUmpiresHead;
    private String refereeHead;
    private String refereeText;
    private String result;
    private TextView resultTextView;
    private CircleImageView secondImageView;
    private CircleImageView secondImageViewId;
    private String secondScore;
    private TextView secondScoreTextView;
    private String secondTeamName;
    private TextView secondTeamNameId;
    private TextView secondTeamNameTextView;
    private String stadiumHead;
    private LinearLayout stadiumLayout;
    private String stadiumText;
    private String teamAName;
    private String teamBName;
    private String thirdUmpireHead;
    private LinearLayout thirdUmpireLayout;
    private String thirdUmpireText;
    private String timeHead;
    private LinearLayout timeLayout;
    private String timeText;
    private String titleHead;
    private String titleText;
    private String tossHead;
    private LinearLayout tossLayout;
    private String tossText;
    private String umpiresHead;
    private LinearLayout umpiresLayout;
    private String umpiresText;
    String url;
    private CardView venueInfoLayout;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_match_info, viewGroup, false);
        if (getArguments() != null) {
            this.teamAName = getArguments().getString("teamAName");
            this.teamBName = getArguments().getString("teamBName");
            this.url = getArguments().getString(ImagesContract.URL);
        }
        this.firstTeamNameId = (TextView) inflate.findViewById(R.id.firstTeamNameId);
        this.secondTeamNameId = (TextView) inflate.findViewById(R.id.secondTeamNameId);
        this.firstTeamImageViewId = (CircleImageView) inflate.findViewById(R.id.firstTeamImageViewId);
        this.secondImageViewId = (CircleImageView) inflate.findViewById(R.id.secondImageViewId);
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.mainScorecardCardView);
        this.mainScorecardCardView = constraintLayout;
        constraintLayout.setVisibility(View.GONE);
        String str = this.teamAName;
        if (str != null) {
            this.firstTeamNameId.setText(str);
            this.firstTeamImageViewId.setImageResource(setUpImage(this.teamAName.trim()));
            this.mainScorecardCardView.setVisibility(View.VISIBLE);
        }
        String str2 = this.teamBName;
        if (str2 != null) {
            this.secondTeamNameId.setText(str2);
            this.secondImageViewId.setImageResource(setUpImage(this.teamBName.trim()));
            this.mainScorecardCardView.setVisibility(View.VISIBLE);
        }
        setUpScorecard(inflate);
        SetUpManOfTheMatch(inflate);
        SetUpMatchInfo(inflate);
        SetUpVenueInfo(inflate);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetLiveSchedule().execute(new Void[0]);
        new GetManOfTheMatchContent().execute(new Void[0]);
        new GetMatchInfoContent().execute(new Void[0]);
        new GetVenueContent().execute(new Void[0]);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.manOfTheMatches.clear();
    }

    private void setUpScorecard(View view) {
        this.firstScoreTextView = (TextView) view.findViewById(R.id.firstScore);
        this.secondScoreTextView = (TextView) view.findViewById(R.id.secondScore);
        this.resultTextView = (TextView) view.findViewById(R.id.dateTextViewId);
        this.firstScoreTextView.setVisibility(View.GONE);
        this.resultTextView.setVisibility(View.GONE);
    }

    /* loaded from: classes.dex */
    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        public void onPostExecute(Void r5) {
            super.onPostExecute(  r5);
            if (MatchInfoFragment.this.firstScore != null) {
                MatchInfoFragment.this.firstScoreTextView.setText(MatchInfoFragment.this.firstScore.replace(") ", ")\n").trim());
                MatchInfoFragment.this.firstScoreTextView.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.mainScorecardCardView.setVisibility(View.VISIBLE);
            }
            if (MatchInfoFragment.this.result != null) {
                MatchInfoFragment.this.resultTextView.setText(MatchInfoFragment.this.result);
                MatchInfoFragment.this.resultTextView.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.mainScorecardCardView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(MatchInfoFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-min-comp");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    MatchInfoFragment.this.firstScore = select.get(i).select("div.cb-col-scores").select("div.cb-scrs-wrp").text();
                    MatchInfoFragment.this.secondScore = select.get(i).select("div.cb-col-scores").text();
                    MatchInfoFragment.this.result = select.get(i).select("div.cb-min-stts").text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }

    private void SetUpManOfTheMatch(View view) {
        this.manOfTheMatchLayout =  view.findViewById(R.id.manOfTheMatchMainLayout);
        this.manOfTheMatchRecyclerView =  view.findViewById(R.id.manOfTheMatchRecyclerView);
        this.manOfTheMatchProgressBar = view.findViewById(R.id.manOfTheMatchProgressBar);
        this.manOfTheMatchRecyclerView.setHasFixedSize(true);
        this.manOfTheMatchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ManOfTheMatchAdapter manOfTheMatchAdapter = new ManOfTheMatchAdapter(this.manOfTheMatches, getContext(), getActivity());
        this.manOfTheMatchAdapter = manOfTheMatchAdapter;
        this.manOfTheMatchRecyclerView.setAdapter(manOfTheMatchAdapter);
    }


    private class GetManOfTheMatchContent extends AsyncTask<Void, Void, Void> {
        private GetManOfTheMatchContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MatchInfoFragment.this.manOfTheMatchProgressBar.setVisibility(View.VISIBLE);
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            MatchInfoFragment.this.manOfTheMatchProgressBar.setVisibility(View.GONE);
            MatchInfoFragment.this.manOfTheMatchAdapter.notifyDataSetChanged();
            if (MatchInfoFragment.this.manOfTheMatchAdapter.getItemCount() <= 0) {
                MatchInfoFragment.this.manOfTheMatchLayout.setVisibility(View.GONE);
            } else {
                MatchInfoFragment.this.manOfTheMatchLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(MatchInfoFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-min-comp").select("div.cb-col-50");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("a.cb-link-undrln").text();
                    MatchInfoFragment.this.manOfTheMatches.add(new ManOfTheMatch(select.get(i).select("span.cb-text-gray").text(), text, select.get(i).select("a.cb-link-undrln").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void SetUpMatchInfo(View view) {
        CardView cardView = view.findViewById(R.id.matchInfoLayout);
        this.matchInfoLayout = cardView;
        cardView.setVisibility(View.GONE);
        this.matchTitleHead = view.findViewById(R.id.matchTitleHead);
        this.matchTitleText = (TextView) view.findViewById(R.id.matchTitleText);
        this.matchDateHead = (TextView) view.findViewById(R.id.matchDateHead);
        this.matchDateText = (TextView) view.findViewById(R.id.matchDateText);
        this.matchTossHead = (TextView) view.findViewById(R.id.matchTossHead);
        this.matchTossText = (TextView) view.findViewById(R.id.matchTossText);
        this.matchTimeHead = (TextView) view.findViewById(R.id.matchTimeHead);
        this.matchTimeText = (TextView) view.findViewById(R.id.matchTimeText);
        this.matchUmpiresHead = (TextView) view.findViewById(R.id.matchUmpiresHead);
        this.matchUmpireText = (TextView) view.findViewById(R.id.matchUmpiresText);
        this.matchThirdUmpireHead = (TextView) view.findViewById(R.id.matchThirdUmpireHead);
        this.matchThirdUmpireText = (TextView) view.findViewById(R.id.matchThirdUmpireText);
        this.matchRefereeHead = (TextView) view.findViewById(R.id.matchRefereeHead);
        this.matchRefereeText = (TextView) view.findViewById(R.id.matchRefereeText);
        this.matchLayout = (LinearLayout) view.findViewById(R.id.matchTitleLayout);
        this.dateLayout = (LinearLayout) view.findViewById(R.id.matchDateLayout);
        this.tossLayout = (LinearLayout) view.findViewById(R.id.matchTossLayout);
        this.timeLayout = (LinearLayout) view.findViewById(R.id.matchTimeLayout);
        this.umpiresLayout = (LinearLayout) view.findViewById(R.id.matchUmpireLayout);
        this.thirdUmpireLayout = (LinearLayout) view.findViewById(R.id.matchThirdUmpireLayout);
        this.matchRefereeLayout = (LinearLayout) view.findViewById(R.id.matchRefereeLayout);
        this.matchLayout.setVisibility(View.GONE);
        this.dateLayout.setVisibility(View.GONE);
        this.tossLayout.setVisibility(View.GONE);
        this.timeLayout.setVisibility(View.GONE);
        this.umpiresLayout.setVisibility(View.GONE);
        this.thirdUmpireLayout.setVisibility(View.GONE);
        this.matchRefereeLayout.setVisibility(View.GONE);
    }


    private class GetMatchInfoContent extends AsyncTask<Void, Void, Void> {
        private GetMatchInfoContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r4) {
            super.onPostExecute( r4);
            if (MatchInfoFragment.this.titleHead != null || MatchInfoFragment.this.titleText != null) {
                MatchInfoFragment.this.matchLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchInfoLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchTitleHead.setText(MatchInfoFragment.this.titleHead);
                MatchInfoFragment.this.matchTitleText.setText(MatchInfoFragment.this.titleText);
            }
            if (MatchInfoFragment.this.dateHead != null || MatchInfoFragment.this.dateText != null) {
                MatchInfoFragment.this.dateLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchInfoLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchDateHead.setText(MatchInfoFragment.this.dateHead);
                if (MatchInfoFragment.this.dateText.length() == 13) {
                    TextView textView = MatchInfoFragment.this.matchDateText;
                    MatchInfoFragment matchInfoFragment = MatchInfoFragment.this;
                    textView.setText(matchInfoFragment.getDateTime(Long.valueOf(matchInfoFragment.dateText)));
                } else {
                    MatchInfoFragment.this.matchDateText.setText(MatchInfoFragment.this.dateText);
                }
            }
            if (MatchInfoFragment.this.tossHead != null || MatchInfoFragment.this.tossText != null) {
                MatchInfoFragment.this.matchInfoLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.tossLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchTossHead.setText(MatchInfoFragment.this.tossHead);
                MatchInfoFragment.this.matchTossText.setText(MatchInfoFragment.this.tossText);
            }
            if ((MatchInfoFragment.this.timeHead != null || MatchInfoFragment.this.timeText != null) && !MatchInfoFragment.this.timeText.equals("")) {
                MatchInfoFragment.this.timeLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchTimeHead.setText(MatchInfoFragment.this.timeHead);
                MatchInfoFragment.this.matchTimeText.setText(MatchInfoFragment.this.timeText);
            }
            if (MatchInfoFragment.this.umpiresHead != null || MatchInfoFragment.this.umpiresText != null) {
                MatchInfoFragment.this.matchInfoLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.umpiresLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchUmpiresHead.setText(MatchInfoFragment.this.umpiresHead);
                MatchInfoFragment.this.matchUmpireText.setText(MatchInfoFragment.this.umpiresText);
            }
            if (MatchInfoFragment.this.thirdUmpireHead != null || MatchInfoFragment.this.thirdUmpireText != null) {
                MatchInfoFragment.this.thirdUmpireLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchThirdUmpireHead.setText(MatchInfoFragment.this.thirdUmpireHead);
                MatchInfoFragment.this.matchThirdUmpireText.setText(MatchInfoFragment.this.thirdUmpireText);
            }
            if (!(MatchInfoFragment.this.refereeHead == null && MatchInfoFragment.this.refereeText == null) && MatchInfoFragment.this.refereeHead.contains("Referee")) {
                MatchInfoFragment.this.matchRefereeLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchRefereeHead.setText(MatchInfoFragment.this.refereeHead);
                MatchInfoFragment.this.matchRefereeText.setText(MatchInfoFragment.this.refereeText);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(MatchInfoFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    MatchInfoFragment.this.titleHead = eq.get(i).select("div.cb-mat-fct-itm").eq(0).text();
                    MatchInfoFragment.this.titleText = eq.get(i).select("div.cb-mat-fct-itm").eq(1).text();
                    MatchInfoFragment.this.dateHead = eq.get(i).select("div.cb-mat-fct-itm").eq(2).text();
                    MatchInfoFragment.this.dateText = eq.get(i).select("div.cb-mat-fct-itm").eq(3).select("span").attr("timestamp");
                    MatchInfoFragment.this.tossHead = eq.get(i).select("div.cb-mat-fct-itm").eq(4).text();
                    MatchInfoFragment.this.tossText = eq.get(i).select("div.cb-mat-fct-itm").eq(5).text();
                    MatchInfoFragment.this.timeHead = eq.get(i).select("div.cb-mat-fct-itm").eq(6).text();
                    MatchInfoFragment.this.timeText = eq.get(i).select("div.cb-mat-fct-itm").eq(7).text();
                    MatchInfoFragment.this.umpiresHead = eq.get(i).select("div.cb-mat-fct-itm").eq(10).text();
                    MatchInfoFragment.this.umpiresText = eq.get(i).select("div.cb-mat-fct-itm").eq(11).text();
                    MatchInfoFragment.this.thirdUmpireHead = eq.get(i).select("div.cb-mat-fct-itm").eq(12).text();
                    MatchInfoFragment.this.thirdUmpireText = eq.get(i).select("div.cb-mat-fct-itm").eq(13).text();
                    MatchInfoFragment.this.refereeHead = eq.get(i).select("div.cb-mat-fct-itm").eq(14).text();
                    MatchInfoFragment.this.refereeText = eq.get(i).select("div.cb-mat-fct-itm").eq(15).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }

    private void SetUpVenueInfo(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.venueInfoLayout);
        this.venueInfoLayout = cardView;
        cardView.setVisibility(View.GONE);
        this.matchStadiumHead = (TextView) view.findViewById(R.id.matchStadiumHead);
        this.matchStadiumText = (TextView) view.findViewById(R.id.matchStadiumText);
        this.matchCityHead = (TextView) view.findViewById(R.id.matchCityHead);
        this.matchCityText = (TextView) view.findViewById(R.id.matchCityText);
        this.matchCapacityHead = (TextView) view.findViewById(R.id.matchCapacityHead);
        this.matchCapacityText = (TextView) view.findViewById(R.id.matchCapacityText);
        this.stadiumLayout = (LinearLayout) view.findViewById(R.id.matchStadiumLayout);
        this.cityLayout = (LinearLayout) view.findViewById(R.id.matchCityLayout);
        this.capacityLayout = (LinearLayout) view.findViewById(R.id.matchCapacityLayout);
        this.stadiumLayout.setVisibility(View.GONE);
        this.cityLayout.setVisibility(View.GONE);
        this.capacityLayout.setVisibility(View.GONE);
    }


    private class GetVenueContent extends AsyncTask<Void, Void, Void> {
        private GetVenueContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            if (MatchInfoFragment.this.stadiumHead != null || MatchInfoFragment.this.stadiumText != null) {
                MatchInfoFragment.this.venueInfoLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.stadiumLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchStadiumHead.setText(MatchInfoFragment.this.stadiumHead);
                MatchInfoFragment.this.matchStadiumText.setText(MatchInfoFragment.this.stadiumText);
            }
            if (MatchInfoFragment.this.cityHead != null || MatchInfoFragment.this.cityText != null) {
                MatchInfoFragment.this.venueInfoLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.cityLayout.setVisibility(View.VISIBLE);
                MatchInfoFragment.this.matchCityHead.setText(MatchInfoFragment.this.cityHead);
                MatchInfoFragment.this.matchCityText.setText(MatchInfoFragment.this.cityText);
            }
            if (MatchInfoFragment.this.capacityHead == null && MatchInfoFragment.this.capacityText == null) {
                return;
            }
            MatchInfoFragment.this.venueInfoLayout.setVisibility(View.VISIBLE);
            MatchInfoFragment.this.capacityLayout.setVisibility(View.VISIBLE);
            MatchInfoFragment.this.matchCapacityHead.setText(MatchInfoFragment.this.capacityHead);
            MatchInfoFragment.this.matchCapacityText.setText(MatchInfoFragment.this.capacityText);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(MatchInfoFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(1);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    MatchInfoFragment.this.stadiumHead = eq.get(i).select("div.cb-mat-fct-itm").eq(0).text();
                    MatchInfoFragment.this.stadiumText = eq.get(i).select("div.cb-mat-fct-itm").eq(1).text();
                    MatchInfoFragment.this.cityHead = eq.get(i).select("div.cb-mat-fct-itm").eq(2).text();
                    MatchInfoFragment.this.cityText = eq.get(i).select("div.cb-mat-fct-itm").eq(3).text();
                    MatchInfoFragment.this.capacityHead = eq.get(i).select("div.cb-mat-fct-itm").eq(4).text();
                    MatchInfoFragment.this.capacityText = eq.get(i).select("div.cb-mat-fct-itm").eq(5).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }


    public String getDateTime(Long l) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(l.longValue());
        return DateFormat.format("dd MMM, hh:mm a", calendar).toString();
    }

    private String setAA(String str) {
        return str.contains("India") ? "IND" : str.contains("Ireland") ? "IRE" : str.contains("Pakistan") ? "PAK" : str.contains("Australia") ? "AUS" : str.contains("Sri Lanka") ? "SL" : str.contains("Bangladesh") ? "BAN" : str.contains("England") ? "ENG" : str.contains("West Indies") ? "WI" : str.contains("South Africa") ? "RSA" : str.contains("Zimbabwe") ? "ZIM" : str.contains("New Zealand") ? "NZ" : str.contains("Afghanistan") ? "AFG" : str.contains("Italy") ? "ITA" : str.contains("Botswana") ? "BW" : str.contains("Belgium") ? "BEL" : str.contains("Iran") ? "IRN" : str.contains("Denmark") ? "DEN" : str.contains("Singapore") ? "SIN" : str.contains("Namibia") ? "NAM" : str.contains("Uganda") ? "UGA" : str.contains("Malaysia") ? "MLY" : str.contains("Nepal") ? "NEP" : str.contains("Germany") ? "GER" : str.contains("Canada") ? "CAN" : str.contains("Bermuda") ? "BER" : str.contains("Netherlands") ? "NED" : str.contains("United Arab Emirates") ? "UAE" : str.contains("Hong Kong") ? "HK" : str.contains("Kenya") ? "KEN" : str.contains("United States") ? "USA" : str.contains("Scotland") ? "SCO" : str.contains("Fiji") ? "FIJI" : str.contains("Papua New Guinea") ? "PNG" : str.contains("Kuwait") ? "KUW" : str.contains("Vanuatu") ? "VAN" : str.contains("Oman") ? "OMAN" : str.contains("Jersey") ? "JER" : str;
    }

    private int setUpImage(String str) {
        return str.contains("India") ? R.drawable.india : str.contains("Ireland") ? R.drawable.ireland : str.contains("Pakistan") ? R.drawable.pakistan : str.contains("Australia") ? R.drawable.australia : str.contains("Sri Lanka") ? R.drawable.sri_lanka : str.contains("Bangladesh") ? R.drawable.bangladesh : str.contains("England") ? R.drawable.england : str.contains("West Indies") ? R.drawable.west_indies : str.contains("South Africa") ? R.drawable.south_africa : str.contains("Zimbabwe") ? R.drawable.zimbabwe : str.contains("New Zealand") ? R.drawable.new_zealand : str.contains("Afghanistan") ? R.drawable.afghanistan : str.contains("Italy") ? R.drawable.italy : str.contains("Botswana") ? R.drawable.botswana : str.contains("Belgium") ? R.drawable.belgium : str.contains("Iran") ? R.drawable.iran : str.contains("Denmark") ? R.drawable.denmark : str.contains("Singapore") ? R.drawable.singapore : str.contains("Namibia") ? R.drawable.namibia : str.contains("Uganda") ? R.drawable.uganda : str.contains("Malaysia") ? R.drawable.malaysia : str.contains("Nepal") ? R.drawable.nepal : str.contains("Germany") ? R.drawable.germany : str.contains("Canada") ? R.drawable.canada : str.contains("Bermuda") ? R.drawable.bermuda : str.contains("Netherlands") ? R.drawable.netherlands : str.contains("United Arab Emirates") ? R.drawable.united_arab_emirates : str.contains("Hong Kong") ? R.drawable.hong_kong : str.contains("Kenya") ? R.drawable.kenya : str.contains("United States") ? R.drawable.united_states : str.contains("Scotland") ? R.drawable.scotland : str.contains("Fiji") ? R.drawable.fiji : str.contains("Papua New Guinea") ? R.drawable.papua_new_guinea : str.contains("Kuwait") ? R.drawable.kuwait : str.contains("Vanuatu") ? R.drawable.vanuatu : str.contains("Oman") ? R.drawable.oman : str.contains("Jersey") ? R.drawable.jersey : str.contains("IND") ? R.drawable.india : str.contains("IRE") ? R.drawable.ireland : str.contains("PAK") ? R.drawable.pakistan : str.contains("AUS") ? R.drawable.australia : str.contains("SL") ? R.drawable.sri_lanka : str.contains("BAN") ? R.drawable.bangladesh : str.contains("ENG") ? R.drawable.england : str.contains("WI") ? R.drawable.west_indies : str.contains("RSA") ? R.drawable.south_africa : str.contains("ZIM") ? R.drawable.zimbabwe : str.contains("NZ") ? R.drawable.new_zealand : str.contains("AFG") ? R.drawable.afghanistan : str.contains("ITA") ? R.drawable.italy : str.contains("BW") ? R.drawable.botswana : str.contains("BEL") ? R.drawable.belgium : str.contains("IRN") ? R.drawable.iran : str.contains("DEN") ? R.drawable.denmark : str.contains("SIN") ? R.drawable.singapore : str.contains("NAM") ? R.drawable.namibia : str.contains("UGA") ? R.drawable.uganda : str.contains("MLY") ? R.drawable.malaysia : str.contains("NEP") ? R.drawable.nepal : str.contains("GER") ? R.drawable.germany : str.contains("CAN") ? R.drawable.canada : str.contains("BER") ? R.drawable.bermuda : str.contains("NED") ? R.drawable.netherlands : str.contains("UAE") ? R.drawable.united_arab_emirates : str.contains("HK") ? R.drawable.hong_kong : str.contains("KEN") ? R.drawable.kenya : str.contains("USA") ? R.drawable.united_states : str.contains("SCO") ? R.drawable.scotland : str.contains("FIJI") ? R.drawable.fiji : str.contains("PNG") ? R.drawable.papua_new_guinea : str.contains("KUW") ? R.drawable.kuwait : str.contains("VAN") ? R.drawable.vanuatu : str.contains("OMAN") ? R.drawable.oman : str.contains("JER") ? R.drawable.jersey : R.drawable.question;
    }
}