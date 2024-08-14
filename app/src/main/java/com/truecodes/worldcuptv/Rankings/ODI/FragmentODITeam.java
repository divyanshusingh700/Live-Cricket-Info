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
import com.truecodes.worldcuptv.Rankings.Players.TeamRankingAdapter;
import com.truecodes.worldcuptv.Rankings.TeamRanking;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class FragmentODITeam extends Fragment {
    private TeamRankingAdapter adapter;
    private TextView notFoundTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<TeamRanking> statsDetails = new ArrayList<>();
    private String url = "https://sports.ndtv.com/cricket/icc-rankings";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_o_d_i_team, viewGroup, false);
        TextView textView = inflate.findViewById(R.id.notFoundTextView);
        this.notFoundTextView = textView;
        textView.setVisibility(View.GONE);
        this.progressBar = inflate.findViewById(R.id.progressBar);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
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
            FragmentODITeam.this.progressBar.setVisibility(View.VISIBLE);
            FragmentODITeam.this.statsDetails.clear();
            FragmentODITeam.this.adapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            FragmentODITeam.this.progressBar.setVisibility(View.GONE);
            FragmentODITeam.this.adapter.notifyDataSetChanged();
            if (FragmentODITeam.this.adapter.getItemCount() == 0) {
                FragmentODITeam.this.notFoundTextView.setVisibility(View.VISIBLE);
                return;
            }
            FragmentODITeam.this.recyclerView.setVisibility(View.VISIBLE);
            FragmentODITeam.this.adapter.notifyDataSetChanged();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                int i = 4;
                Elements select = Jsoup.connect(FragmentODITeam.this.url).userAgent("Opera").get().select("div.rnk_lst-wrp > div.rnk_tbl-wrp").eq(4).select("table.rnk_tbl > tbody.rnk_tbl-tb > tr.rnk_tbl-tr");
                int size = select.size();
                int i2 = 1;
                while (i2 < size) {
                    Elements select2 = select.get(i2).select("tr.rnk_tbl-tr");
                    String text = select2.select("td.rnk_tbl-td").eq(0).text();
                    Elements eq = select2.select("td.rnk_tbl-td").eq(1);
                    FragmentODITeam.this.statsDetails.add(new TeamRanking(text, eq.select("span.rnk_tbl-pl").select("a").select("img").attr("data-src"), eq.select("span.rnk_tbl-nm").select("a").attr("title"), select2.select("td.rnk_tbl-td").eq(2).text(), select2.select("td.rnk_tbl-td").eq(3).text(), select2.select("td.rnk_tbl-td").eq(i).text()));
                    Log.d("rankingItem", text);
                    i2++;
                    i = 4;
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