package com.truecodes.worldcuptv.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.AdManager;
import com.truecodes.worldcuptv.Adapters.RecordsAdapters;
import com.truecodes.worldcuptv.OnClickListener.OnClickInterface;
import com.truecodes.worldcuptv.R;

public class RecordsScreen extends AppCompatActivity implements OnClickInterface {
    AdManager adManager;
    FrameLayout frameLayout;
    int[] recordsImageArray = {R.drawable.highest_totals, R.drawable.most_runs, R.drawable.high_score, R.drawable.highest_avareage, R.drawable.duck, R.drawable.six, R.drawable.wicket, R.drawable.ball};
    String[] recordsTextArray;
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_records_screen);
        this.frameLayout = (FrameLayout) findViewById(R.id.bannerContainer);
        AdManager adManager = new AdManager(getApplicationContext(), this);
        this.adManager = adManager;
        adManager.loadBanner(this.frameLayout);
        this.recordsTextArray = getResources().getStringArray(R.array.recordsArrayList);
        RecyclerView recyclerView =   findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.setAdapter(new RecordsAdapters(this.recordsImageArray, this.recordsTextArray, this));
    }

    @Override
    public void onItemClickListener(int i) {
        if (i == 0) {
            int[] iArr = {R.drawable.oman, R.drawable.afghanistan, R.drawable.uae, R.drawable.hong_kong, R.drawable.uae, R.drawable.afghanistan, R.drawable.ind, R.drawable.oman, R.drawable.afghanistan, R.drawable.pakistan};
            String[] stringArray = getResources().getStringArray(R.array.highestTotalTeamName);
            String[] stringArray2 = getResources().getStringArray(R.array.highestTotalScore);
            String[] stringArray3 = getResources().getStringArray(R.array.highestTotalRunRate);
            Intent intent = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent.putExtra("teamImage", iArr);
            intent.putExtra("teamName", stringArray);
            intent.putExtra("teamScore", stringArray2);
            intent.putExtra("teamRunRate", stringArray3);
            intent.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent);
            this.adManager.showInterstitial();
        } else if (i == 1) {
            int[] iArr2 = {R.drawable.babar_hayat, R.drawable.sabbir_rahman, R.drawable.profile, R.drawable.virat_kohli, R.drawable.profile, R.drawable.profile, R.drawable.rohit_sharma, R.drawable.tillakaratne_dilshan, R.drawable.mohammad_shahzad, R.drawable.rohan_mustafa};
            String[] stringArray4 = getResources().getStringArray(R.array.mostRunsTeamName);
            String[] stringArray5 = getResources().getStringArray(R.array.mostRunsScore);
            String[] stringArray6 = getResources().getStringArray(R.array.mostRunsRunRate);
            Intent intent2 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent2.putExtra("teamImage", iArr2);
            intent2.putExtra("teamName", stringArray4);
            intent2.putExtra("teamScore", stringArray5);
            intent2.putExtra("teamRunRate", stringArray6);
            intent2.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent2);
            this.adManager.showInterstitial();
        } else if (i == 2) {
            int[] iArr3 = {R.drawable.babar_hayat, R.drawable.rohit_sharma, R.drawable.sabbir_rahman, R.drawable.rohan_mustafa, R.drawable.tillakaratne_dilshan, R.drawable.karim_sadiq, R.drawable.shoaib_malik, R.drawable.profile, R.drawable.najibullah_zadran, R.drawable.shikhar_dhawan};
            String[] stringArray7 = getResources().getStringArray(R.array.highScoresTeamName);
            String[] stringArray8 = getResources().getStringArray(R.array.highScoresScore);
            String[] stringArray9 = getResources().getStringArray(R.array.highScoresRunRate);
            Intent intent3 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent3.putExtra("teamImage", iArr3);
            intent3.putExtra("teamName", stringArray7);
            intent3.putExtra("teamScore", stringArray8);
            intent3.putExtra("teamRunRate", stringArray9);
            intent3.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent3);
            this.adManager.showInterstitial();
        } else if (i == 3) {
            int[] iArr4 = {R.drawable.mahmudullah, R.drawable.virat_kohli, R.drawable.babar_hayat, R.drawable.sarfaraz_ahmed, R.drawable.shoaib_malik, R.drawable.najibullah_zadran, R.drawable.mehran_khan, R.drawable.yuvraj_singh, R.drawable.sabbir_rahman, R.drawable.tillakaratne_dilshan};
            String[] stringArray10 = getResources().getStringArray(R.array.highestAveragesTeamName);
            String[] stringArray11 = getResources().getStringArray(R.array.highestAveragesScore);
            String[] stringArray12 = getResources().getStringArray(R.array.highestAveragesRunRate);
            Intent intent4 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent4.putExtra("teamImage", iArr4);
            intent4.putExtra("teamName", stringArray10);
            intent4.putExtra("teamScore", stringArray11);
            intent4.putExtra("teamRunRate", stringArray12);
            intent4.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent4);
            this.adManager.showInterstitial();
        } else if (i == 4) {
            int[] iArr5 = {R.drawable.mashrafe_mortaza, R.drawable.kinchit_shah, R.drawable.ajinkya_rahane, R.drawable.profile, R.drawable.sultan_ahmed};
            String[] stringArray13 = getResources().getStringArray(R.array.mostDuckTeamName);
            String[] stringArray14 = getResources().getStringArray(R.array.mostDuckScore);
            String[] stringArray15 = getResources().getStringArray(R.array.mostDuckRunRate);
            Intent intent5 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent5.putExtra("teamImage", iArr5);
            intent5.putExtra("teamName", stringArray13);
            intent5.putExtra("teamScore", stringArray14);
            intent5.putExtra("teamRunRate", stringArray15);
            intent5.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent5);
            this.adManager.showInterstitial();
        } else if (i == 5) {
            int[] iArr6 = {R.drawable.babar_hayat, R.drawable.mahmudullah, R.drawable.rohan_mustafa, R.drawable.adnan_ilyas, R.drawable.najibullah_zadran, R.drawable.umar_akmal, R.drawable.sabbir_rahman, R.drawable.profile, R.drawable.asghar_afghan, R.drawable.dawlat_zadran};
            String[] stringArray16 = getResources().getStringArray(R.array.mostSixTeamName);
            String[] stringArray17 = getResources().getStringArray(R.array.mostSixScore);
            String[] stringArray18 = getResources().getStringArray(R.array.mostSixRunRate);
            Intent intent6 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent6.putExtra("teamImage", iArr6);
            intent6.putExtra("teamName", stringArray16);
            intent6.putExtra("teamScore", stringArray17);
            intent6.putExtra("teamRunRate", stringArray18);
            intent6.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent6);
            this.adManager.showInterstitial();
        } else if (i == 6) {
            int[] iArr7 = {R.drawable.amjad_javed, R.drawable.al_amin_hossain, R.drawable.profile, R.drawable.mohammad_amir, R.drawable.profile, R.drawable.profile, R.drawable.jasprit_bumrah, R.drawable.ashish_nehra, R.drawable.rohan_mustafa, R.drawable.rashid_khan};
            String[] stringArray19 = getResources().getStringArray(R.array.mostWicketTeamName);
            String[] stringArray20 = getResources().getStringArray(R.array.mostWicketScore);
            String[] stringArray21 = getResources().getStringArray(R.array.mostWicketRunRate);
            Intent intent7 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent7.putExtra("teamImage", iArr7);
            intent7.putExtra("teamName", stringArray19);
            intent7.putExtra("teamScore", stringArray20);
            intent7.putExtra("teamRunRate", stringArray21);
            intent7.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent7);
            this.adManager.showInterstitial();
        } else if (i != 7) {
        } else {
            int[] iArr8 = {R.drawable.mohammad_nabi, R.drawable.lasith_malinga, R.drawable.aamir_kaleem, R.drawable.hardik_pandya, R.drawable.profile, R.drawable.profile, R.drawable.mehran_khan, R.drawable.mohammad_amir, R.drawable.rohan_mustafa, R.drawable.ashish_nehra};
            String[] stringArray22 = getResources().getStringArray(R.array.bowlingFiguresTeamName);
            String[] stringArray23 = getResources().getStringArray(R.array.bowlingFiguresScore);
            String[] stringArray24 = getResources().getStringArray(R.array.bowlingFiguresRunRate);
            Intent intent8 = new Intent(getApplicationContext(), RecordsDetailsScreen.class);
            intent8.putExtra("teamImage", iArr8);
            intent8.putExtra("teamName", stringArray22);
            intent8.putExtra("teamScore", stringArray23);
            intent8.putExtra("teamRunRate", stringArray24);
            intent8.putExtra("recordName", this.recordsTextArray[i]);
            startActivity(intent8);
            this.adManager.showInterstitial();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        this.adManager.destroyBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.adManager.loadBanner(this.frameLayout);
    }
}