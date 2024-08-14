package com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Fragments;


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
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Adapters.TeamsResultAdapter;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models.TeamsResult;
import com.google.android.gms.common.internal.ImagesContract;
//import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class TeamsResultsFragment extends Fragment {
    private TeamsResultAdapter adapter;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<TeamsResult> schedules = new ArrayList<>();
    private String url;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_teams_results, viewGroup, false);
        if (getArguments() != null) {
            this.url = getArguments().getString(ImagesContract.URL);
        }
        this.noMatchTextView = (TextView) inflate.findViewById(R.id.noMatchTextView);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TeamsResultAdapter teamsResultAdapter = new TeamsResultAdapter(this.schedules, getContext(), getActivity());
        this.adapter = teamsResultAdapter;
        this.recyclerView.setAdapter(teamsResultAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }


    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TeamsResultsFragment.this.progressBar.setVisibility(0);
            TeamsResultsFragment.this.schedules.clear();
            TeamsResultsFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            TeamsResultsFragment.this.progressBar.setVisibility(8);
            TeamsResultsFragment.this.adapter.notifyDataSetChanged();
            if (TeamsResultsFragment.this.adapter.getItemCount() == 0) {
                TeamsResultsFragment.this.noMatchTextView.setVisibility(0);
            } else {
                TeamsResultsFragment.this.noMatchTextView.setVisibility(8);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(TeamsResultsFragment.this.url + "/results").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#series-matches").select("div.cb-brdr-thin-btm");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("div.cb-col-60").select("a.text-hvr-underline").text();
                    String attr = select.get(i).select("div.cb-col-60").select("a.text-hvr-underline").attr("href");
                    String text2 = select.get(i).select("div.cb-col-60").select("div.cb-srs-mtchs-tm").eq(0).select("div.text-gray").eq(1).text();
                    String text3 = select.get(i).select("div.cb-col-60").select("a.cb-text-complete").text();
                    TeamsResultsFragment.this.schedules.add(new TeamsResult(select.get(i).select("div.cb-col-60").select("div.cb-col-40").select("span.schedule-date").attr("timestamp"), text3, text2, text, attr));
//                    Log.d(FirebaseAnalytics.Param.ITEMS, "doInBackground: " + attr);
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }
}