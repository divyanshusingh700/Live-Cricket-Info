package com.truecodes.worldcuptv.Schedule.UpcomingScheduleDetails;



import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class UpcomingScheduleDetailsScreen extends AppCompatActivity {
    AdManager adManager;
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
    private CircleImageView firstTeamImageViewId;
    private String firstTeamName;
    private TextView firstTeamNameId;
    private TextView firstTeamNameTextView;
    FrameLayout frameLayout;
    private ConstraintLayout mainScorecardCardView;
    private TextView matchCapacityHead;
    private TextView matchCapacityText;
    private TextView matchCityHead;
    private TextView matchCityText;
    private TextView matchDateHead;
    private TextView matchDateText;
    private CardView matchInfoLayout;
    private LinearLayout matchLayout;
    private TextView matchStadiumHead;
    private TextView matchStadiumText;
    private TextView matchTitleHead;
    private TextView matchTitleText;
    private TextView matchTossHead;
    private TextView matchTossText;
    private CircleImageView secondImageView;
    private CircleImageView secondImageViewId;
    private String secondTeamName;
    private TextView secondTeamNameId;
    private TextView secondTeamNameTextView;
    private String stadiumHead;
    private LinearLayout stadiumLayout;
    private String stadiumText;
    private String teamAName;
    private String teamBName;
    private String titleHead;
    private String titleText;
    private String tossHead;
    private LinearLayout tossLayout;
    private String tossText;
    String url;
    private CardView venueInfoLayout;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_upcoming_schedule_details_screen);
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(this, getLifecycle());
        builder.getDialogProperties().setConnectionCallback(new ConnectionCallback() {
            @Override
            public void hasActiveConnection(boolean z) {
            }
        });
        builder.build();
        this.frameLayout = findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        this.teamAName = getIntent().getStringExtra("teamAName");
        this.teamBName = getIntent().getStringExtra("teamBName");
        this.url = getIntent().getStringExtra(ImagesContract.URL);
        getWindow().getDecorView().setSystemUiVisibility(8192);
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_color));
        }
        this.firstTeamNameId = findViewById(R.id.firstTeamNameId);
        this.secondTeamNameId =   findViewById(R.id.secondTeamNameId);
        this.firstTeamImageViewId = findViewById(R.id.firstTeamImageViewId);
        this.secondImageViewId =   findViewById(R.id.secondImageViewId);
        ConstraintLayout constraintLayout =   findViewById(R.id.mainScorecardCardView);
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
        SetUpMatchInfo();
        SetUpVenueInfo();
    }


    @Override
    public void onStart() {
        super.onStart();
        new GetMatchInfoContent().execute(new Void[0]);
        new GetVenueContent().execute(new Void[0]);
    }

    private void SetUpMatchInfo() {
        CardView cardView =   findViewById(R.id.matchInfoLayout);
        this.matchInfoLayout = cardView;
        cardView.setVisibility(View.GONE);
        this.matchTitleHead =   findViewById(R.id.matchTitleHead);
        this.matchTitleText =   findViewById(R.id.matchTitleText);
        this.matchDateHead =   findViewById(R.id.matchDateHead);
        this.matchDateText =   findViewById(R.id.matchDateText);
        this.matchTossHead =   findViewById(R.id.matchTossHead);
        this.matchTossText =   findViewById(R.id.matchTossText);
        this.matchLayout =   findViewById(R.id.matchTitleLayout);
        this.dateLayout =   findViewById(R.id.matchDateLayout);
        this.tossLayout =   findViewById(R.id.matchTossLayout);
        this.matchLayout.setVisibility(View.GONE);
        this.dateLayout.setVisibility(View.GONE);
        this.tossLayout.setVisibility(View.GONE);
    }


    @Override
    public void onPause() {
        super.onPause();
        this.adManager.destroyBanner();
    }


    @Override
    public void onResume() {
        super.onResume();
        this.adManager.loadBanner(this.frameLayout);
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
            if (UpcomingScheduleDetailsScreen.this.titleHead != null || UpcomingScheduleDetailsScreen.this.titleText != null) {
                UpcomingScheduleDetailsScreen.this.matchLayout.setVisibility(View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.matchInfoLayout.setVisibility(View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.matchTitleHead.setText(UpcomingScheduleDetailsScreen.this.titleHead);
                UpcomingScheduleDetailsScreen.this.matchTitleText.setText(UpcomingScheduleDetailsScreen.this.titleText);
            }
            if (UpcomingScheduleDetailsScreen.this.dateHead != null || UpcomingScheduleDetailsScreen.this.dateText != null) {
                UpcomingScheduleDetailsScreen.this.dateLayout.setVisibility(View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.matchInfoLayout.setVisibility(View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.matchDateHead.setText(UpcomingScheduleDetailsScreen.this.dateHead);
                if (UpcomingScheduleDetailsScreen.this.dateText.length() == 13) {
                    TextView textView = UpcomingScheduleDetailsScreen.this.matchDateText;
                    UpcomingScheduleDetailsScreen upcomingScheduleDetailsScreen = UpcomingScheduleDetailsScreen.this;
                    textView.setText(upcomingScheduleDetailsScreen.getDateTime(Long.valueOf(upcomingScheduleDetailsScreen.dateText)));
                } else {
                    UpcomingScheduleDetailsScreen.this.matchDateText.setText(UpcomingScheduleDetailsScreen.this.dateText);
                }
            }
            if (UpcomingScheduleDetailsScreen.this.tossHead == null && UpcomingScheduleDetailsScreen.this.tossText == null) {
                return;
            }
            UpcomingScheduleDetailsScreen.this.matchInfoLayout.setVisibility(View.VISIBLE);
            UpcomingScheduleDetailsScreen.this.tossLayout.setVisibility(View.VISIBLE);
            UpcomingScheduleDetailsScreen.this.matchTossHead.setText(UpcomingScheduleDetailsScreen.this.tossHead);
            UpcomingScheduleDetailsScreen.this.matchTossText.setText(UpcomingScheduleDetailsScreen.this.tossText);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(UpcomingScheduleDetailsScreen.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    UpcomingScheduleDetailsScreen.this.titleHead = eq.get(i).select("div.cb-mat-fct-itm").eq(0).text();
                    UpcomingScheduleDetailsScreen.this.titleText = eq.get(i).select("div.cb-mat-fct-itm").eq(1).text();
                    UpcomingScheduleDetailsScreen.this.dateHead = eq.get(i).select("div.cb-mat-fct-itm").eq(2).text();
                    UpcomingScheduleDetailsScreen.this.dateText = eq.get(i).select("div.cb-mat-fct-itm").eq(3).select("span").attr("timestamp");
                    UpcomingScheduleDetailsScreen.this.tossHead = eq.get(i).select("div.cb-mat-fct-itm").eq(6).text();
                    UpcomingScheduleDetailsScreen.this.tossText = eq.get(i).select("div.cb-mat-fct-itm").eq(7).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }

    private void SetUpVenueInfo() {
        CardView cardView = (CardView) findViewById(R.id.venueInfoLayout);
        this.venueInfoLayout = cardView;
        cardView.setVisibility(View.GONE);
        this.matchStadiumHead =   findViewById(R.id.matchStadiumHead);
        this.matchStadiumText =   findViewById(R.id.matchStadiumText);
        this.matchCityHead =   findViewById(R.id.matchCityHead);
        this.matchCityText =   findViewById(R.id.matchCityText);
        this.matchCapacityHead =   findViewById(R.id.matchCapacityHead);
        this.matchCapacityText =   findViewById(R.id.matchCapacityText);
        this.stadiumLayout =   findViewById(R.id.matchStadiumLayout);
        this.cityLayout =   findViewById(R.id.matchCityLayout);
        this.capacityLayout =   findViewById(R.id.matchCapacityLayout);
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
            if (UpcomingScheduleDetailsScreen.this.stadiumHead != null || UpcomingScheduleDetailsScreen.this.stadiumText != null) {
                UpcomingScheduleDetailsScreen.this.venueInfoLayout.setVisibility( View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.stadiumLayout.setVisibility( View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.matchStadiumHead.setText(UpcomingScheduleDetailsScreen.this.stadiumHead);
                UpcomingScheduleDetailsScreen.this.matchStadiumText.setText(UpcomingScheduleDetailsScreen.this.stadiumText);
            }
            if (UpcomingScheduleDetailsScreen.this.cityHead != null || UpcomingScheduleDetailsScreen.this.cityText != null) {
                UpcomingScheduleDetailsScreen.this.venueInfoLayout.setVisibility( View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.cityLayout.setVisibility(View.VISIBLE);
                UpcomingScheduleDetailsScreen.this.matchCityHead.setText(UpcomingScheduleDetailsScreen.this.cityHead);
                UpcomingScheduleDetailsScreen.this.matchCityText.setText(UpcomingScheduleDetailsScreen.this.cityText);
            }
            if (UpcomingScheduleDetailsScreen.this.capacityHead == null && UpcomingScheduleDetailsScreen.this.capacityText == null) {
                return;
            }
            UpcomingScheduleDetailsScreen.this.venueInfoLayout.setVisibility(View.VISIBLE);
            UpcomingScheduleDetailsScreen.this.capacityLayout.setVisibility(View.VISIBLE);
            UpcomingScheduleDetailsScreen.this.matchCapacityHead.setText(UpcomingScheduleDetailsScreen.this.capacityHead);
            UpcomingScheduleDetailsScreen.this.matchCapacityText.setText(UpcomingScheduleDetailsScreen.this.capacityText);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(UpcomingScheduleDetailsScreen.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(1);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    UpcomingScheduleDetailsScreen.this.stadiumHead = eq.get(i).select("div.cb-mat-fct-itm").eq(0).text();
                    UpcomingScheduleDetailsScreen.this.stadiumText = eq.get(i).select("div.cb-mat-fct-itm").eq(1).text();
                    UpcomingScheduleDetailsScreen.this.cityHead = eq.get(i).select("div.cb-mat-fct-itm").eq(2).text();
                    UpcomingScheduleDetailsScreen.this.cityText = eq.get(i).select("div.cb-mat-fct-itm").eq(3).text();
                    UpcomingScheduleDetailsScreen.this.capacityHead = eq.get(i).select("div.cb-mat-fct-itm").eq(4).text();
                    UpcomingScheduleDetailsScreen.this.capacityText = eq.get(i).select("div.cb-mat-fct-itm").eq(5).text();
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

    private int setUpImage(String str) {
        return str.contains("India") ? R.drawable.india : str.contains("Ireland") ? R.drawable.ireland : str.contains("Pakistan") ? R.drawable.pakistan : str.contains("Australia") ? R.drawable.australia : str.contains("Sri Lanka") ? R.drawable.sri_lanka : str.contains("Bangladesh") ? R.drawable.bangladesh : str.contains("England") ? R.drawable.england : str.contains("West Indies") ? R.drawable.west_indies : str.contains("South Africa") ? R.drawable.south_africa : str.contains("Zimbabwe") ? R.drawable.zimbabwe : str.contains("New Zealand") ? R.drawable.new_zealand : str.contains("Afghanistan") ? R.drawable.afghanistan : str.contains("Italy") ? R.drawable.italy : str.contains("Botswana") ? R.drawable.botswana : str.contains("Belgium") ? R.drawable.belgium : str.contains("Iran") ? R.drawable.iran : str.contains("Denmark") ? R.drawable.denmark : str.contains("Singapore") ? R.drawable.singapore : str.contains("Namibia") ? R.drawable.namibia : str.contains("Uganda") ? R.drawable.uganda : str.contains("Malaysia") ? R.drawable.malaysia : str.contains("Nepal") ? R.drawable.nepal : str.contains("Germany") ? R.drawable.germany : str.contains("Canada") ? R.drawable.canada : str.contains("Bermuda") ? R.drawable.bermuda : str.contains("Netherlands") ? R.drawable.netherlands : str.contains("United Arab Emirates") ? R.drawable.united_arab_emirates : str.contains("Hong Kong") ? R.drawable.hong_kong : str.contains("Kenya") ? R.drawable.kenya : str.contains("United States") ? R.drawable.united_states : str.contains("Scotland") ? R.drawable.scotland : str.contains("Fiji") ? R.drawable.fiji : str.contains("Papua New Guinea") ? R.drawable.papua_new_guinea : str.contains("Kuwait") ? R.drawable.kuwait : str.contains("Vanuatu") ? R.drawable.vanuatu : str.contains("Oman") ? R.drawable.oman : str.contains("Jersey") ? R.drawable.jersey : str.contains("IND") ? R.drawable.india : str.contains("IRE") ? R.drawable.ireland : str.contains("PAK") ? R.drawable.pakistan : str.contains("AUS") ? R.drawable.australia : str.contains("SL") ? R.drawable.sri_lanka : str.contains("BAN") ? R.drawable.bangladesh : str.contains("ENG") ? R.drawable.england : str.contains("WI") ? R.drawable.west_indies : str.contains("RSA") ? R.drawable.south_africa : str.contains("ZIM") ? R.drawable.zimbabwe : str.contains("NZ") ? R.drawable.new_zealand : str.contains("AFG") ? R.drawable.afghanistan : str.contains("ITA") ? R.drawable.italy : str.contains("BW") ? R.drawable.botswana : str.contains("BEL") ? R.drawable.belgium : str.contains("IRN") ? R.drawable.iran : str.contains("DEN") ? R.drawable.denmark : str.contains("SIN") ? R.drawable.singapore : str.contains("NAM") ? R.drawable.namibia : str.contains("UGA") ? R.drawable.uganda : str.contains("MLY") ? R.drawable.malaysia : str.contains("NEP") ? R.drawable.nepal : str.contains("GER") ? R.drawable.germany : str.contains("CAN") ? R.drawable.canada : str.contains("BER") ? R.drawable.bermuda : str.contains("NED") ? R.drawable.netherlands : str.contains("UAE") ? R.drawable.united_arab_emirates : str.contains("HK") ? R.drawable.hong_kong : str.contains("KEN") ? R.drawable.kenya : str.contains("USA") ? R.drawable.united_states : str.contains("SCO") ? R.drawable.scotland : str.contains("FIJI") ? R.drawable.fiji : str.contains("PNG") ? R.drawable.papua_new_guinea : str.contains("KUW") ? R.drawable.kuwait : str.contains("VAN") ? R.drawable.vanuatu : str.contains("OMAN") ? R.drawable.oman : str.contains("JER") ? R.drawable.jersey : R.drawable.question;
    }
}