package com.truecodes.worldcuptv.CricketSeries.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesPlayerAdapter;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeriesPlayer;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class CricketSeriesPlayerActivity extends AppCompatActivity {
    AdManager adManager;
    private CricketSeriesPlayerAdapter adapter;
    FrameLayout frameLayout;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String seriesUrl;
    private ArrayList<CricketSeriesPlayer> squads = new ArrayList<>();
    private String teamName;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cricket_series_player);
        this.seriesUrl = getIntent().getStringExtra(ImagesContract.URL);
        String stringExtra = getIntent().getStringExtra("teamName");
        this.teamName = stringExtra;
        if (stringExtra != null && !stringExtra.equals("")) {
            getSupportActionBar().setTitle(teamName);
        }
        frameLayout = findViewById(R.id.bannerContainer);
        adManager = new AdManager(getApplicationContext(), this);
        adManager.loadBanner(this.frameLayout);
        noMatchTextView =  findViewById(R.id.noMatchTextView);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        CricketSeriesPlayerAdapter cricketSeriesPlayerAdapter = new CricketSeriesPlayerAdapter(squads, getApplicationContext(), this);
        adapter = cricketSeriesPlayerAdapter;
        recyclerView.setAdapter(cricketSeriesPlayerAdapter);
        new GETSeriesSquads().execute(new Void[0]);
    }

    private class GETSeriesSquads extends AsyncTask<Void, Void, Void> {
        private GETSeriesSquads() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            squads.clear();
            adapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
            if (adapter.getItemCount() == 0) {
                noMatchTextView.setVisibility(View.VISIBLE);
                return;
            }
            noMatchTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(seriesUrl).get().select("div.cb-row").eq(1).select("div.cb-col-matches").select("div.cb-squad-list");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    squads.add(new CricketSeriesPlayer(select.get(i).select("a.list-group-item").select("h3.cb-list-heading").text(), select.get(i).select("a.list-group-item").select("p.cb-list-intro-text").text(), select.get(i).select("a.list-group-item").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        adManager.destroyBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        adManager.loadBanner(frameLayout);
    }
}