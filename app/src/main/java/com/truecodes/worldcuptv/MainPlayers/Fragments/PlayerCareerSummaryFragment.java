package com.truecodes.worldcuptv.MainPlayers.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.MainPlayers.Adapters.BatsmanSummaryAdapter;
import com.truecodes.worldcuptv.MainPlayers.Models.BatsmanSummary;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class PlayerCareerSummaryFragment extends Fragment {
    BatsmanSummaryAdapter adapter;
    private ConstraintLayout batsmanLayout;
    private RecyclerView batsmanRecyclerView;
    BatsmanSummaryAdapter bowlerAdapter;
    private ConstraintLayout bowlerLayout;
    private RecyclerView bowlerRecyclerView;
    private TextView notFound;
    private ProgressBar progressbar;
    private String url;
    private ArrayList<BatsmanSummary> batsmanSummaries = new ArrayList<>();
    private ArrayList<BatsmanSummary> bowlerSummaries = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_player_career_summary, viewGroup, false);
        if (getArguments() != null) {
            this.url = getArguments().getString(ImagesContract.URL);
        }
        ConstraintLayout batsmanLayout =  inflate.findViewById(R.id.batsmanLayout);
        batsmanLayout.setVisibility(View.GONE);
        ConstraintLayout bowlerLayout =  inflate.findViewById(R.id.bowlerLayout);
        bowlerLayout.setVisibility(View.GONE);
        batsmanRecyclerView = inflate.findViewById(R.id.batsmanRecyclerView);
        progressbar = inflate.findViewById(R.id.progressbar);
        TextView notFound = inflate.findViewById(R.id.notFound);
        notFound.setVisibility( View.GONE);
        batsmanRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BatsmanSummaryAdapter batsmanSummaryAdapter = new BatsmanSummaryAdapter(this.batsmanSummaries);
        adapter = batsmanSummaryAdapter;
        batsmanRecyclerView.setAdapter(batsmanSummaryAdapter);
        RecyclerView bowlerRecyclerView =  inflate.findViewById(R.id.bowlerRecyclerView);
        bowlerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BatsmanSummaryAdapter batsmanSummaryAdapter2 = new BatsmanSummaryAdapter(this.bowlerSummaries);
        bowlerAdapter = batsmanSummaryAdapter2;
        bowlerRecyclerView.setAdapter(batsmanSummaryAdapter2);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetBatsmanStatsContent().execute(new Void[0]);
        new GetBowlerStatsContent().execute(new Void[0]);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.batsmanSummaries.clear();
        this.bowlerSummaries.clear();
    }


    private class GetBatsmanStatsContent extends AsyncTask<Void, Void, Void> {
        private GetBatsmanStatsContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayerCareerSummaryFragment.this.progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
             progressbar.setVisibility(View.GONE);
             adapter.notifyDataSetChanged();
            if ( adapter.getItemCount() == 0) {
                 notFound.setVisibility(View.VISIBLE);
                 batsmanRecyclerView.setVisibility( View.GONE);
                 batsmanLayout.setVisibility(View.GONE);
                return;
            }
            PlayerCareerSummaryFragment.this.notFound.setVisibility(View.GONE);
            PlayerCareerSummaryFragment.this.batsmanRecyclerView.setVisibility(View.VISIBLE);
            PlayerCareerSummaryFragment.this.batsmanLayout.setVisibility(View.VISIBLE);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayerCareerSummaryFragment.this.url.replace("www", "m")).get().select("table.table-condensed").eq(2).select("tbody > tr");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    PlayerCareerSummaryFragment.this.batsmanSummaries.add(new BatsmanSummary(select.get(i).select("td").eq(0).text(), select.get(i).select("td").eq(1).text(), select.get(i).select("td").eq(2).text(), select.get(i).select("td").eq(3).text(), select.get(i).select("td").eq(4).text(), select.get(i).select("td").eq(5).text()));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }


    private class GetBowlerStatsContent extends AsyncTask<Void, Void, Void> {
        private GetBowlerStatsContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayerCareerSummaryFragment.this.progressbar.setVisibility(View.VISIBLE);
        }
        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            PlayerCareerSummaryFragment.this.progressbar.setVisibility(View.GONE);
            PlayerCareerSummaryFragment.this.bowlerAdapter.notifyDataSetChanged();
            if ( bowlerAdapter.getItemCount() == 0) {
                 notFound.setVisibility(View.VISIBLE);
                bowlerRecyclerView.setVisibility(View.GONE);
                bowlerLayout.setVisibility(View.GONE);
                return;
            }
            notFound.setVisibility(View.GONE);
            bowlerRecyclerView.setVisibility(View.VISIBLE);
            bowlerLayout.setVisibility(View.VISIBLE);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect( url.replace("www", "m")).get().select("table.table-condensed").eq(4).select("tbody > tr");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                     bowlerSummaries.add(new BatsmanSummary(select.get(i).select("td").eq(0).text(), select.get(i).select("td").eq(1).text(), select.get(i).select("td").eq(2).text(), select.get(i).select("td").eq(3).text(), select.get(i).select("td").eq(4).text(), select.get(i).select("td").eq(5).text()));
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