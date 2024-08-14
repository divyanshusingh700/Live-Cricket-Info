package com.truecodes.worldcuptv.CricketSeries.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesScheduleAdapter;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeriesSchedule;
import com.truecodes.worldcuptv.R;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class CricketSeriesScheduleFragment extends Fragment {
    private CricketSeriesScheduleAdapter adapter;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String seriesUrl;
    private ArrayList<CricketSeriesSchedule> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-series/2834/icc-cricket-world-cup-league-two-2019-23/matches";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_cricket_series_schedule, viewGroup, false);
        if (getArguments() != null) {
            this.seriesUrl = getArguments().getString("seriesUrl");
        }
        this.recyclerView = inflate.findViewById(R.id.recyclerView);
        this.progressBar = inflate.findViewById(R.id.progressBar);
        this.noMatchTextView = inflate.findViewById(R.id.noMatchTextView);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CricketSeriesScheduleAdapter cricketSeriesScheduleAdapter = new CricketSeriesScheduleAdapter(this.schedules, getContext(), getActivity());
        this.adapter = cricketSeriesScheduleAdapter;
        this.recyclerView.setAdapter(cricketSeriesScheduleAdapter);
        new GETSeriesScheduleContent().execute(new Void[0]);
        return inflate;
    }


    private class GETSeriesScheduleContent extends AsyncTask<Void, Void, Void> {
        private GETSeriesScheduleContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            CricketSeriesScheduleFragment.this.schedules.clear();
            CricketSeriesScheduleFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            CricketSeriesScheduleFragment.this.adapter.notifyDataSetChanged();
            CricketSeriesScheduleFragment.this.progressBar.setVisibility(View.GONE);
            if (CricketSeriesScheduleFragment.this.adapter.getItemCount() == 0) {
                CricketSeriesScheduleFragment.this.noMatchTextView.setVisibility(View.VISIBLE);
                return;
            }
            CricketSeriesScheduleFragment.this.noMatchTextView.setVisibility(View.GONE);
            CricketSeriesScheduleFragment.this.recyclerView.setVisibility(View.VISIBLE);
        }


        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(CricketSeriesScheduleFragment.this.seriesUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#series-matches").select("div.cb-series-matches");
                int size = select.size();
                int i = 0;
                int i2 = 0;
                while (i2 < size) {
                    String text = select.get(i2).select("div.cb-srs-mtchs-tm").eq(i).select("div.cb-srs-mtchs-tm").select("span").text();
                    String text2 = select.get(i2).select("div.cb-srs-mtchs-tm").eq(i).select("a.text-hvr-underline").select("span").text();
                    String attr = select.get(i2).select("div.cb-srs-mtchs-tm").eq(i).select("a").attr("href");
                    String text3 = select.get(i2).select("div.cb-srs-mtchs-tm").eq(i).select("div.text-gray").text();
                    String text4 = select.get(i2).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(i).select("a.cb-text-complete").text();
                    String text5 = select.get(i2).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(i).select("a.cb-text-inprogress").text();
                    String attr2 = select.get(i2).select("div.cb-srs-mtchs-tm").eq(1).select("span").attr("timestamp");
                    String text6 = select.get(i2).select("div.cb-srs-mtchs-tm").eq(1).select("a.cb-lv-scrs-well-complete").select("div.cb-lv-scrs-col").select("div.cb-hmscg-bat-txt").text();
                    String text7 = select.get(i2).select("div.cb-srs-mtchs-tm").eq(1).select("a.cb-lv-scrs-well-complete").select("div.cb-lv-scrs-col").select("div.cb-hmscg-bwl-txt").text();
                    Log.d("rerwgd", "doInBackground: " + text3);
                    CricketSeriesScheduleFragment.this.schedules.add(new CricketSeriesSchedule(attr2, text4, text5, text3, text, text2, attr, text6, text7));
                    i2++;
                    i = 0;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}