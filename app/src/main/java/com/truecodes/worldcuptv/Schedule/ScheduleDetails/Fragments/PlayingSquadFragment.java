package com.truecodes.worldcuptv.Schedule.ScheduleDetails.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Adapters.SchedulePlayerAdapter;
import com.truecodes.worldcuptv.Schedule.ScheduleDetails.Models.SchedulePlayer;
import com.google.android.gms.common.internal.ImagesContract;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class PlayingSquadFragment extends Fragment {
    private ProgressBar progressBar;
    private TextView squadNotFound;
    SchedulePlayerAdapter teamABenchAdapter;
    private CardView teamABenchCardView;
    private RecyclerView teamABenchRecyclerView;
    private ImageView teamABenchUp;
    private String teamAPlaying;
    SchedulePlayerAdapter teamAPlayingAdapter;
    private CardView teamAPlayingCardView;
    private RecyclerView teamAPlayingRecyclerView;
    private TextView teamAPlayingTextView;
    private ImageView teamAPlayingUp;
    SchedulePlayerAdapter teamBBenchAdapter;
    private CardView teamBBenchCardView;
    private RecyclerView teamBBenchRecyclerView;
    private ImageView teamBBenchUp;
    private String teamBPlaying;
    SchedulePlayerAdapter teamBPlayingAdapter;
    private CardView teamBPlayingCardView;
    private RecyclerView teamBPlayingRecyclerView;
    private TextView teamBPlayingTextView;
    private ImageView teamBPlayingUp;
    String url;
    private Boolean teamAPlayingBoolean = false;
    private Boolean teamABenchBoolean = false;
    private Boolean teamBPlayingBoolean = false;
    private Boolean teamBBenchBoolean = false;
    private ArrayList<SchedulePlayer> teamAPlayingModel = new ArrayList<>();
    private ArrayList<SchedulePlayer> teamBPlayingModel = new ArrayList<>();
    private ArrayList<SchedulePlayer> teamABenchModel = new ArrayList<>();
    private ArrayList<SchedulePlayer> teamBBenchModel = new ArrayList<>();
    private boolean taskComplete = false;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_playing_squad, viewGroup, false);
        if (getArguments() != null) {
            this.url = getArguments().getString(ImagesContract.URL).replace("/cricket-scores/", "/cricket-match-facts/");
        }
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.squadNotFound = (TextView) inflate.findViewById(R.id.squadNotFound);
        this.teamAPlayingCardView = (CardView) inflate.findViewById(R.id.teamAPlayingCardView);
        this.teamABenchCardView = (CardView) inflate.findViewById(R.id.teamABenchCardView);
        this.teamBBenchCardView = (CardView) inflate.findViewById(R.id.teamBBenchCardView);
        this.teamBPlayingCardView = (CardView) inflate.findViewById(R.id.teamBPlayingCardView);
        this.teamAPlayingCardView.setVisibility(View.GONE);
        this.teamABenchCardView.setVisibility(View.GONE);
        this.teamBBenchCardView.setVisibility(View.GONE);
        this.teamBPlayingCardView.setVisibility(View.GONE);
        this.teamAPlayingRecyclerView = (RecyclerView) inflate.findViewById(R.id.teamAPlayingRecyclerView);
        this.teamABenchRecyclerView = (RecyclerView) inflate.findViewById(R.id.teamABenchRecyclerView);
        this.teamBPlayingRecyclerView = (RecyclerView) inflate.findViewById(R.id.teamBPlayingRecyclerView);
        this.teamBBenchRecyclerView = (RecyclerView) inflate.findViewById(R.id.teamBBenchRecyclerView);
        this.teamAPlayingTextView = (TextView) inflate.findViewById(R.id.teamAPlayingTitle);
        this.teamBPlayingTextView = (TextView) inflate.findViewById(R.id.teamBPlayingTitle);
        this.teamAPlayingUp = (ImageView) inflate.findViewById(R.id.teamAPlayingUp);
        this.teamABenchUp = (ImageView) inflate.findViewById(R.id.teamABenchUp);
        this.teamBPlayingUp = (ImageView) inflate.findViewById(R.id.teamBPlayingUp);
        this.teamBBenchUp = (ImageView) inflate.findViewById(R.id.teamBBenchUp);
        this.teamAPlayingUp.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!PlayingSquadFragment.this.teamAPlayingBoolean.booleanValue()) {
                    PlayingSquadFragment.this.teamAPlayingBoolean = true;
                    PlayingSquadFragment.this.teamAPlayingUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    PlayingSquadFragment.this.teamAPlayingRecyclerView.setVisibility(View.GONE);
                    return;
                }
                PlayingSquadFragment.this.teamAPlayingBoolean = false;
                PlayingSquadFragment.this.teamAPlayingUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                PlayingSquadFragment.this.teamAPlayingRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        this.teamABenchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PlayingSquadFragment.this.teamABenchBoolean.booleanValue()) {
                    PlayingSquadFragment.this.teamABenchBoolean = true;
                    PlayingSquadFragment.this.teamABenchUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    PlayingSquadFragment.this.teamABenchRecyclerView.setVisibility(View.GONE);
                    return;
                }
                PlayingSquadFragment.this.teamABenchBoolean = false;
                PlayingSquadFragment.this.teamABenchUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                PlayingSquadFragment.this.teamABenchRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        this.teamBPlayingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PlayingSquadFragment.this.teamBPlayingBoolean.booleanValue()) {
                    PlayingSquadFragment.this.teamBPlayingBoolean = true;
                    PlayingSquadFragment.this.teamBPlayingUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    PlayingSquadFragment.this.teamBPlayingRecyclerView.setVisibility(View.GONE);
                    return;
                }
                PlayingSquadFragment.this.teamBPlayingBoolean = false;
                PlayingSquadFragment.this.teamBPlayingUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                PlayingSquadFragment.this.teamBPlayingRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        this.teamBBenchUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PlayingSquadFragment.this.teamBBenchBoolean.booleanValue()) {
                    PlayingSquadFragment.this.teamBBenchBoolean = true;
                    PlayingSquadFragment.this.teamBBenchUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    PlayingSquadFragment.this.teamBBenchRecyclerView.setVisibility(View.GONE);
                    return;
                }
                PlayingSquadFragment.this.teamBBenchBoolean = false;
                PlayingSquadFragment.this.teamBBenchUp.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                PlayingSquadFragment.this.teamBBenchRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        this.teamAPlayingRecyclerView.setHasFixedSize(true);
        this.teamAPlayingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SchedulePlayerAdapter schedulePlayerAdapter = new SchedulePlayerAdapter(this.teamAPlayingModel, getContext(), getActivity());
        this.teamAPlayingAdapter = schedulePlayerAdapter;
        this.teamAPlayingRecyclerView.setAdapter(schedulePlayerAdapter);
        this.teamBPlayingRecyclerView.setHasFixedSize(true);
        this.teamBPlayingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SchedulePlayerAdapter schedulePlayerAdapter2 = new SchedulePlayerAdapter(this.teamBPlayingModel, getContext(), getActivity());
        this.teamBPlayingAdapter = schedulePlayerAdapter2;
        this.teamBPlayingRecyclerView.setAdapter(schedulePlayerAdapter2);
        this.teamABenchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SchedulePlayerAdapter schedulePlayerAdapter3 = new SchedulePlayerAdapter(this.teamABenchModel, getContext(), getActivity());
        this.teamABenchAdapter = schedulePlayerAdapter3;
        this.teamABenchRecyclerView.setAdapter(schedulePlayerAdapter3);
        this.teamBBenchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SchedulePlayerAdapter schedulePlayerAdapter4 = new SchedulePlayerAdapter(this.teamBBenchModel, getContext(), getActivity());
        this.teamBBenchAdapter = schedulePlayerAdapter4;
        this.teamBBenchRecyclerView.setAdapter(schedulePlayerAdapter4);
        UpdateData();
        return inflate;
    }

    private void UpdateData() {
        new GetPlayingSquadTitle().execute(new Void[0]);
        new TeamAPlayingSquadContent().execute(new Void[0]);
        new TeamBPlayingSquadContent().execute(new Void[0]);
        new TeamABenchSquadContent().execute(new Void[0]);
        new TeamBBenchSquadContent().execute(new Void[0]);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public class GetPlayingSquadTitle extends AsyncTask<Void, Void, Void> {
        private GetPlayingSquadTitle() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(r3);
            if (PlayingSquadFragment.this.teamAPlaying != null) {
                PlayingSquadFragment.this.teamAPlayingCardView.setVisibility(View.VISIBLE);
                PlayingSquadFragment.this.teamAPlayingTextView.setText(PlayingSquadFragment.this.teamAPlaying);
            }
            if (PlayingSquadFragment.this.teamBPlaying != null) {
                PlayingSquadFragment.this.teamBPlayingCardView.setVisibility(View.VISIBLE);
                PlayingSquadFragment.this.teamBPlayingTextView.setText(PlayingSquadFragment.this.teamBPlaying);
            }
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements eq = Jsoup.connect(PlayingSquadFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0);
                int size = eq.size();
                for (int i = 0; i < size; i++) {
                    PlayingSquadFragment.this.teamAPlaying = eq.get(i).select("div.cb-col.cb-col-100.cb-mat-fct-itm.text-bold").eq(0).text();
                    PlayingSquadFragment.this.teamBPlaying = eq.get(i).select("div.cb-col.cb-col-100.cb-mat-fct-itm.text-bold").eq(1).text();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("errorBd", e.getLocalizedMessage());
                return null;
            }
        }
    }


    public class TeamAPlayingSquadContent extends AsyncTask<Void, Void, Void> {
        private TeamAPlayingSquadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayingSquadFragment.this.teamAPlayingModel.clear();
            PlayingSquadFragment.this.teamAPlayingAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r3) {
            super.onPostExecute(  r3);
            PlayingSquadFragment.this.teamAPlayingAdapter.notifyDataSetChanged();
            if (PlayingSquadFragment.this.teamAPlayingAdapter.getItemCount() == 0) {
                PlayingSquadFragment.this.squadNotFound.setVisibility(View.VISIBLE);
                PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamAPlayingCardView.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamAPlayingRecyclerView.setVisibility(View.GONE);
                return;
            }
            PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
            PlayingSquadFragment.this.squadNotFound.setVisibility(View.GONE);
            PlayingSquadFragment.this.teamAPlayingCardView.setVisibility(View.VISIBLE);
            PlayingSquadFragment.this.teamAPlayingRecyclerView.setVisibility(View.VISIBLE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayingSquadFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0).select("div.cb-col.cb-col-100").eq(2).select("div").select("a.text-hvr-underline");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    PlayingSquadFragment.this.teamAPlayingModel.add(new SchedulePlayer(select.get(i).select("a").text(), select.get(i).select("a").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "doInBackground: " + e.getMessage());
                return null;
            }
        }
    }

    public class TeamBPlayingSquadContent extends AsyncTask<Void, Void, Void> {
        private TeamBPlayingSquadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayingSquadFragment.this.teamBPlayingModel.clear();
            PlayingSquadFragment.this.teamBPlayingAdapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            PlayingSquadFragment.this.teamBPlayingAdapter.notifyDataSetChanged();
            if (PlayingSquadFragment.this.teamBPlayingAdapter.getItemCount() == 0) {
                PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamBPlayingCardView.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamBPlayingRecyclerView.setVisibility(View.GONE);
                return;
            }
            PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
            PlayingSquadFragment.this.teamBPlayingCardView.setVisibility(View.VISIBLE);
            PlayingSquadFragment.this.teamBPlayingRecyclerView.setVisibility(View.VISIBLE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayingSquadFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0).select("div.cb-col.cb-col-100").eq(5).select("div").select("a.text-hvr-underline");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    PlayingSquadFragment.this.teamBPlayingModel.add(new SchedulePlayer(select.get(i).select("a").text(), select.get(i).select("a").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "doInBackground: " + e.getMessage());
                return null;
            }
        }
    }


    public class TeamABenchSquadContent extends AsyncTask<Void, Void, Void> {
        private TeamABenchSquadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayingSquadFragment.this.teamABenchModel.clear();
            PlayingSquadFragment.this.teamABenchAdapter.notifyDataSetChanged();
        }

        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            PlayingSquadFragment.this.teamABenchAdapter.notifyDataSetChanged();
            if (PlayingSquadFragment.this.teamABenchAdapter.getItemCount() == 0) {
                PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamABenchRecyclerView.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamABenchCardView.setVisibility(View.GONE);
                return;
            }
            PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
            PlayingSquadFragment.this.teamABenchRecyclerView.setVisibility(View.VISIBLE);
            PlayingSquadFragment.this.teamABenchCardView.setVisibility(View.VISIBLE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayingSquadFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0).select("div.cb-col.cb-col-100").eq(3).select("div").select("a.text-hvr-underline");
                int size = select.size();
                for (int i = 0; i < size; i++) {
                    PlayingSquadFragment.this.teamABenchModel.add(new SchedulePlayer(select.get(i).select("a").text(), select.get(i).select("a").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "doInBackground: " + e.getMessage());
                return null;
            }
        }
    }

    public class TeamBBenchSquadContent extends AsyncTask<Void, Void, Void> {
        private TeamBBenchSquadContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PlayingSquadFragment.this.teamBBenchModel.clear();
            PlayingSquadFragment.this.teamBBenchAdapter.notifyDataSetChanged();
        }
        @Override
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            PlayingSquadFragment.this.teamBBenchAdapter.notifyDataSetChanged();
            if (PlayingSquadFragment.this.teamBBenchAdapter.getItemCount() == 0) {
                PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamBBenchRecyclerView.setVisibility(View.GONE);
                PlayingSquadFragment.this.teamBBenchCardView.setVisibility(View.GONE);
                return;
            }
            PlayingSquadFragment.this.progressBar.setVisibility(View.GONE);
            PlayingSquadFragment.this.teamBBenchRecyclerView.setVisibility(View.VISIBLE);
            PlayingSquadFragment.this.teamBBenchCardView.setVisibility(View.VISIBLE);
        }

        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayingSquadFragment.this.url.replace("live-cricket-scores", "cricket-match-facts")).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div.cb-scrd-lft-col").select("div.cb-col-rt").eq(0).select("div.cb-col.cb-col-100").eq(6).select("div").select("a.text-hvr-underline");
                int size = select.size();
//                Log.d(FirebaseAnalytics.Param.ITEMS, "doInBackground: " + size);
                for (int i = 0; i < size; i++) {
                    PlayingSquadFragment.this.teamBBenchModel.add(new SchedulePlayer(select.get(i).select("a").text(), select.get(i).select("a").attr("href")));
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "doInBackground: " + e.getMessage());
                return null;
            }
        }
    }
}
