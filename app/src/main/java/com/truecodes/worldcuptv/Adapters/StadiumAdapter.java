package com.truecodes.worldcuptv.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.truecodes.worldcuptv.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class StadiumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    String[] stadiumImage;
    String[] stadiumName;

    public StadiumAdapter(String[] strArr, String[] strArr2, Context context) {
        this.stadiumName = strArr;
        this.stadiumImage = strArr2;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.row_stadium, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int i) {
        ViewHolder viewHolder = (ViewHolder) vHolder;
        viewHolder.textView.setText(this.stadiumName[i]);
        Picasso.get().load(this.stadiumImage[i]).into(viewHolder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception exc) {
                viewHolder.imageView.setImageResource(R.drawable.question);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.stadiumName.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imageView =view.findViewById(R.id.homeImageViewId);
            this.textView =  view.findViewById(R.id.homeTextViewId);
            this.progressBar = view.findViewById(R.id.progressBar);
        }
    }
}