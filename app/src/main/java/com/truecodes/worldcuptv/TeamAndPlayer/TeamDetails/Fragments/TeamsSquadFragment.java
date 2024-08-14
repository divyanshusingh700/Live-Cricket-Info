package com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Adapters.TeamsSquadAdapter;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models.TeamsSquad;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class TeamsSquadFragment extends Fragment {
    TeamsSquadAdapter adapter;
    private LinearLayout notFoundLayout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    private ArrayList<TeamsSquad> squads = new ArrayList<>();
    String teamName;
    String url;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_teams_squad, viewGroup, false);
        LinearLayout linearLayout = inflate.findViewById(R.id.notFoundLayout);
        this.notFoundLayout = linearLayout;
        linearLayout.setVisibility(View.GONE);
        if (getArguments() != null) {
            this.url = getArguments().getString(ImagesContract.URL);
            this.teamName = getArguments().getString("teamName");
        }
        this.recyclerView =  inflate.findViewById(R.id.recyclerView);
        this.progressBar = inflate.findViewById(R.id.progressBar);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        TeamsSquadAdapter teamsSquadAdapter = new TeamsSquadAdapter(this.squads, getContext(), getActivity());
        this.adapter = teamsSquadAdapter;
        this.recyclerView.setAdapter(teamsSquadAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }


    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TeamsSquadFragment.this.progressBar.setVisibility(0);
            TeamsSquadFragment.this.squads.clear();
            TeamsSquadFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            TeamsSquadFragment.this.progressBar.setVisibility(8);
            TeamsSquadFragment.this.adapter.notifyDataSetChanged();
            if (TeamsSquadFragment.this.adapter.getItemCount() == 0) {
                TeamsSquadFragment.this.notFoundLayout.setVisibility(0);
            } else {
                TeamsSquadFragment.this.notFoundLayout.setVisibility(8);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(TeamsSquadFragment.this.url + "/players").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#series-news-list").select("a.cb-col-50");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String attr = select.get(i).attr("title");
                    String attr2 = select.get(i).attr("href");
                    TeamsSquadFragment.this.squads.add(new TeamsSquad(attr, select.get(i).select("div.cb-col-27").select("img").attr("src"), attr2));
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