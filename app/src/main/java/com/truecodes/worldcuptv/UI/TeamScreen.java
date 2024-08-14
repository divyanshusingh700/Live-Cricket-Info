package com.truecodes.worldcuptv.UI;


import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Adapters.HomeAdapters;
import com.truecodes.worldcuptv.OnClickListener.OnClickInterface;
import com.truecodes.worldcuptv.R;


public class TeamScreen extends AppCompatActivity implements OnClickInterface {
    int[] asiaCupIconList = {R.drawable.afghanistan, R.drawable.ban, R.drawable.hong_kong, R.drawable.ind, R.drawable.pakistan, R.drawable.srilanka};
    String[] asiaCupItemList;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_team_screen);
        this.asiaCupItemList = getResources().getStringArray(R.array.asia_cup_team_list);
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.setAdapter(new HomeAdapters(this.asiaCupItemList, this.asiaCupIconList, this));
    }

    @Override
    public void onItemClickListener(int i) {
        Toast.makeText(getApplicationContext(), "Squad Not decided", Toast.LENGTH_SHORT).show();
    }
}