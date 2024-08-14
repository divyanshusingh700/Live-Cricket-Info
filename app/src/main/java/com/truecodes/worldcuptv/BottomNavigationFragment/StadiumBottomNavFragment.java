package com.truecodes.worldcuptv.BottomNavigationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.Adapters.StadiumAdapter;
import com.truecodes.worldcuptv.R;

public class StadiumBottomNavFragment extends Fragment {
    RecyclerView recyclerView;
    String[] stadiumImage;
    String[] stadiumName;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_stadium_bottom_nav, viewGroup, false);
        stadiumName = getResources().getStringArray(R.array.stadium_name);
        stadiumImage = getResources().getStringArray(R.array.stadium_image);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerView.setAdapter(new StadiumAdapter(stadiumName, stadiumImage, getContext()));
        return inflate;
    }
}