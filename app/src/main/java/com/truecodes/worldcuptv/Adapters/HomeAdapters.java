package com.truecodes.worldcuptv.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.OnClickListener.OnClickInterface;
import com.truecodes.worldcuptv.R;

/* loaded from: classes.dex */
public class HomeAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnClickInterface anInterface;
    int[] asiaCupIconLis;
    String[] asiaCupItemList;

    public HomeAdapters(String[] strArr, int[] iArr, OnClickInterface onClickInterface) {
        this.asiaCupItemList = strArr;
        this.asiaCupIconLis = iArr;
        this.anInterface = onClickInterface;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.imageView.setImageResource(this.asiaCupIconLis[i]);
        viewHolder.textView.setText(this.asiaCupItemList[i]);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.asiaCupItemList.length;
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.homeImageViewId);
            this.textView = (TextView) view.findViewById(R.id.homeTextViewId);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.digital.livecricketapp.Adapters.HomeAdapters.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    HomeAdapters.this.anInterface.onItemClickListener(ViewHolder.this.getAdapterPosition());
                }
            });
        }
    }
}