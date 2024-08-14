package com.truecodes.worldcuptv.MainPlayers.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.EnvironmentCompat;
import androidx.fragment.app.Fragment;
import com.truecodes.worldcuptv.R;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class PlayerPersonalInformationFragment extends Fragment {
    private String battingStyle;
    private String battingStyleName;
    private TextView battingStyleNameTextView;
    private TextView battingStyleTextView;
    private TextView birthDayNameTextView;
    private String birthDayPlace;
    private String birthDayPlaceName;
    private TextView birthDayTextView;
    private String born;
    private String bornName;
    private TextView bornNameTextView;
    private TextView bornTextView;
    private String bowlingStyle;
    private String bowlingStyleName;
    private TextView bowlingStyleNameTextView;
    private TextView bowlingStyleTextView;
    private String county;
    private String countyName;
    private TextView countyNameTextView;
    private TextView countyTextView;
    private String height;
    private String heightName;
    private TextView heightNameTextView;
    private TextView heightTextView;
    private String odiBatsman;
    private TextView odiBatsmanTextView;
    private String odiBowler;
    private TextView odiBowlerTextView;
    private LinearLayout personalInformationLayout;
    private ProgressBar personalProgressBar;
    private ConstraintLayout playerHead;
    private String playerImage;
    private ProgressBar playerImageProgressBar;
    private ImageView playerImageView;
    private String playerName;
    private TextView playerNameTextView;
    private LinearLayout playerRankingLayout;
    private String role;
    private String roleName;
    private TextView roleNameTextView;
    private TextView roleTextView;
    private String t20Batsman;
    private TextView t20BatsmanTextView;
    private String t20Bowler;
    private TextView t20BowlerTextView;
    private String testBatsman;
    private TextView testBatsmanTextView;
    private String testBowler;
    private TextView testBowlerTextView;
    private String url;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_player_personal_information, viewGroup, false);
        if (getArguments() != null) {
            url = getArguments().getString(ImagesContract.URL);
        }
        personalProgressBar = inflate.findViewById(R.id.personalProgressBar);
        ConstraintLayout constraintLayout = inflate.findViewById(R.id.playerHead);
        playerHead = constraintLayout;
        constraintLayout.setVisibility(View.GONE);
        playerNameTextView =  inflate.findViewById(R.id.playerName);
        playerImageView = inflate.findViewById(R.id.playerImage);
        ProgressBar progressBar =  inflate.findViewById(R.id.progressBar);
        playerImageProgressBar = progressBar;
        progressBar.setVisibility(View.VISIBLE);
        LinearLayout linearLayout =  inflate.findViewById(R.id.playerRankingLayout);
        playerRankingLayout = linearLayout;
        linearLayout.setVisibility(View.GONE);
        odiBatsmanTextView = inflate.findViewById(R.id.batsmanODIRanking);
        testBatsmanTextView = inflate.findViewById(R.id.batsmanTestRanking);
        t20BatsmanTextView = inflate.findViewById(R.id.batsmanT20Ranking);
        odiBowlerTextView = inflate.findViewById(R.id.bowlerODIRanking);
        testBowlerTextView = inflate.findViewById(R.id.bowlerTestRanking);
        t20BowlerTextView = inflate.findViewById(R.id.bowlerT20Ranking);
        personalInformationLayout =  inflate.findViewById(R.id.personalInformationLayout);
        birthDayTextView = inflate.findViewById(R.id.birth_place);
        birthDayNameTextView = inflate.findViewById(R.id.birthPlaceName);
        personalInformationLayout.setVisibility( View.GONE);
        countyTextView =  inflate.findViewById(R.id.county);
        countyNameTextView = inflate.findViewById(R.id.countyName);
        bornTextView = inflate.findViewById(R.id.born);
        bornNameTextView = inflate.findViewById(R.id.bornName);
        heightTextView = inflate.findViewById(R.id.height);
        heightNameTextView = inflate.findViewById(R.id.heightName);
        roleTextView = inflate.findViewById(R.id.role);
        roleNameTextView = inflate.findViewById(R.id.roleName);
        battingStyleTextView = inflate.findViewById(R.id.battingStyle);
        battingStyleNameTextView = inflate.findViewById(R.id.battingStyleName);
        bowlingStyleTextView = inflate.findViewById(R.id.bowlingStyle);
        bowlingStyleNameTextView = inflate.findViewById(R.id.bowlingStyleName);
        birthDayTextView.setVisibility(View.GONE);
        birthDayNameTextView.setVisibility(View.GONE);
        countyTextView.setVisibility(View.GONE);
        countyNameTextView.setVisibility(View.GONE);
        bornTextView.setVisibility(View.GONE);
        bornNameTextView.setVisibility(View.GONE);
        heightTextView.setVisibility(View.GONE);
        heightNameTextView.setVisibility(View.GONE);
        roleTextView.setVisibility(View.GONE);
        roleNameTextView.setVisibility(View.GONE);
        battingStyleTextView.setVisibility(View.GONE);
        battingStyleNameTextView.setVisibility(View.GONE);
        bowlingStyleTextView.setVisibility(View.GONE);
        bowlingStyleNameTextView.setVisibility(View.GONE);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();
        new GetPlayerProfileContent().execute(new Void[0]);
    }


    private class GetPlayerProfileContent extends AsyncTask<Void, Void, Void> {
        private GetPlayerProfileContent() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        public void onPostExecute(Void r8) {
            super.onPostExecute( r8);
             personalProgressBar.setVisibility(View.GONE);
            if ( playerName != null && ! playerName.equals("")) {
                 playerHead.setVisibility(View.VISIBLE);
                 playerNameTextView.setText( playerName);
                if ( playerImage != null && ! playerImage.equals("")) {
                    Picasso picasso = Picasso.get();
                    picasso.load("https://www.cricbuzz.com" +  playerImage.replace("152x152", "192x192")).into(PlayerPersonalInformationFragment.this.playerImageView, new Callback() { // from class: com.digital.livecricketapp.MainPlayers.Fargments.PlayerPersonalInformationFragment.GetPlayerProfileContent.1
                        @Override
                        public void onSuccess() {
                            playerImageProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception exc) {
                            playerImageProgressBar.setVisibility(View.GONE);
                            playerImageView.setImageResource(R.drawable.profile);
                        }
                    });
                }
            }
            if ((testBatsman != null || odiBatsman != null || t20Batsman != null) && (!testBatsman.equals("") || !odiBatsman.equals("") || !PlayerPersonalInformationFragment.this.t20Batsman.equals(""))) {
                playerRankingLayout.setVisibility(View.VISIBLE);
                testBatsmanTextView.setText(testBatsman);
                odiBatsmanTextView.setText(odiBatsman);
                t20BatsmanTextView.setText(t20Batsman);
            }
            if ((testBowler != null || odiBowler != null || t20Bowler != null) && (!testBowler.equals("") || !odiBowler.equals("") || !PlayerPersonalInformationFragment.this.t20Bowler.equals(""))) {
                playerRankingLayout.setVisibility(View.VISIBLE);
                testBowlerTextView.setText(testBowler);
                odiBowlerTextView.setText(odiBowler);
                t20BowlerTextView.setText(PlayerPersonalInformationFragment.this.t20Bowler);
            }
            if (county != null) {
                countyTextView.setVisibility(View.VISIBLE);
                countyNameTextView.setVisibility(View.VISIBLE);
                countyNameTextView.setText(county);
            } else {
                countyTextView.setVisibility(View.VISIBLE);
                countyNameTextView.setVisibility(View.GONE);
                 countyNameTextView.setText(EnvironmentCompat.MEDIA_UNKNOWN);
            }
            if (( born != null || bornName != null) && (!born.equals("") || !bornName.equals(""))) {
                personalInformationLayout.setVisibility(View.VISIBLE);
                bornTextView.setVisibility(View.VISIBLE);
                bornNameTextView.setVisibility(View.VISIBLE);
                TextView textView =  bornTextView;
                textView.setText( born + ": ");
                 bornNameTextView.setText(bornName);
            }
            if (( birthDayPlace != null || birthDayPlaceName != null) && (!birthDayPlace.equals("") || !birthDayPlaceName.equals(""))) {
                 personalInformationLayout.setVisibility(View.VISIBLE);
                birthDayTextView.setVisibility(View.VISIBLE);
                birthDayNameTextView.setVisibility(View.VISIBLE);
                TextView textView2 = birthDayTextView;
                textView2.setText(birthDayPlace + ": ");
                PlayerPersonalInformationFragment.this.birthDayNameTextView.setText(birthDayPlaceName);
            }
            if ((height != null || heightName != null) && (!PlayerPersonalInformationFragment.this.height.equals("") || !heightName.equals(""))) {
                heightTextView.setVisibility(View.VISIBLE);
                heightNameTextView.setVisibility(View.VISIBLE);
                TextView textView3 = heightTextView;
                textView3.setText(height + ": ");
                heightNameTextView.setText(PlayerPersonalInformationFragment.this.heightName);
            }
            if ((role != null || PlayerPersonalInformationFragment.this.roleName != null) && (!role.equals("") || !roleName.equals(""))) {
                roleTextView.setVisibility(View.VISIBLE);
                roleNameTextView.setVisibility(View.VISIBLE);
                TextView textView4 = roleTextView;
                textView4.setText(role + ": ");
                roleNameTextView.setText(roleName);
            }
            if ((battingStyle != null || battingStyleName != null) && (!battingStyle.equals("") || !battingStyleName.equals(""))) {
                battingStyleTextView.setVisibility(View.VISIBLE);
                battingStyleNameTextView.setVisibility(View.VISIBLE);
                TextView textView5 = battingStyleTextView;
                textView5.setText(battingStyle + ": ");
                battingStyleNameTextView.setText(battingStyleName);
            }
            if (bowlingStyle == null && bowlingStyleName == null) {
                return;
            }
            if ((bowlingStyle.equals("") && bowlingStyleName.equals("")) || bowlingStyle.contains("ICC")) {
                return;
            }
            bowlingStyleTextView.setVisibility(View.VISIBLE);
            bowlingStyleNameTextView.setVisibility(View.VISIBLE);
            TextView textView6 = bowlingStyleTextView;
            textView6.setText(bowlingStyle + ": ");
            bowlingStyleNameTextView.setText(bowlingStyleName);
        }
        @Override
        public Void doInBackground(Void... voidArr) {
            try {
                Elements select = Jsoup.connect(PlayerPersonalInformationFragment.this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get().select("div#page-wrapper").select("div#playerProfile");
                int size = select.size();
                int i = 0;
                int i2 = 0;
                while (i2 < size) {
                    playerImage = select.get(i2).select("div.cb-bg-white").select("div.cb-col-rt").select("img").attr("src");
                    playerName = select.get(i2).select("div.cb-bg-white").select("div.cb-player-name-wrap").select("h1").text();
                    county = select.get(i2).select("div.cb-bg-white").select("div.cb-player-name-wrap").select("h3").text();
                    testBatsman = select.get(i2).select("div.cb-hm-rght").select("div.cb-plyr-rank").eq(4).text();
                    odiBatsman = select.get(i2).select("div.cb-hm-rght").select("div.cb-plyr-rank").eq(5).text();
                    t20Batsman = select.get(i2).select("div.cb-hm-rght").select("div.cb-plyr-rank").eq(6).text();
                    testBowler = select.get(i2).select("div.cb-hm-rght").select("div.cb-plyr-rank").eq(8).text();
                    odiBowler = select.get(i2).select("div.cb-hm-rght").select("div.cb-plyr-rank").eq(9).text();
                    t20Bowler = select.get(i2).select("div.cb-hm-rght").select("div.cb-plyr-rank").eq(10).text();
                    born = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(i).text();
                    bornName = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(1).text();
                    birthDayPlace = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(2).text();
                    birthDayPlaceName = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(3).text();
                    height = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(4).text();
                    heightName = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(5).text();
                    role = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(6).text();
                    roleName = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(7).text();
                    battingStyle = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(8).text();
                    battingStyleName = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(9).text();
                    bowlingStyle = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(10).text();
                    bowlingStyleName = select.get(i2).select("div.cb-hm-rght").select("div.cb-col").eq(11).text();
                    Log.d("playerDetails", "doInBackground: " + born + bowlingStyleName);
                    i2++;
                    i = 0;
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