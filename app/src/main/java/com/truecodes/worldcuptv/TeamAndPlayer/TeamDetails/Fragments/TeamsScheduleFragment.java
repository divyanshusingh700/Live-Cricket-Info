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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Adapters.TeamsScheduleAdapter;
import com.truecodes.worldcuptv.TeamAndPlayer.TeamDetails.Models.TeamsSchedule;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class TeamsScheduleFragment extends Fragment {
    TeamsScheduleAdapter adapter;
    private LinearLayout notFoundLayout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    private ArrayList<TeamsSchedule> schedules = new ArrayList<>();
    String teamName;
    String url;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_teams_schedule, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.notFoundLayout);
        this.notFoundLayout = linearLayout;
        linearLayout.setVisibility(View.GONE);
        if (getArguments() != null) {
            this.url = getArguments().getString(ImagesContract.URL);
            this.teamName = getArguments().getString("teamName");
        }
        this.recyclerView = inflate.findViewById(R.id.recyclerView);
        this.progressBar = inflate.findViewById(R.id.progressBar);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TeamsScheduleAdapter teamsScheduleAdapter = new TeamsScheduleAdapter(this.schedules, getContext(), getActivity());
        this.adapter = teamsScheduleAdapter;
        this.recyclerView.setAdapter(teamsScheduleAdapter);
        new GetLiveSchedule().execute(new Void[0]);
        return inflate;
    }


    private class GetLiveSchedule extends AsyncTask<Void, Void, Void> {
        private GetLiveSchedule() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TeamsScheduleFragment.this.progressBar.setVisibility(View.VISIBLE);
            TeamsScheduleFragment.this.schedules.clear();
            TeamsScheduleFragment.this.adapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute( r3);
            TeamsScheduleFragment.this.progressBar.setVisibility(View.GONE);
            TeamsScheduleFragment.this.adapter.notifyDataSetChanged();
            if (TeamsScheduleFragment.this.adapter.getItemCount() == 0) {
                TeamsScheduleFragment.this.notFoundLayout.setVisibility(View.VISIBLE);
                return;
            }
            TeamsScheduleFragment.this.notFoundLayout.setVisibility(View.VISIBLE);
            TeamsScheduleFragment.this.notFoundLayout.setVisibility(View.GONE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(TeamsScheduleFragment.this.url + "/schedule").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-series-filters").select("div.cb-series-matches");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    String text = select.get(i).select("div.cb-col-75").select("div.cb-ovr-flo").select("a.text-hvr-underline").text();
                    String attr = select.get(i).select("div.cb-col-75").select("div.cb-ovr-flo").select("a.text-hvr-underline").attr("href");
                    String text2 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("div.text-gray").eq(1).text();
                    String text3 = select.get(i).select("div.cb-col-75").select("div.cb-srs-mtchs-tm").eq(0).select("a.cb-text-inprogress").text();
                    TeamsScheduleFragment.this.schedules.add(new TeamsSchedule(select.get(i).select("div.cb-col-75").select("div.cb-col-40").select("span.schedule-date").attr("timestamp"), text3, text2, text, attr, ""));
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