package com.truecodes.worldcuptv.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.OnClickListener.OnClickInterface;
import com.truecodes.worldcuptv.R;

public class RecordsAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    OnClickInterface onClickInterface;
    int[] recordsImageArray;
    String[] recordsTextArray;

    public RecordsAdapters(int[] iArr, String[] strArr, OnClickInterface onClickInterface) {
        this.recordsImageArray = iArr;
        this.recordsTextArray = strArr;
        this.onClickInterface = onClickInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_records, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.recordsImageView.setImageResource(this.recordsImageArray[i]);
        viewHolder.recordsTextView.setText(this.recordsTextArray[i]);
    }

    @Override
    public int getItemCount() {
        return this.recordsImageArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recordsImageView;
        TextView recordsTextView;

        public ViewHolder(View view) {
            super(view);
            recordsImageView = view.findViewById(R.id.homeImageViewId);
            recordsTextView = view.findViewById(R.id.homeTextViewId);
            view.setOnClickListener(view2 -> onClickInterface.onItemClickListener(getAdapterPosition()));
        }
    }
}