package com.truecodes.worldcuptv.UI;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Adapters.ScheduleAdapters;
import com.truecodes.worldcuptv.R;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

public class ScheduleScreen extends AppCompatActivity {
    String[] dateList;
    String[] firstTeamName;
    String[] matchNo;
    RecyclerView recyclerView;
    String[] secondTeamName;
    int[] firstTeam = {R.drawable.ban, R.drawable.hong_kong, R.drawable.afghanistan, R.drawable.ind, R.drawable.pakistan, R.drawable.afghanistan, R.drawable.ban, R.drawable.afghanistan, R.drawable.pakistan, R.drawable.afghanistan};
    int[] secondTeam = {R.drawable.srilanka, R.drawable.pakistan, R.drawable.srilanka, R.drawable.hong_kong, R.drawable.ind, R.drawable.ban, R.drawable.ind, R.drawable.pakistan, R.drawable.afghanistan, R.drawable.ind};


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_schedule_screen);
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(this, getLifecycle());
        builder.getDialogProperties().setConnectionCallback(new ConnectionCallback() {
            @Override
            public void hasActiveConnection(boolean z) {
            }
        });
        builder.build();
        firstTeamName = getResources().getStringArray(R.array.scheduleOneTeam);
        secondTeamName = getResources().getStringArray(R.array.scheduleTwoTeam);
        matchNo = getResources().getStringArray(R.array.scheduleMatchNo);
        dateList = getResources().getStringArray(R.array.scheduleDate);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(new ScheduleAdapters(this.firstTeam, this.secondTeam, this.firstTeamName, this.secondTeamName, this.matchNo, this.dateList));
    }
}