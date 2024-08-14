package com.truecodes.worldcuptv.Rankings.ODI;

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
import com.truecodes.worldcuptv.Rankings.Players.RankingPlayer;
import com.truecodes.worldcuptv.Rankings.Players.RankingPlayerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class FragmentODIBowling extends Fragment {
    private RankingPlayerAdapter adapter;
    private TextView notFoundTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<RankingPlayer> statsDetails = new ArrayList<>();
    private String url = "https://sports.ndtv.com/cricket/icc-rankings/odi-bowling";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_o_d_i_bowling, viewGroup, false);
        TextView notFoundTextView = inflate.findViewById(R.id.notFoundTextView);
//        notFoundTextView = textView;
        notFoundTextView.setVisibility(View.GONE);
        progressBar = inflate.findViewById(R.id.progressBar);
        RecyclerView recyclerView =   inflate.findViewById(R.id.recyclerView);
//        recyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RankingPlayerAdapter rankingPlayerAdapter = new RankingPlayerAdapter(statsDetails, getContext());
        adapter = rankingPlayerAdapter;
        recyclerView.setAdapter(rankingPlayerAdapter);
        new Content().execute(new Void[0]);
        return inflate;
    }


    private class Content extends AsyncTask<Void, Void, Void> {
        private Content() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            statsDetails.clear();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            if ( adapter.getItemCount() == 0) {
                notFoundTextView.setVisibility(View.VISIBLE);
                return;
            }
             recyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements rankingData = Jsoup.connect(FragmentODIBowling.this.url).userAgent("Opera").get().select("div.rnk_tbl-wrp").select("table.rnk_tbl > tbody.rnk_tbl-tb > tr.rnk_tbl-tr");
                int ranks = rankingData.size();
                for (int rank = 1; rank < ranks; rank++) {
                    Elements rankingContainer = rankingData.get(rank).select("tr.rnk_tbl-tr");
                    String text = rankingContainer.select("td.rnk_tbl-td").eq(0).text();
                    Elements eq = rankingContainer.select("td.rnk_tbl-td").eq(1);
                    String attr = eq.select("span.rnk_tbl-nm").select("a").attr("title");
                    String text2 = rankingContainer.select("td.rnk_tbl-td").eq(2).text();
                    statsDetails.add(new RankingPlayer(text, attr, eq.select("span.rnk_tbl-pl").select("a").select("img").attr("data-src"), text2));
                    Log.d("rankingItem", text);
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