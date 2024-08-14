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
import com.truecodes.worldcuptv.Rankings.Players.RankingPlayer;
import com.truecodes.worldcuptv.Rankings.Players.RankingPlayerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class FragmentT20AllRounder extends Fragment {
    private RankingPlayerAdapter adapter;
    private TextView notFoundTextView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<RankingPlayer> statsDetails = new ArrayList<>();
    private String url = "https://sports.ndtv.com/cricket/icc-rankings/t20-all-rounder";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_t20_all_rounder, viewGroup, false);
        TextView textView = inflate.findViewById(R.id.notFoundTextView);
        this.notFoundTextView = textView;
        textView.setVisibility(View.GONE);
        this.progressBar = inflate.findViewById(R.id.progressBar);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerView);
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
            FragmentT20AllRounder.this.progressBar.setVisibility(View.VISIBLE);
            FragmentT20AllRounder.this.statsDetails.clear();
            FragmentT20AllRounder.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            FragmentT20AllRounder.this.progressBar.setVisibility(View.GONE);
            FragmentT20AllRounder.this.adapter.notifyDataSetChanged();
            if (FragmentT20AllRounder.this.adapter.getItemCount() == 0) {
                FragmentT20AllRounder.this.notFoundTextView.setVisibility(View.VISIBLE);
                return;
            }
            FragmentT20AllRounder.this.recyclerView.setVisibility(View.VISIBLE);
            FragmentT20AllRounder.this.adapter.notifyDataSetChanged();
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(FragmentT20AllRounder.this.url).userAgent("Opera").get().select("div.rnk_tbl-wrp").select("table.rnk_tbl > tbody.rnk_tbl-tb > tr.rnk_tbl-tr");
                int size = select.size();
                for (int i = 1; i < size; i++) {
                    Elements select2 = select.get(i).select("tr.rnk_tbl-tr");
                    String text = select2.select("td.rnk_tbl-td").eq(0).text();
                    Elements eq = select2.select("td.rnk_tbl-td").eq(1);
                    String attr = eq.select("span.rnk_tbl-nm").select("a").attr("title");
                    String text2 = select2.select("td.rnk_tbl-td").eq(2).text();
                    FragmentT20AllRounder.this.statsDetails.add(new RankingPlayer(text, attr, eq.select("span.rnk_tbl-pl").select("a").select("img").attr("data-src"), text2));
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