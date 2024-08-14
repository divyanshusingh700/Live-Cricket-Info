package com.truecodes.worldcuptv.CricketSeries;

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
import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesAdapter;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeries;
import com.truecodes.worldcuptv.R;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class FragmentSeriesMain extends Fragment {
    private CricketSeriesAdapter adapter;
    private TextView errorTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<CricketSeries> schedules = new ArrayList<>();
    private String url = "https://www.cricbuzz.com/cricket-schedule/series";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_series_main, viewGroup, false);
        this.recyclerView =   inflate.findViewById(R.id.recyclerView);
        this.progressBar =  inflate.findViewById(R.id.progressBar);
        TextView textView =  inflate.findViewById(R.id.error);
        this.errorTextView = textView;
        textView.setVisibility(View.GONE);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CricketSeriesAdapter cricketSeriesAdapter = new CricketSeriesAdapter(this.schedules, getContext(), getActivity());
        this.adapter = cricketSeriesAdapter;
        this.recyclerView.setAdapter(cricketSeriesAdapter);
        new GETCricketSeries().execute(new Void[0]);
        return inflate;
    }


    private class GETCricketSeries extends AsyncTask<Void, Void, Void> {
        private GETCricketSeries() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FragmentSeriesMain.this.schedules.clear();
            FragmentSeriesMain.this.adapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            FragmentSeriesMain.this.adapter.notifyDataSetChanged();
            FragmentSeriesMain.this.recyclerView.setVisibility(View.VISIBLE);
            FragmentSeriesMain.this.progressBar.setVisibility(View.GONE);
            if (FragmentSeriesMain.this.adapter.getItemCount() == 0) {
                FragmentSeriesMain.this.errorTextView.setVisibility(View.VISIBLE);
                return;
            }
            FragmentSeriesMain.this.recyclerView.setVisibility(View.VISIBLE);
            FragmentSeriesMain.this.errorTextView.setVisibility(View.GONE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentSeriesMain.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36").timeout(30000).get().select("div#page-wrapper").select("div.cb-schdl").select("div.cb-sch-lst-itm");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("a").select("span.text-black").text();
                    String text2 = select.get(i).select("div.text-gray.cb-font-12").text();
                    String attr = select.get(i).select("a").attr("href");
                    Log.d("hsoighsp", "doInBackground: " + attr);
                    FragmentSeriesMain.this.schedules.add(new CricketSeries(text, text2, attr));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "doInBackground: " + e.getMessage());
                return null;
            }
        }
    }
}