package com.example.newsapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    public static String TAG = RecyclerAdapter.class.getSimpleName();
    String[] titles;

    public RecyclerAdapter(String[] mTitles){
        titles = mTitles;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.article_row, parent,false);
        return new RecyclerViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.headlineTextView.setText(titles[position]);
        holder.headlineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),titles[holder.getAdapterPosition()], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView headlineTextView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            headlineTextView = itemView.findViewById(R.id.title_row);
        }
    }
}
