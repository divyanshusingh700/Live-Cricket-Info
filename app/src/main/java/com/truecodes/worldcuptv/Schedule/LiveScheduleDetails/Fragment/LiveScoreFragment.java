package com.truecodes.worldcuptv.Schedule.LiveScheduleDetails.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.Timer;
import java.util.TimerTask;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class LiveScoreFragment extends Fragment {
    private LinearLayout batsmanHead;
    private LinearLayout bowlerHead;
    private String complete;
    private String firstBatsman;
    private String firstBatsmanBall;
    private TextView firstBatsmanBallTextView;
    private String firstBatsmanFore;
    private TextView firstBatsmanForeTextView;
    private LinearLayout firstBatsmanLayout;
    private TextView firstBatsmanNameTextView;
    private String firstBatsmanRuns;
    private TextView firstBatsmanRunsTextView;
    private String firstBatsmanSR;
    private TextView firstBatsmanSRTextView;
    private String firstBatsmanSix;
    private TextView firstBatsmanSixTextView;
    private String firstBowlerEcon;
    private TextView firstBowlerEconTextView;
    private LinearLayout firstBowlerLayout;
    private String firstBowlerMaiden;
    private TextView firstBowlerMaidenTextView;
    private String firstBowlerName;
    private TextView firstBowlerNameTextView;
    private String firstBowlerOver;
    private TextView firstBowlerOverTextView;
    private String firstBowlerRuns;
    private TextView firstBowlerRunsTextView;
    private String firstBowlerWicket;
    private TextView firstBowlerWicketTextView;
    private CircleImageView firstImageView;
    private TextView firstTeamName;
    private String lastFiveOver;
    private TextView lastFiveOverTextView;
    private String lastWicket;
    private TextView lastWicketTextview;
    private ConstraintLayout mainScorecardCardView;
    private ManOfTheMatchAdapter manOfTheMatchAdapter;
    private RelativeLayout manOfTheMatchLayout;
    private ProgressBar manOfTheMatchProgressBar;
    private RecyclerView manOfTheMatchRecyclerView;
    private ArrayList<ManOfTheMatch> manOfTheMatches = new ArrayList<>();
    private String needed;
    private TextView neededTextView;
    private String partnership;
    private TextView partnershipTextView;
    private ProgressBar progressBar;
    private String recent;
    private TextView recentTextView;
    private String score;
    private TextView scoreTextView;
    private String secondBatsman;
    private String secondBatsmanBall;
    private TextView secondBatsmanBallTextView;
    private String secondBatsmanFore;
    private TextView secondBatsmanForeTextView;
    private LinearLayout secondBatsmanLayout;
    private TextView secondBatsmanNameTextView;
    private String secondBatsmanRuns;
    private TextView secondBatsmanRunsTextView;
    private String secondBatsmanSR;
    private TextView secondBatsmanSRTextView;
    private String secondBatsmanSix;
    private TextView secondBatsmanSixTextView;
    private String secondBowlerEcon;
    private TextView secondBowlerEconTextView;
    private LinearLayout secondBowlerLayout;
    private String secondBowlerMaiden;
    private TextView secondBowlerMaidenTextView;
    private String secondBowlerName;
    private TextView secondBowlerNameTextView;
    private String secondBowlerOver;
    private TextView secondBowlerOverTextView;
    private String secondBowlerRuns;
    private TextView secondBowlerRunsTextView;
    private String secondBowlerWicket;
    private TextView secondBowlerWicketTextView;
    private CircleImageView secondImageView;
    private String secondScore;
    private TextView secondScoreTextView;
    private TextView secondTeamName;
    private String teamAName;
    private String teamBName;
    private String toss;
    private TextView tossTextView;
    private String url;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_live_score, viewGroup, false);
        if (getArguments() != null) {
            this.teamAName = getArguments().getString("teamAName");
            this.teamBName = getArguments().getString("teamBName");
            this.url = getArguments().getString(ImagesContract.URL);
        }
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.firstTeamName = (TextView) inflate.findViewById(R.id.firstTeamNameId);
        this.secondTeamName = (TextView) inflate.findViewById(R.id.secondTeamNameId);
        this.firstImageView = (CircleImageView) inflate.findViewById(R.id.firstTeamImageViewId);
        this.secondImageView = (CircleImageView) inflate.findViewById(R.id.secondImageViewId);
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.mainScorecardCardView);
        this.mainScorecardCardView = constraintLayout;
        constraintLayout.setVisibility(View.GONE);
        String str = this.teamAName;
        if (str != null) {
            this.firstTeamName.setText(str);
            this.firstImageView.setImageResource(setUpImage(this.teamAName.trim()));
        }
        String str2 = this.teamBName;
        if (str2 != null) {
            this.secondTeamName.setText(str2);
            this.secondImageView.setImageResource(setUpImage(this.teamBName.trim()));
        }
        TextView textView = inflate.findViewById(R.id.firstScore);
        this.scoreTextView = textView;
        textView.setVisibility(View.GONE);
        TextView textView2 = (TextView) inflate.findViewById(R.id.textView10);
        this.secondScoreTextView = textView2;
        textView2.setVisibility(View.GONE);
        TextView textView3 = inflate.findViewById(R.id.dateTextViewId);
        this.neededTextView = textView3;
        textView3.setVisibility(View.GONE);
        LinearLayout linearLayout =  inflate.findViewById(R.id.batsmanHead);
        this.batsmanHead = linearLayout;
        linearLayout.setVisibility(View.GONE);
        LinearLayout linearLayout2 = inflate.findViewById(R.id.firstBatsmanLayout);
        this.firstBatsmanLayout = linearLayout2;
        linearLayout2.setVisibility(View.GONE);
        LinearLayout linearLayout3 = inflate.findViewById(R.id.secondBatsmanLayout);
        this.secondBatsmanLayout = linearLayout3;
        linearLayout3.setVisibility(View.GONE);
        this.firstBatsmanNameTextView = inflate.findViewById(R.id.firstBatsmanName);
        this.firstBatsmanRunsTextView = inflate.findViewById(R.id.firstBatsmanRuns);
        this.firstBatsmanBallTextView = inflate.findViewById(R.id.firstBatsmanBals);
        this.firstBatsmanForeTextView = inflate.findViewById(R.id.firstBatsmanFore);
        this.firstBatsmanSixTextView = inflate.findViewById(R.id.firstBatsmanSix);
        this.firstBatsmanSRTextView = inflate.findViewById(R.id.firstBatsmanSR);
        this.secondBatsmanNameTextView =   inflate.findViewById(R.id.secondBatsmanName);
        this.secondBatsmanRunsTextView =   inflate.findViewById(R.id.secondBatsmanRuns);
        this.secondBatsmanBallTextView =   inflate.findViewById(R.id.secondBatsmanBall);
        this.secondBatsmanForeTextView =   inflate.findViewById(R.id.secondBatsmanFore);
        this.secondBatsmanSixTextView =   inflate.findViewById(R.id.secondBatsmanSix);
        this.secondBatsmanSRTextView =   inflate.findViewById(R.id.secondBatsmanSR);
        LinearLayout linearLayout4 =  inflate.findViewById(R.id.bowlerHead);
        this.bowlerHead = linearLayout4;
        linearLayout4.setVisibility(View.GONE);
        LinearLayout linearLayout5 = inflate.findViewById(R.id.firstBowlerLayout);
        this.firstBowlerLayout = linearLayout5;
        linearLayout5.setVisibility(View.GONE);
        LinearLayout linearLayout6 =  inflate.findViewById(R.id.secondBowlerLayout);
        this.secondBowlerLayout = linearLayout6;
        linearLayout6.setVisibility(View.GONE);
        this.firstBowlerNameTextView = inflate.findViewById(R.id.firstBowlerName);
        this.firstBowlerOverTextView = inflate.findViewById(R.id.firstBowlerOver);
        this.firstBowlerMaidenTextView = inflate.findViewById(R.id.firstBowlerMaiden);
        this.firstBowlerRunsTextView =   inflate.findViewById(R.id.firstBowlerRuns);
        this.firstBowlerWicketTextView =   inflate.findViewById(R.id.firstBowlerWicket);
        this.firstBowlerEconTextView = inflate.findViewById(R.id.firstBowlerEcon);
        this.secondBowlerNameTextView =  inflate.findViewById(R.id.secondBowlerName);
        this.secondBowlerOverTextView = inflate.findViewById(R.id.secondBowlerOver);
        this.secondBowlerMaidenTextView = inflate.findViewById(R.id.secondBowlerMaiden);
        this.secondBowlerRunsTextView =   inflate.findViewById(R.id.secondBowlerRuns);
        this.secondBowlerWicketTextView = inflate.findViewById(R.id.secondBowlerWicket);
        this.secondBowlerEconTextView =   inflate.findViewById(R.id.secondBowlerEcon);
        TextView textView4 =  inflate.findViewById(R.id.textView3);
        this.recentTextView = textView4;
        textView4.setVisibility(View.GONE);
        TextView textView5 = (TextView) inflate.findViewById(R.id.textView);
        this.partnershipTextView = textView5;
        textView5.setVisibility(View.GONE);
        TextView textView6 =  inflate.findViewById(R.id.textView6);
        this.lastWicketTextview = textView6;
        textView6.setVisibility(View.GONE);
        TextView textView7 =   inflate.findViewById(R.id.textView8);
        this.lastFiveOverTextView = textView7;
        textView7.setVisibility(View.GONE);
        TextView textView8 =  inflate.findViewById(R.id.textView9);
        this.tossTextView = textView8;
        textView8.setVisibility(View.GONE);
        new Timer().scheduleAtFixedRate(new TimerTask() { // from class: com.digital.livecricketapp.Schedule.LiveScheduleDetails.Fragment.LiveScoreFragment.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                new GetLiveSchedule().execute(new Void[0]);
            }
        }, 500L, 1000L);
        SetUpManOfTheMatch(inflate);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetLiveSchedule().execute(new Void[0]);
        new GetManOfTheMatchContent().execute(new Void[0]);
    }


    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r7) {
            super.onPostExecute(r7);
            LiveScoreFragment.this.progressBar.setVisibility(View.GONE);
            LiveScoreFragment.this.mainScorecardCardView.setVisibility(View.VISIBLE);
            if (LiveScoreFragment.this.score != null) {
                if (!LiveScoreFragment.this.score.equals("")) {
                    LiveScoreFragment.this.scoreTextView.setText(LiveScoreFragment.this.score);
                    LiveScoreFragment.this.scoreTextView.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.mainScorecardCardView.setVisibility(View.VISIBLE);
                }
            } else {
                LiveScoreFragment.this.scoreTextView.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.secondScore != null) {
                if (!LiveScoreFragment.this.secondScore.equals("")) {
                    LiveScoreFragment.this.secondScoreTextView.setText(LiveScoreFragment.this.secondScore);
                    LiveScoreFragment.this.secondScoreTextView.setVisibility( View.VISIBLE);
                    LiveScoreFragment.this.mainScorecardCardView.setVisibility(View.VISIBLE);
                }
            } else {
                LiveScoreFragment.this.secondScoreTextView.setVisibility( View.GONE);
            }
            if (LiveScoreFragment.this.needed != null) {
                if (!LiveScoreFragment.this.needed.equals("")) {
                    LiveScoreFragment.this.neededTextView.setText(LiveScoreFragment.this.needed);
                    LiveScoreFragment.this.neededTextView.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.mainScorecardCardView.setVisibility(View.VISIBLE);
                } else if (LiveScoreFragment.this.complete != null) {
                    if (!LiveScoreFragment.this.complete.equals("")) {
                        LiveScoreFragment.this.neededTextView.setVisibility(View.VISIBLE);
                        LiveScoreFragment.this.neededTextView.setText(LiveScoreFragment.this.complete);
                    } else {
                        LiveScoreFragment.this.neededTextView.setVisibility(View.GONE);
                    }
                }
            } else if (LiveScoreFragment.this.complete != null) {
                if (!LiveScoreFragment.this.complete.equals("")) {
                    LiveScoreFragment.this.neededTextView.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.neededTextView.setText(LiveScoreFragment.this.complete);
                } else {
                    LiveScoreFragment.this.neededTextView.setVisibility(View.GONE);
                }
            }
            if (LiveScoreFragment.this.firstBatsman != null || LiveScoreFragment.this.firstBatsmanRuns != null || LiveScoreFragment.this.firstBatsmanBall != null || LiveScoreFragment.this.firstBatsmanFore != null || LiveScoreFragment.this.firstBatsmanSix != null || LiveScoreFragment.this.firstBatsmanSR != null) {
                if (!LiveScoreFragment.this.firstBatsman.equals("")) {
                    LiveScoreFragment.this.batsmanHead.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.firstBatsmanLayout.setVisibility(View.VISIBLE);
                    TextView textView = LiveScoreFragment.this.firstBatsmanNameTextView;
                    textView.setText(LiveScoreFragment.this.firstBatsman + " *");
                    LiveScoreFragment.this.firstBatsmanRunsTextView.setText(LiveScoreFragment.this.firstBatsmanRuns);
                    LiveScoreFragment.this.firstBatsmanBallTextView.setText(LiveScoreFragment.this.firstBatsmanBall);
                    LiveScoreFragment.this.firstBatsmanForeTextView.setText(LiveScoreFragment.this.firstBatsmanFore);
                    LiveScoreFragment.this.firstBatsmanSixTextView.setText(LiveScoreFragment.this.firstBatsmanSix);
                    LiveScoreFragment.this.firstBatsmanSRTextView.setText(LiveScoreFragment.this.firstBatsmanSR);
                } else {
                    LiveScoreFragment.this.batsmanHead.setVisibility(View.GONE);
                    LiveScoreFragment.this.firstBatsmanLayout.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.firstBatsmanLayout.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.secondBatsman != null || LiveScoreFragment.this.secondBatsmanRuns != null || LiveScoreFragment.this.secondBatsmanBall != null || LiveScoreFragment.this.secondBatsmanFore != null || LiveScoreFragment.this.secondBatsmanSix != null || LiveScoreFragment.this.secondBatsmanSR != null) {
                if (!LiveScoreFragment.this.secondBatsman.equals("Bowler")) {
                    if (!LiveScoreFragment.this.secondBatsman.equals("")) {
                        LiveScoreFragment.this.batsmanHead.setVisibility(View.VISIBLE);
                        LiveScoreFragment.this.secondBatsmanLayout.setVisibility(View.VISIBLE);
                        LiveScoreFragment.this.secondBatsmanNameTextView.setText(LiveScoreFragment.this.secondBatsman);
                        LiveScoreFragment.this.secondBatsmanRunsTextView.setText(LiveScoreFragment.this.secondBatsmanRuns);
                        LiveScoreFragment.this.secondBatsmanBallTextView.setText(LiveScoreFragment.this.secondBatsmanBall);
                        LiveScoreFragment.this.secondBatsmanForeTextView.setText(LiveScoreFragment.this.secondBatsmanFore);
                        LiveScoreFragment.this.secondBatsmanSixTextView.setText(LiveScoreFragment.this.secondBatsmanSix);
                        LiveScoreFragment.this.secondBatsmanSRTextView.setText(LiveScoreFragment.this.secondBatsmanSR);
                    }
                } else {
                    LiveScoreFragment.this.secondBatsmanLayout.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.secondBatsmanLayout.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.firstBowlerName != null || LiveScoreFragment.this.firstBowlerOver != null || LiveScoreFragment.this.firstBowlerMaiden != null || LiveScoreFragment.this.firstBowlerRuns != null || LiveScoreFragment.this.firstBowlerWicket != null || LiveScoreFragment.this.firstBowlerEcon != null) {
                if (!LiveScoreFragment.this.firstBowlerName.equals("")) {
                    LiveScoreFragment.this.bowlerHead.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.firstBowlerLayout.setVisibility(View.VISIBLE);
                    TextView textView2 = LiveScoreFragment.this.firstBowlerNameTextView;
                    textView2.setText(LiveScoreFragment.this.firstBowlerName + " *");
                    LiveScoreFragment.this.firstBowlerOverTextView.setText(LiveScoreFragment.this.firstBowlerOver);
                    LiveScoreFragment.this.firstBowlerMaidenTextView.setText(LiveScoreFragment.this.firstBowlerMaiden);
                    LiveScoreFragment.this.firstBowlerRunsTextView.setText(LiveScoreFragment.this.firstBowlerRuns);
                    LiveScoreFragment.this.firstBowlerWicketTextView.setText(LiveScoreFragment.this.firstBowlerWicket);
                    LiveScoreFragment.this.firstBowlerEconTextView.setText(LiveScoreFragment.this.firstBowlerEcon);
                } else {
                    LiveScoreFragment.this.bowlerHead.setVisibility(View.GONE);
                    LiveScoreFragment.this.firstBowlerLayout.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.firstBowlerLayout.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.secondBowlerName != null || LiveScoreFragment.this.secondBowlerOver != null || LiveScoreFragment.this.secondBowlerMaiden != null || LiveScoreFragment.this.secondBowlerRuns != null || LiveScoreFragment.this.secondBowlerWicket != null || LiveScoreFragment.this.secondBowlerEcon != null) {
                if (!LiveScoreFragment.this.secondBowlerName.equals("")) {
                    LiveScoreFragment.this.bowlerHead.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.secondBowlerLayout.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.secondBowlerNameTextView.setText(LiveScoreFragment.this.secondBowlerName);
                    LiveScoreFragment.this.secondBowlerOverTextView.setText(LiveScoreFragment.this.secondBowlerOver);
                    LiveScoreFragment.this.secondBowlerMaidenTextView.setText(LiveScoreFragment.this.secondBowlerMaiden);
                    LiveScoreFragment.this.secondBowlerRunsTextView.setText(LiveScoreFragment.this.secondBowlerRuns);
                    LiveScoreFragment.this.secondBowlerWicketTextView.setText(LiveScoreFragment.this.secondBowlerWicket);
                    LiveScoreFragment.this.secondBowlerEconTextView.setText(LiveScoreFragment.this.secondBowlerEcon);
                } else {
                    LiveScoreFragment.this.secondBowlerLayout.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.secondBowlerLayout.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.recent != null) {
                if (!LiveScoreFragment.this.recent.equals("")) {
                    LiveScoreFragment.this.recentTextView.setText(LiveScoreFragment.this.recent);
                    LiveScoreFragment.this.recentTextView.setVisibility(View.VISIBLE);
                } else {
                    LiveScoreFragment.this.recentTextView.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.recentTextView.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.partnership != null) {
                if (!LiveScoreFragment.this.partnership.equals("")) {
                    LiveScoreFragment.this.partnershipTextView.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.partnershipTextView.setText(LiveScoreFragment.this.partnership);
                } else {
                    LiveScoreFragment.this.partnershipTextView.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.partnershipTextView.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.lastWicket != null) {
                if (!LiveScoreFragment.this.lastWicket.equals("")) {
                    LiveScoreFragment.this.lastWicketTextview.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.lastWicketTextview.setText(LiveScoreFragment.this.lastWicket);
                } else {
                    LiveScoreFragment.this.lastWicketTextview.setVisibility(View.GONE);
                }
            } else {
                LiveScoreFragment.this.lastWicketTextview.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.lastFiveOver != null) {
                if (!LiveScoreFragment.this.lastWicket.equals("")) {
                    LiveScoreFragment.this.lastFiveOverTextView.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.lastFiveOverTextView.setText(LiveScoreFragment.this.lastFiveOver);
                } else {
                    LiveScoreFragment.this.lastFiveOverTextView.setVisibility( View.GONE);
                }
            } else {
                LiveScoreFragment.this.lastFiveOverTextView.setVisibility(View.GONE);
            }
            if (LiveScoreFragment.this.toss != null) {
                if (!LiveScoreFragment.this.toss.equals("")) {
                    LiveScoreFragment.this.tossTextView.setVisibility(View.VISIBLE);
                    LiveScoreFragment.this.tossTextView.setText(LiveScoreFragment.this.toss);
                    return;
                }
                LiveScoreFragment.this.tossTextView.setVisibility(View.GONE);
                return;
            }
            LiveScoreFragment.this.tossTextView.setVisibility(View.GONE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(LiveScoreFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").timeout(10000).get().select("div.cb-nws-lft-col");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    LiveScoreFragment.this.score = select.get(i).select("div.cb-col-scores").select("div.cb-scrs-wrp").select("div").eq(1).text();
                    LiveScoreFragment.this.secondScore = select.get(i).select("div.cb-col-scores").select("div.cb-scrs-wrp").select("div").eq(2).text();
                    LiveScoreFragment.this.needed = select.get(i).select("div.cb-col-scores").select("div.cb-scrs-wrp").select("div").eq(3).text();
                    LiveScoreFragment.this.complete = select.get(i).select("div.cb-text-complete").text();
                    Elements eq = select.get(i).select("div.cb-col-67").select("div.cb-min-inf").select("div.cb-col").eq(7);
                    LiveScoreFragment.this.firstBatsman = eq.select("div.cb-col").eq(1).text();
                    LiveScoreFragment.this.firstBatsmanRuns = eq.select("div.cb-col").eq(2).text();
                    LiveScoreFragment.this.firstBatsmanBall = eq.select("div.cb-col").eq(3).text();
                    LiveScoreFragment.this.firstBatsmanFore = eq.select("div.cb-col").eq(4).text();
                    LiveScoreFragment.this.firstBatsmanSix = eq.select("div.cb-col").eq(5).text();
                    LiveScoreFragment.this.firstBatsmanSR = eq.select("div.cb-col").eq(6).text();
                    Elements eq2 = select.get(i).select("div.cb-col-67").select("div.cb-min-inf").select("div.cb-col").eq(14);
                    LiveScoreFragment.this.secondBatsman = eq2.select("div.cb-col").eq(1).text();
                    LiveScoreFragment.this.secondBatsmanRuns = eq2.select("div.cb-col").eq(2).text();
                    LiveScoreFragment.this.secondBatsmanBall = eq2.select("div.cb-col").eq(3).text();
                    LiveScoreFragment.this.secondBatsmanFore = eq2.select("div.cb-col").eq(4).text();
                    LiveScoreFragment.this.secondBatsmanSix = eq2.select("div.cb-col").eq(5).text();
                    LiveScoreFragment.this.secondBatsmanSR = eq2.select("div.cb-col").eq(6).text();
                    Elements eq3 = select.get(i).select("div.cb-col-67").select("div.cb-min-inf").select("div.cb-col").eq(28);
                    LiveScoreFragment.this.firstBowlerName = eq3.select("div.cb-col").eq(1).text();
                    LiveScoreFragment.this.firstBowlerOver = eq3.select("div.cb-col").eq(2).text();
                    LiveScoreFragment.this.firstBowlerMaiden = eq3.select("div.cb-col").eq(3).text();
                    LiveScoreFragment.this.firstBowlerRuns = eq3.select("div.cb-col").eq(4).text();
                    LiveScoreFragment.this.firstBowlerWicket = eq3.select("div.cb-col").eq(5).text();
                    LiveScoreFragment.this.firstBowlerEcon = eq3.select("div.cb-col").eq(6).text();
                    Elements eq4 = select.get(i).select("div.cb-col-67").select("div.cb-min-inf").select("div.cb-col").eq(35);
                    LiveScoreFragment.this.secondBowlerName = eq4.select("div.cb-col").eq(1).text();
                    LiveScoreFragment.this.secondBowlerOver = eq4.select("div.cb-col").eq(2).text();
                    LiveScoreFragment.this.secondBowlerMaiden = eq4.select("div.cb-col").eq(3).text();
                    LiveScoreFragment.this.secondBowlerRuns = eq4.select("div.cb-col").eq(4).text();
                    LiveScoreFragment.this.secondBowlerWicket = eq4.select("div.cb-col").eq(5).text();
                    LiveScoreFragment.this.secondBowlerEcon = eq4.select("div.cb-col").eq(6).text();
                    LiveScoreFragment.this.recent = select.get(i).select("div.cb-min-rcnt").text();
                    LiveScoreFragment.this.partnership = select.get(i).select("div.cb-key-lst-wrp").select("div.cb-min-itm-rw").eq(0).text();
                    LiveScoreFragment.this.lastWicket = select.get(i).select("div.cb-key-lst-wrp").select("div.cb-min-itm-rw").eq(1).text();
                    LiveScoreFragment.this.lastFiveOver = select.get(i).select("div.cb-key-lst-wrp").select("div.cb-min-itm-rw").eq(2).text();
                    LiveScoreFragment.this.toss = select.get(i).select("div.cb-key-lst-wrp").select("div.cb-min-itm-rw").eq(3).text();
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
        RecyclerView recyclerView  =view.findViewById(R.id.manOfTheMatchRecyclerView);
        this.manOfTheMatchRecyclerView = recyclerView;
        recyclerView.setVisibility(View.GONE);
        this.manOfTheMatchRecyclerView.setHasFixedSize(true);
        this.manOfTheMatchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ManOfTheMatchAdapter manOfTheMatchAdapter = new ManOfTheMatchAdapter(this.manOfTheMatches, getContext(), getActivity());
        this.manOfTheMatchAdapter = manOfTheMatchAdapter;
        this.manOfTheMatchRecyclerView.setAdapter(manOfTheMatchAdapter);
    }

    /* loaded from: classes.dex */
    private class GetManOfTheMatchContent extends AsyncTask<Void, Void, Void> {
        private GetManOfTheMatchContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LiveScoreFragment.this.manOfTheMatches.clear();
            LiveScoreFragment.this.manOfTheMatchAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            LiveScoreFragment.this.manOfTheMatchAdapter.notifyDataSetChanged();
            if (LiveScoreFragment.this.manOfTheMatchAdapter.getItemCount() == 0) {
                LiveScoreFragment.this.manOfTheMatchRecyclerView.setVisibility(View.GONE);
            } else {
                LiveScoreFragment.this.manOfTheMatchRecyclerView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(LiveScoreFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-min-comp").select("div.cb-col-50");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("a.cb-link-undrln").text();
                    LiveScoreFragment.this.manOfTheMatches.add(new ManOfTheMatch(select.get(i).select("span.cb-text-gray").text(), text, select.get(i).select("a.cb-link-undrln").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private int setUpImage(String str) {
        return str.contains("India") ? R.drawable.india : str.contains("Ireland") ? R.drawable.ireland : str.contains("Pakistan") ? R.drawable.pakistan : str.contains("Australia") ? R.drawable.australia : str.contains("Sri Lanka") ? R.drawable.sri_lanka : str.contains("Bangladesh") ? R.drawable.bangladesh : str.contains("England") ? R.drawable.england : str.contains("West Indies") ? R.drawable.west_indies : str.contains("South Africa") ? R.drawable.south_africa : str.contains("Zimbabwe") ? R.drawable.zimbabwe : str.contains("New Zealand") ? R.drawable.new_zealand : str.contains("Afghanistan") ? R.drawable.afghanistan : str.contains("Italy") ? R.drawable.italy : str.contains("Botswana") ? R.drawable.botswana : str.contains("Belgium") ? R.drawable.belgium : str.contains("Iran") ? R.drawable.iran : str.contains("Denmark") ? R.drawable.denmark : str.contains("Singapore") ? R.drawable.singapore : str.contains("Namibia") ? R.drawable.namibia : str.contains("Uganda") ? R.drawable.uganda : str.contains("Malaysia") ? R.drawable.malaysia : str.contains("Nepal") ? R.drawable.nepal : str.contains("Germany") ? R.drawable.germany : str.contains("Canada") ? R.drawable.canada : str.contains("Bermuda") ? R.drawable.bermuda : str.contains("Netherlands") ? R.drawable.netherlands : str.contains("United Arab Emirates") ? R.drawable.united_arab_emirates : str.contains("Hong Kong") ? R.drawable.hong_kong : str.contains("Kenya") ? R.drawable.kenya : str.contains("United States") ? R.drawable.united_states : str.contains("Scotland") ? R.drawable.scotland : str.contains("Fiji") ? R.drawable.fiji : str.contains("Papua New Guinea") ? R.drawable.papua_new_guinea : str.contains("Kuwait") ? R.drawable.kuwait : str.contains("Vanuatu") ? R.drawable.vanuatu : str.contains("Oman") ? R.drawable.oman : str.contains("Jersey") ? R.drawable.jersey : str.contains("IND") ? R.drawable.india : str.contains("IRE") ? R.drawable.ireland : str.contains("PAK") ? R.drawable.pakistan : str.contains("AUS") ? R.drawable.australia : str.contains("SL") ? R.drawable.sri_lanka : str.contains("BAN") ? R.drawable.bangladesh : str.contains("ENG") ? R.drawable.england : str.contains("WI") ? R.drawable.west_indies : str.contains("RSA") ? R.drawable.south_africa : str.contains("ZIM") ? R.drawable.zimbabwe : str.contains("NZ") ? R.drawable.new_zealand : str.contains("AFG") ? R.drawable.afghanistan : str.contains("ITA") ? R.drawable.italy : str.contains("BW") ? R.drawable.botswana : str.contains("BEL") ? R.drawable.belgium : str.contains("IRN") ? R.drawable.iran : str.contains("DEN") ? R.drawable.denmark : str.contains("SIN") ? R.drawable.singapore : str.contains("NAM") ? R.drawable.namibia : str.contains("UGA") ? R.drawable.uganda : str.contains("MLY") ? R.drawable.malaysia : str.contains("NEP") ? R.drawable.nepal : str.contains("GER") ? R.drawable.germany : str.contains("CAN") ? R.drawable.canada : str.contains("BER") ? R.drawable.bermuda : str.contains("NED") ? R.drawable.netherlands : str.contains("UAE") ? R.drawable.united_arab_emirates : str.contains("HK") ? R.drawable.hong_kong : str.contains("KEN") ? R.drawable.kenya : str.contains("USA") ? R.drawable.united_states : str.contains("SCO") ? R.drawable.scotland : str.contains("FIJI") ? R.drawable.fiji : str.contains("PNG") ? R.drawable.papua_new_guinea : str.contains("KUW") ? R.drawable.kuwait : str.contains("VAN") ? R.drawable.vanuatu : str.contains("OMAN") ? R.drawable.oman : str.contains("JER") ? R.drawable.jersey : R.drawable.question;
    }
}