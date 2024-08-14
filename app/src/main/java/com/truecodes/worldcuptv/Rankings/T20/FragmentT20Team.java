package com.truecodes.worldcuptv.Rankings.T20;

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
import com.truecodes.worldcuptv.Rankings.Players.TeamRankingAdapter;
import com.truecodes.worldcuptv.Rankings.TeamRanking;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class FragmentT20Team extends Fragment {
    private TeamRankingAdapter adapter;
    private TextView notFoundTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<TeamRanking> statsDetails = new ArrayList<>();
    private String url = "https://sports.ndtv.com/cricket/icc-rankings";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_t20_team, viewGroup, false);
        TextView textView =  inflate.findViewById(R.id.notFoundTextView);
        this.notFoundTextView = textView;
        textView.setVisibility(View.GONE);
        this.progressBar =  inflate.findViewById(R.id.progressBar);
        RecyclerView recyclerView =  inflate.findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TeamRankingAdapter teamRankingAdapter = new TeamRankingAdapter(this.statsDetails, getContext(), getActivity());
        this.adapter = teamRankingAdapter;
        this.recyclerView.setAdapter(teamRankingAdapter);
        new Content().execute(new Void[0]);
        return inflate;
    }

    private class Content extends AsyncTask<Void, Void, Void> {
        private Content() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            FragmentT20Team.this.progressBar.setVisibility(View.VISIBLE);
            FragmentT20Team.this.statsDetails.clear();
            FragmentT20Team.this.adapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            FragmentT20Team.this.progressBar.setVisibility(View.GONE);
            FragmentT20Team.this.adapter.notifyDataSetChanged();
            if (FragmentT20Team.this.adapter.getItemCount() == 0) {
                FragmentT20Team.this.notFoundTextView.setVisibility(View.VISIBLE);
                return;
            }
            FragmentT20Team.this.recyclerView.setVisibility(View.VISIBLE);
            FragmentT20Team.this.adapter.notifyDataSetChanged();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentT20Team.this.url).userAgent("Opera").get().select("div.rnk_lst-wrp > div.rnk_tbl-wrp").eq(8).select("table.rnk_tbl > tbody.rnk_tbl-tb > tr.rnk_tbl-tr");
                int size = select.size();
                int i = 1;
                int i2 = 1;
                while (i2 < size) {
                    Elements select2 = select.get(i2).select("tr.rnk_tbl-tr");
                    String text = select2.select("td.rnk_tbl-td").eq(0).text();
                    Elements eq = select2.select("td.rnk_tbl-td").eq(i);
                    FragmentT20Team.this.statsDetails.add(new TeamRanking(text, eq.select("span.rnk_tbl-pl").select("a").select("img").attr("data-src"), eq.select("span.rnk_tbl-nm").select("a").attr("title"), select2.select("td.rnk_tbl-td").eq(2).text(), select2.select("td.rnk_tbl-td").eq(3).text(), select2.select("td.rnk_tbl-td").eq(4).text()));
                    Log.d("rankingItem", text);
                    i2++;
                    i = 1;
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