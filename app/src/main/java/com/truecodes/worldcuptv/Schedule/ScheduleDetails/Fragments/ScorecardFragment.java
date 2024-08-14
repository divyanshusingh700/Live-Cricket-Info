package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Fragments;



import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters.BatsmanScorecardAdapter;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters.BowlerScorecardAdapter;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.ScorecardBatsman;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.ScorecardBowler;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class ScorecardFragment extends Fragment {
    private String A1InningsName;
    private String A1InningsScore;
    private String AInningsName;
    private String AInningsScore;
    private String B1InningsName;
    private String B1InningsScore;
    private String BInningsName;
    private String BInningsScore;
    private BatsmanScorecardAdapter a1BatsmanAdapter;
    private CardView a1BatsmanHead;
    private RecyclerView a1BatsmanRecycler;
    private ImageView a1BatsmanUpIcon;
    private BowlerScorecardAdapter a1BowlerAdapter;
    private CardView a1BowlerHead;
    private BatsmanScorecardAdapter aBatsmanAdapter;
    private CardView aBatsmanHead;
    private RecyclerView aBatsmanRecycler;
    private ImageView aBatsmanUpIcon;
    private BowlerScorecardAdapter aBowlerAdapter;
    private CardView aBowlerHead;
    private BatsmanScorecardAdapter b1BatsmanAdapter;
    private CardView b1BatsmanHead;
    private RecyclerView b1BatsmanRecycler;
    private ImageView b1BatsmanUpIcon;
    private BowlerScorecardAdapter b1BowlerAdapter;
    private CardView b1BowlerHead;
    private BatsmanScorecardAdapter bBatsmanAdapter;
    private CardView bBatsmanHead;
    private RecyclerView bBatsmanRecycler;
    private ImageView bBatsmanUpIcon;
    private BowlerScorecardAdapter bBowlerAdapter;
    private CardView bBowlerHead;
    private ProgressBar progressBar;
    private TextView scoreCardNotFound;
    private CardView teamA1BatsmanLayout;
    private RecyclerView teamA1BowlerRecyclerView;
    private TextView teamA1InningsName;
    private TextView teamA1InningsScore;
    private CardView teamABatsmanLayout;
    private RecyclerView teamABowlerRecyclerView;
    private TextView teamAInningsName;
    private TextView teamAInningsScore;
    private CardView teamB1BatsmanLayout;
    private RecyclerView teamB1BowlerRecyclerView;
    private TextView teamB1InningsName;
    private TextView teamB1InningsScore;
    private CardView teamBBatsmanLayout;
    private RecyclerView teamBBowlerRecyclerView;
    private TextView teamBInningsName;
    private TextView teamBInningsScore;
    private String url;
    private Boolean aBatsmanUp = false;
    private ArrayList<ScorecardBatsman> aBatsman = new ArrayList<>();
    private Boolean bBatsmanUp = false;
    private ArrayList<ScorecardBatsman> bBatsman = new ArrayList<>();
    private ArrayList<ScorecardBowler> aBowler = new ArrayList<>();
    private ArrayList<ScorecardBowler> bBowler = new ArrayList<>();
    private Boolean a1BatsmanUp = false;
    private ArrayList<ScorecardBatsman> a1Batsman = new ArrayList<>();
    private Boolean b1BatsmanUp = false;
    private ArrayList<ScorecardBatsman> b1Batsman = new ArrayList<>();
    private ArrayList<ScorecardBowler> a1Bowler = new ArrayList<>();
    private ArrayList<ScorecardBowler> b1Bowler = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_scorecard, viewGroup, false);
        if (getArguments() != null) {
            String replace = getArguments().getString(ImagesContract.URL).replace("live-cricket-scores", "live-cricket-scorecard");
            this.url = replace;
            this.url = replace.replace("cricket-scores", "live-cricket-scorecard");
        }
        this.scoreCardNotFound = (TextView) inflate.findViewById(R.id.scoreCardNotFound);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.teamAInningsName = (TextView) inflate.findViewById(R.id.teamAInningsName);
        this.teamAInningsScore = (TextView) inflate.findViewById(R.id.teamAInningsScore);
        this.teamBInningsName = (TextView) inflate.findViewById(R.id.teamBInningsName);
        this.teamBInningsScore = (TextView) inflate.findViewById(R.id.teamBInningsScore);
        ASetupRecyclerView(inflate);
        BSetupRecyclerView(inflate);
        ABowlerSetup(inflate);
        BBowlerSetup(inflate);
        this.teamA1InningsName = (TextView) inflate.findViewById(R.id.teamA1InningsName);
        this.teamA1InningsScore = (TextView) inflate.findViewById(R.id.teamA1InningsScore);
        this.teamB1InningsName = (TextView) inflate.findViewById(R.id.teamB1InningsName);
        this.teamB1InningsScore = (TextView) inflate.findViewById(R.id.teamB1InningsScore);
        A1SetupRecyclerView(inflate);
        B1SetupRecyclerView(inflate);
        A1BowlerSetup(inflate);
        B1BowlerSetup(inflate);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        new AInningsHeadContent().execute(new Void[0]);
        new BInningsHeadContent().execute(new Void[0]);
        new ABatsmanContent().execute(new Void[0]);
        new BBatsmanContent().execute(new Void[0]);
        new ABowlerContent().execute(new Void[0]);
        new BBowlerContent().execute(new Void[0]);
        new A1InningsHeadContent().execute(new Void[0]);
        new B1InningsHeadContent().execute(new Void[0]);
        new A1BatsmanContent().execute(new Void[0]);
        new B1BatsmanContent().execute(new Void[0]);
        new A1BowlerContent().execute(new Void[0]);
        new B1BowlerContent().execute(new Void[0]);
    }


    private class AInningsHeadContent extends AsyncTask<Void, Void, Void> {
        private AInningsHeadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            if (ScorecardFragment.this.AInningsName != null || ScorecardFragment.this.AInningsScore != null) {
                ScorecardFragment.this.teamAInningsName.setText(ScorecardFragment.this.AInningsName);
                ScorecardFragment.this.teamAInningsScore.setText(ScorecardFragment.this.AInningsScore);
            }
            if (ScorecardFragment.this.BInningsName == null && ScorecardFragment.this.BInningsScore == null) {
                return;
            }
            ScorecardFragment.this.teamBInningsName.setText(ScorecardFragment.this.BInningsName);
            ScorecardFragment.this.teamBInningsScore.setText(ScorecardFragment.this.BInningsScore);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_1").select("div.cb-ltst-wgt-hdr").eq(0);
                int size = eq.size();
                Log.d("AInningsName", "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.AInningsName = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(0).text();
                    ScorecardFragment.this.AInningsScore = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(1).text();
                    Log.d("AInningsName", "doInBackground: " + ScorecardFragment.this.AInningsName);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    private class BInningsHeadContent extends AsyncTask<Void, Void, Void> {
        private BInningsHeadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            if (ScorecardFragment.this.BInningsName == null && ScorecardFragment.this.BInningsScore == null) {
                return;
            }
            ScorecardFragment.this.teamBInningsName.setText(ScorecardFragment.this.BInningsName);
            ScorecardFragment.this.teamBInningsScore.setText(ScorecardFragment.this.BInningsScore);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_2").select("div.cb-ltst-wgt-hdr").eq(0);
                int size = eq.size();
                Log.d("AInningsName", "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.BInningsName = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(0).text();
                    ScorecardFragment.this.BInningsScore = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(1).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void ASetupRecyclerView(View view) {
        CardView cardView = view.findViewById(R.id.teamABatsmanLayout);
        this.teamABatsmanLayout = cardView;
        cardView.setVisibility(View.GONE);
        CardView cardView2 = view.findViewById(R.id.aBatsmanHead);
        this.aBatsmanHead = cardView2;
        cardView2.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.teamABatsmanRecyclerView);
        this.aBatsmanRecycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BatsmanScorecardAdapter batsmanScorecardAdapter = new BatsmanScorecardAdapter(this.aBatsman);
        this.aBatsmanAdapter = batsmanScorecardAdapter;
        this.aBatsmanRecycler.setAdapter(batsmanScorecardAdapter);
        ImageView imageView =   view.findViewById(R.id.aBatsmanUpIcon);
        this.aBatsmanUpIcon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (!ScorecardFragment.this.aBatsmanUp.booleanValue()) {
                    ScorecardFragment.this.aBatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    ScorecardFragment.this.aBatsmanRecycler.setVisibility(8);
                    ScorecardFragment.this.aBatsmanHead.setVisibility(8);
                    ScorecardFragment.this.teamABowlerRecyclerView.setVisibility(8);
                    ScorecardFragment.this.aBowlerHead.setVisibility(8);
                    ScorecardFragment.this.aBatsmanUp = true;
                    return;
                }
                ScorecardFragment.this.aBatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                ScorecardFragment.this.aBatsmanRecycler.setVisibility(0);
                ScorecardFragment.this.aBatsmanHead.setVisibility(0);
                ScorecardFragment.this.teamABowlerRecyclerView.setVisibility(0);
                ScorecardFragment.this.aBowlerHead.setVisibility(0);
                ScorecardFragment.this.aBatsmanUp = false;
            }
        });
    }

    /* loaded from: classes.dex */
    private class ABatsmanContent extends AsyncTask<Void, Void, Void> {
        private ABatsmanContent() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.aBatsman.clear();
            ScorecardFragment.this.aBatsmanAdapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            ScorecardFragment.this.aBatsmanRecycler.setVisibility(8);
            ScorecardFragment.this.aBatsmanAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.aBatsmanAdapter.getItemCount() == 0) {
                ScorecardFragment.this.aBatsmanRecycler.setVisibility(8);
                ScorecardFragment.this.scoreCardNotFound.setVisibility(0);
                ScorecardFragment.this.progressBar.setVisibility(8);
                return;
            }
            ScorecardFragment.this.aBatsmanRecycler.setVisibility(0);
            ScorecardFragment.this.scoreCardNotFound.setVisibility(8);
            ScorecardFragment.this.teamABatsmanLayout.setVisibility(0);
            ScorecardFragment.this.aBatsmanHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_1").select("div.cb-ltst-wgt-hdr").eq(0).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.aBatsman.add(new ScorecardBatsman(select.get(i).select("div.cb-col").eq(1).text(), select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(6).text(), select.get(i).select("div.cb-col").eq(7).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void BSetupRecyclerView(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.teamBBatsmanLayout);
        this.teamBBatsmanLayout = cardView;
        cardView.setVisibility(8);
        CardView cardView2 = (CardView) view.findViewById(R.id.bBatsmanHead);
        this.bBatsmanHead = cardView2;
        cardView2.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamBBatsmanRecyclerView);
        this.bBatsmanRecycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BatsmanScorecardAdapter batsmanScorecardAdapter = new BatsmanScorecardAdapter(this.bBatsman);
        this.bBatsmanAdapter = batsmanScorecardAdapter;
        this.bBatsmanRecycler.setAdapter(batsmanScorecardAdapter);
        ImageView imageView = (ImageView) view.findViewById(R.id.bBatsmanUpIcon);
        this.bBatsmanUpIcon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.digital.livecricketapp.Schedule.ScheduleDetails.Fragments.ScorecardFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!ScorecardFragment.this.bBatsmanUp.booleanValue()) {
                    ScorecardFragment.this.bBatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    ScorecardFragment.this.bBatsmanRecycler.setVisibility(8);
                    ScorecardFragment.this.bBatsmanHead.setVisibility(8);
                    ScorecardFragment.this.teamBBowlerRecyclerView.setVisibility(8);
                    ScorecardFragment.this.bBowlerHead.setVisibility(8);
                    ScorecardFragment.this.bBatsmanUp = true;
                    return;
                }
                ScorecardFragment.this.bBatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                ScorecardFragment.this.bBatsmanRecycler.setVisibility(0);
                ScorecardFragment.this.bBatsmanHead.setVisibility(0);
                ScorecardFragment.this.teamBBowlerRecyclerView.setVisibility(0);
                ScorecardFragment.this.bBowlerHead.setVisibility(0);
                ScorecardFragment.this.bBatsmanUp = false;
            }
        });
    }


    private class BBatsmanContent extends AsyncTask<Void, Void, Void> {
        private BBatsmanContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.bBatsman.clear();
            ScorecardFragment.this.bBatsmanAdapter.notifyDataSetChanged();
            ScorecardFragment.this.progressBar.setVisibility(0);
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            ScorecardFragment.this.bBatsmanRecycler.setVisibility(8);
            ScorecardFragment.this.bBatsmanAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.bBatsmanAdapter.getItemCount() == 0) {
                ScorecardFragment.this.bBatsmanRecycler.setVisibility(8);
                ScorecardFragment.this.bBatsmanRecycler.setVisibility(8);
                ScorecardFragment.this.teamBBatsmanLayout.setVisibility(8);
                ScorecardFragment.this.bBatsmanHead.setVisibility(8);
                return;
            }
            ScorecardFragment.this.bBatsmanRecycler.setVisibility(0);
            ScorecardFragment.this.teamBBatsmanLayout.setVisibility(0);
            ScorecardFragment.this.bBatsmanHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_2").select("div.cb-ltst-wgt-hdr").eq(0).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.bBatsman.add(new ScorecardBatsman(select.get(i).select("div.cb-col").eq(1).text(), select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(6).text(), select.get(i).select("div.cb-col").eq(7).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void ABowlerSetup(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.aBowlerHead);
        this.aBowlerHead = cardView;
        cardView.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamABowlerRecyclerView);
        this.teamABowlerRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BowlerScorecardAdapter bowlerScorecardAdapter = new BowlerScorecardAdapter(this.aBowler);
        this.aBowlerAdapter = bowlerScorecardAdapter;
        this.teamABowlerRecyclerView.setAdapter(bowlerScorecardAdapter);
    }

    /* loaded from: classes.dex */
    private class ABowlerContent extends AsyncTask<Void, Void, Void> {
        private ABowlerContent() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.aBowler.clear();
            ScorecardFragment.this.aBowlerAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            ScorecardFragment.this.teamABowlerRecyclerView.setVisibility(8);
            ScorecardFragment.this.aBowlerAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.aBowlerAdapter.getItemCount() == 0) {
                ScorecardFragment.this.teamABowlerRecyclerView.setVisibility(8);
                return;
            }
            ScorecardFragment.this.teamABowlerRecyclerView.setVisibility(0);
            ScorecardFragment.this.aBowlerHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_1").select("div.cb-ltst-wgt-hdr").eq(1).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("div.cb-col").eq(1).text();
                    ScorecardFragment.this.aBowler.add(new ScorecardBowler(text, select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(8).text()));
                    Log.d("bowlerA", "doInBackground: " + text);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void BBowlerSetup(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.bBowlerHead);
        this.bBowlerHead = cardView;
        cardView.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamBBowlerRecyclerView);
        this.teamBBowlerRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BowlerScorecardAdapter bowlerScorecardAdapter = new BowlerScorecardAdapter(this.bBowler);
        this.bBowlerAdapter = bowlerScorecardAdapter;
        this.teamBBowlerRecyclerView.setAdapter(bowlerScorecardAdapter);
    }


    private class BBowlerContent extends AsyncTask<Void, Void, Void> {
        private BBowlerContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.bBowler.clear();
            ScorecardFragment.this.bBowlerAdapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            ScorecardFragment.this.teamBBowlerRecyclerView.setVisibility(8);
            ScorecardFragment.this.bBowlerAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.bBowlerAdapter.getItemCount() == 0) {
                ScorecardFragment.this.teamBBowlerRecyclerView.setVisibility(8);
                return;
            }
            ScorecardFragment.this.teamBBowlerRecyclerView.setVisibility(0);
            ScorecardFragment.this.bBowlerHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_2").select("div.cb-ltst-wgt-hdr").eq(1).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.bBowler.add(new ScorecardBowler(select.get(i).select("div.cb-col").eq(1).text(), select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(8).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private class A1InningsHeadContent extends AsyncTask<Void, Void, Void> {
        private A1InningsHeadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            if (ScorecardFragment.this.A1InningsName != null || ScorecardFragment.this.A1InningsScore != null) {
                ScorecardFragment.this.teamA1InningsName.setText(ScorecardFragment.this.A1InningsName);
                ScorecardFragment.this.teamA1InningsScore.setText(ScorecardFragment.this.A1InningsScore);
            }
            if (ScorecardFragment.this.B1InningsName == null && ScorecardFragment.this.B1InningsScore == null) {
                return;
            }
            ScorecardFragment.this.teamB1InningsName.setText(ScorecardFragment.this.B1InningsName);
            ScorecardFragment.this.teamB1InningsScore.setText(ScorecardFragment.this.B1InningsScore);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_3").select("div.cb-ltst-wgt-hdr").eq(0);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.A1InningsName = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(0).text();
                    ScorecardFragment.this.A1InningsScore = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(1).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    private class B1InningsHeadContent extends AsyncTask<Void, Void, Void> {
        private B1InningsHeadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            if (ScorecardFragment.this.B1InningsName == null && ScorecardFragment.this.B1InningsScore == null) {
                return;
            }
            ScorecardFragment.this.teamB1InningsName.setText(ScorecardFragment.this.B1InningsName);
            ScorecardFragment.this.teamB1InningsScore.setText(ScorecardFragment.this.B1InningsScore);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_4").select("div.cb-ltst-wgt-hdr").eq(0);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.B1InningsName = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(0).text();
                    ScorecardFragment.this.B1InningsScore = eq.get(i).select("div.cb-scrd-hdr-rw").select("span").eq(1).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void A1SetupRecyclerView(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.teamA1BatsmanLayout);
        this.teamA1BatsmanLayout = cardView;
        cardView.setVisibility(8);
        CardView cardView2 = (CardView) view.findViewById(R.id.a1BatsmanHead);
        this.a1BatsmanHead = cardView2;
        cardView2.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamA1BatsmanRecyclerView);
        this.a1BatsmanRecycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BatsmanScorecardAdapter batsmanScorecardAdapter = new BatsmanScorecardAdapter(this.a1Batsman);
        this.a1BatsmanAdapter = batsmanScorecardAdapter;
        this.a1BatsmanRecycler.setAdapter(batsmanScorecardAdapter);
        ImageView imageView = (ImageView) view.findViewById(R.id.a1BatsmanUpIcon);
        this.a1BatsmanUpIcon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (!ScorecardFragment.this.a1BatsmanUp.booleanValue()) {
                    ScorecardFragment.this.a1BatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    ScorecardFragment.this.a1BatsmanRecycler.setVisibility(8);
                    ScorecardFragment.this.a1BatsmanHead.setVisibility(8);
                    ScorecardFragment.this.teamA1BowlerRecyclerView.setVisibility(8);
                    ScorecardFragment.this.a1BowlerHead.setVisibility(8);
                    ScorecardFragment.this.a1BatsmanUp = true;
                    return;
                }
                ScorecardFragment.this.a1BatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                ScorecardFragment.this.a1BatsmanRecycler.setVisibility(0);
                ScorecardFragment.this.a1BatsmanHead.setVisibility(0);
                ScorecardFragment.this.teamA1BowlerRecyclerView.setVisibility(0);
                ScorecardFragment.this.a1BowlerHead.setVisibility(0);
                ScorecardFragment.this.a1BatsmanUp = false;
            }
        });
    }


    private class A1BatsmanContent extends AsyncTask<Void, Void, Void> {
        private A1BatsmanContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.a1Batsman.clear();
            ScorecardFragment.this.a1BatsmanAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            ScorecardFragment.this.a1BatsmanRecycler.setVisibility(8);
            ScorecardFragment.this.a1BatsmanAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.a1BatsmanAdapter.getItemCount() == 0) {
                ScorecardFragment.this.a1BatsmanRecycler.setVisibility(8);
                return;
            }
            ScorecardFragment.this.a1BatsmanRecycler.setVisibility(0);
            ScorecardFragment.this.teamA1BatsmanLayout.setVisibility(0);
            ScorecardFragment.this.a1BatsmanHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_3").select("div.cb-ltst-wgt-hdr").eq(0).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.a1Batsman.add(new ScorecardBatsman(select.get(i).select("div.cb-col").eq(1).text(), select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(6).text(), select.get(i).select("div.cb-col").eq(7).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void B1SetupRecyclerView(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.teamB1BatsmanLayout);
        this.teamB1BatsmanLayout = cardView;
        cardView.setVisibility(8);
        CardView cardView2 = (CardView) view.findViewById(R.id.b1BatsmanHead);
        this.b1BatsmanHead = cardView2;
        cardView2.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamB1BatsmanRecyclerView);
        this.b1BatsmanRecycler = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BatsmanScorecardAdapter batsmanScorecardAdapter = new BatsmanScorecardAdapter(this.b1Batsman);
        this.b1BatsmanAdapter = batsmanScorecardAdapter;
        this.b1BatsmanRecycler.setAdapter(batsmanScorecardAdapter);
        ImageView imageView = (ImageView) view.findViewById(R.id.b1BatsmanUpIcon);
        this.b1BatsmanUpIcon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (!ScorecardFragment.this.b1BatsmanUp.booleanValue()) {
                    ScorecardFragment.this.b1BatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    ScorecardFragment.this.b1BatsmanRecycler.setVisibility(8);
                    ScorecardFragment.this.b1BatsmanHead.setVisibility(8);
                    ScorecardFragment.this.teamB1BowlerRecyclerView.setVisibility(8);
                    ScorecardFragment.this.b1BowlerHead.setVisibility(8);
                    ScorecardFragment.this.b1BatsmanUp = true;
                    return;
                }
                ScorecardFragment.this.b1BatsmanUpIcon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                ScorecardFragment.this.b1BatsmanRecycler.setVisibility(0);
                ScorecardFragment.this.b1BatsmanHead.setVisibility(0);
                ScorecardFragment.this.teamB1BowlerRecyclerView.setVisibility(0);
                ScorecardFragment.this.b1BowlerHead.setVisibility(0);
                ScorecardFragment.this.b1BatsmanUp = false;
            }
        });
    }


    private class B1BatsmanContent extends AsyncTask<Void, Void, Void> {
        private B1BatsmanContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.b1Batsman.clear();
            ScorecardFragment.this.b1BatsmanAdapter.notifyDataSetChanged();
        }

        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            ScorecardFragment.this.b1BatsmanRecycler.setVisibility(8);
            ScorecardFragment.this.b1BatsmanAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.b1BatsmanAdapter.getItemCount() == 0) {
                ScorecardFragment.this.b1BatsmanRecycler.setVisibility(8);
                ScorecardFragment.this.b1BatsmanRecycler.setVisibility(8);
                ScorecardFragment.this.teamB1BatsmanLayout.setVisibility(8);
                ScorecardFragment.this.b1BatsmanHead.setVisibility(8);
                return;
            }
            ScorecardFragment.this.b1BatsmanRecycler.setVisibility(0);
            ScorecardFragment.this.teamB1BatsmanLayout.setVisibility(0);
            ScorecardFragment.this.b1BatsmanHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }

        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_4").select("div.cb-ltst-wgt-hdr").eq(0).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.b1Batsman.add(new ScorecardBatsman(select.get(i).select("div.cb-col").eq(1).text(), select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(6).text(), select.get(i).select("div.cb-col").eq(7).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void A1BowlerSetup(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.a1BowlerHead);
        this.a1BowlerHead = cardView;
        cardView.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamA1BowlerRecyclerView);
        this.teamA1BowlerRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BowlerScorecardAdapter bowlerScorecardAdapter = new BowlerScorecardAdapter(this.a1Bowler);
        this.a1BowlerAdapter = bowlerScorecardAdapter;
        this.teamA1BowlerRecyclerView.setAdapter(bowlerScorecardAdapter);
    }


    private class A1BowlerContent extends AsyncTask<Void, Void, Void> {
        private A1BowlerContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.a1Bowler.clear();
            ScorecardFragment.this.a1BowlerAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            ScorecardFragment.this.teamA1BowlerRecyclerView.setVisibility(8);
            ScorecardFragment.this.a1BowlerAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.a1BowlerAdapter.getItemCount() == 0) {
                ScorecardFragment.this.teamA1BowlerRecyclerView.setVisibility(8);
                return;
            }
            ScorecardFragment.this.teamA1BowlerRecyclerView.setVisibility(0);
            ScorecardFragment.this.a1BowlerHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_3").select("div.cb-ltst-wgt-hdr").eq(1).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("div.cb-col").eq(1).text();
                    ScorecardFragment.this.a1Bowler.add(new ScorecardBowler(text, select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(8).text()));
                    Log.d("bowlerA", "doInBackground: " + text);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void B1BowlerSetup(View view) {
        CardView cardView = (CardView) view.findViewById(R.id.b1BowlerHead);
        this.b1BowlerHead = cardView;
        cardView.setVisibility(8);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teamB1BowlerRecyclerView);
        this.teamB1BowlerRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BowlerScorecardAdapter bowlerScorecardAdapter = new BowlerScorecardAdapter(this.b1Bowler);
        this.b1BowlerAdapter = bowlerScorecardAdapter;
        this.teamB1BowlerRecyclerView.setAdapter(bowlerScorecardAdapter);
    }


    private class B1BowlerContent extends AsyncTask<Void, Void, Void> {
        private B1BowlerContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ScorecardFragment.this.progressBar.setVisibility(0);
            ScorecardFragment.this.b1Bowler.clear();
            ScorecardFragment.this.b1BowlerAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            ScorecardFragment.this.teamB1BowlerRecyclerView.setVisibility(8);
            ScorecardFragment.this.b1BowlerAdapter.notifyDataSetChanged();
            if (ScorecardFragment.this.b1BowlerAdapter.getItemCount() == 0) {
                ScorecardFragment.this.teamB1BowlerRecyclerView.setVisibility(8);
                return;
            }
            ScorecardFragment.this.teamB1BowlerRecyclerView.setVisibility(0);
            ScorecardFragment.this.b1BowlerHead.setVisibility(0);
            ScorecardFragment.this.progressBar.setVisibility(8);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(ScorecardFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#innings_4").select("div.cb-ltst-wgt-hdr").eq(1).select("div.cb-scrd-itms");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    ScorecardFragment.this.b1Bowler.add(new ScorecardBowler(select.get(i).select("div.cb-col").eq(1).text(), select.get(i).select("div.cb-col").eq(2).text(), select.get(i).select("div.cb-col").eq(3).text(), select.get(i).select("div.cb-col").eq(4).text(), select.get(i).select("div.cb-col").eq(5).text(), select.get(i).select("div.cb-col").eq(8).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}