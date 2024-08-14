package com.truecodes.worldcuptv.Rankings.Test;



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

public class FragmentTestAllRounder extends Fragment {
    private RankingPlayerAdapter adapter;
    private TextView notFoundTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<RankingPlayer> statsDetails = new ArrayList<>();
    private String url = "https://sports.ndtv.com/cricket/icc-rankings/test-all-rounder";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_test_all_rounder, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.notFoundTextView);
        this.notFoundTextView = textView;
        textView.setVisibility(View.GONE);
        this.progressBar =  inflate.findViewById(R.id.testRankingProgressBar);
        RecyclerView recyclerView =  inflate.findViewById(R.id.testRankingRecyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RankingPlayerAdapter rankingPlayerAdapter = new RankingPlayerAdapter(this.statsDetails, getContext());
        this.adapter = rankingPlayerAdapter;
        this.recyclerView.setAdapter(rankingPlayerAdapter);
        new Content().execute(new Void[0]);
        return inflate;
    }

    private class Content extends AsyncTask<Void, Void, Void> {
        private Content() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FragmentTestAllRounder.this.progressBar.setVisibility(View.VISIBLE);
            FragmentTestAllRounder.this.statsDetails.clear();
            FragmentTestAllRounder.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            FragmentTestAllRounder.this.progressBar.setVisibility(View.GONE);
            FragmentTestAllRounder.this.adapter.notifyDataSetChanged();
            if (FragmentTestAllRounder.this.adapter.getItemCount() == 0) {
                FragmentTestAllRounder.this.notFoundTextView.setVisibility(View.VISIBLE);
                return;
            }
            FragmentTestAllRounder.this.recyclerView.setVisibility(View.VISIBLE);
            FragmentTestAllRounder.this.adapter.notifyDataSetChanged();
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentTestAllRounder.this.url).userAgent("Opera").get().select("div.rnk_tbl-wrp").select("table.rnk_tbl > tbody.rnk_tbl-tb > tr.rnk_tbl-tr");
                int size = select.size();
                for (int i = 1; i < size; i++) {
                    Elements select2 = select.get(i).select("tr.rnk_tbl-tr");
                    String text = select2.select("td.rnk_tbl-td").eq(0).text();
                    Elements eq = select2.select("td.rnk_tbl-td").eq(1);
                    FragmentTestAllRounder.this.statsDetails.add(new RankingPlayer(text, eq.select("span.rnk_tbl-nm").select("a").attr("title"), eq.select("span.rnk_tbl-pl").select("a").select("img").attr("data-src"), select2.select("td.rnk_tbl-td").eq(2).text()));
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