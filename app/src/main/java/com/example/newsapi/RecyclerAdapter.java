package com.example.newsapi;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    public static String TAG = RecyclerAdapter.class.getSimpleName();
    List<HeadlineModel> mData;
    Context context;

    public RecyclerAdapter(Context context, List<HeadlineModel> mData){
        this.context = context;
        this.mData = mData;

    }

    /**
     *
     * Rows are being created
     */
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.article_row, parent,false);
        return new RecyclerViewHolder(row);
    }

    /**
     * Data is being put into each row
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        HeadlineModel headlineModel = mData.get(position);
        holder.headlineTextView.setText(headlineModel.getHeadlineText());
        holder.descriptionTextView.setText(headlineModel.getDescText());
        holder.headlineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setPositiveButton("OK", null);
                alertDialogBuilder.setTitle(mData.get(holder.getAdapterPosition()).getHeadlineText());
                alertDialogBuilder.setMessage(mData.get(holder.getAdapterPosition()).getDescText());
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    /**
     * @return  get the total count to see if more rows need to be created
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * declare what views are going to be used for each row
     */
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView headlineTextView;
        TextView descriptionTextView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            headlineTextView = itemView.findViewById(R.id.hTitle);
            descriptionTextView = itemView.findViewById(R.id.hDescription);
        }
    }
}
