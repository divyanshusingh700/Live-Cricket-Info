package com.truecodes.worldcuptv.TeamAndPlayer.AsiaCupPlayer.Players;



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
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models.TeamsSquad;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/* loaded from: classes.dex */public class AsiacupPlayerFragment extends Fragment {
    AsiaCupPlayerAdapter adapter;
    private LinearLayout notFoundLayout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    private ArrayList<TeamsSquad> squads = new ArrayList<>();
    String teamName;
    String url;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_asiacup_player, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.notFoundLayout);
        this.notFoundLayout = linearLayout;
        linearLayout.setVisibility(View.GONE);
        if (getArguments() != null) {
            this.url = getArguments().getString("squad");
            this.teamName = getArguments().getString("teamName");
        }
        this.recyclerView =  inflate.findViewById(R.id.recyclerView);
        this.progressBar = inflate.findViewById(R.id.progressBar);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AsiaCupPlayerAdapter asiaCupPlayerAdapter = new AsiaCupPlayerAdapter(this.squads, getContext(), getActivity());
        this.adapter = asiaCupPlayerAdapter;
        this.recyclerView.setAdapter(asiaCupPlayerAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }

    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AsiacupPlayerFragment.this.progressBar.setVisibility(View.VISIBLE);
            AsiacupPlayerFragment.this.squads.clear();
            AsiacupPlayerFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            AsiacupPlayerFragment.this.progressBar.setVisibility(View.GONE);
            AsiacupPlayerFragment.this.adapter.notifyDataSetChanged();
            if (AsiacupPlayerFragment.this.adapter.getItemCount() == 0) {
                AsiacupPlayerFragment.this.notFoundLayout.setVisibility(View.VISIBLE);
            } else {
                AsiacupPlayerFragment.this.notFoundLayout.setVisibility(View.GONE);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(AsiacupPlayerFragment.this.url).get().select("div.cb-row").eq(1).select("div.cb-col-matches").select("div.cb-squad-list");
                int size = select.size();
                Log.d("errorBd", "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("a.list-group-item").select("h3.cb-list-heading").text();
                    String attr = select.get(i).select("a.list-group-item").attr("href");
                    AsiacupPlayerFragment.this.squads.add(new TeamsSquad(text, select.get(i).select("div.cb-col-27").select("img").attr("src"), attr));
                    Log.d("errorBd", "doInBackground: " + text);
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