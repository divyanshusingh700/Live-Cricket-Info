package com.truecodes.worldcuptv.CricketSeries.Fragments;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.CricketSeries.Adapters.CricketSeriesTeamAdapter;
import com.truecodes.worldcuptv.CricketSeries.Models.CricketSeriesTeam;
import com.truecodes.worldcuptv.R;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class CricketSeriesTeamsFragment extends Fragment {
    private CricketSeriesTeamAdapter adapter;
    private TextView noMatchTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<CricketSeriesTeam> seriesTeams = new ArrayList<>();
    private String seriesUrl;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_cricket_series_teams, viewGroup, false);
        if (getArguments() != null) {
            seriesUrl = getArguments().getString("seriesUrl").replace("www", "m");
        }
        recyclerView = inflate.findViewById(R.id.recyclerView);
        progressBar =  inflate.findViewById(R.id.progressBar);
        noMatchTextView =  inflate.findViewById(R.id.noMatchTextView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CricketSeriesTeamAdapter cricketSeriesTeamAdapter = new CricketSeriesTeamAdapter(seriesTeams, getContext(), getActivity());
        adapter = cricketSeriesTeamAdapter;
        recyclerView.setAdapter(cricketSeriesTeamAdapter);
        new GETSeriesScheduleContent().execute(new Void[0]);
        return inflate;
    }
    private class GETSeriesScheduleContent extends AsyncTask<Void, Void, Void> {
        private GETSeriesScheduleContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            CricketSeriesTeamsFragment.this.seriesTeams.clear();
            CricketSeriesTeamsFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
             adapter.notifyDataSetChanged();
             progressBar.setVisibility(View.GONE);
            if ( adapter.getItemCount() == 0) {
                 noMatchTextView.setVisibility( View.VISIBLE);
                return;
            }
            CricketSeriesTeamsFragment.this.noMatchTextView.setVisibility(8);
            CricketSeriesTeamsFragment.this.recyclerView.setVisibility(0);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(CricketSeriesTeamsFragment.this.seriesUrl.replace("matches", "squads")).timeout(30000).get().select("div.container").select("div.cb-row").select("div.cb-col-matches").select("div.cb-squad-list");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("a.list-group-item").select("h3.cb-list-heading").text();
                    CricketSeriesTeamsFragment.this.seriesTeams.add(new CricketSeriesTeam(text.replace("Squad", ""), select.get(i).select("a.list-group-item").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}