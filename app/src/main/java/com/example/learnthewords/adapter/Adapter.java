package com.example.learnthewords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnthewords.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MoreFragmentRowViewHolder>{

    private ArrayList<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;


    public Adapter(Context context, ArrayList<String> data ) {
        this.mContext = context;
        this.mData = data;

    }

    @NonNull
    @Override
    public MoreFragmentRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_frame, parent, false);
        return new MoreFragmentRowViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MoreFragmentRowViewHolder holder, int position) {
        String item = mData.get(position);
        holder.myTextView.setText(item);
        holder.myTextView.setTextColor(Color.BLACK);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MoreFragmentRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView signUpTv;
        TextView myTextView;

        MoreFragmentRowViewHolder(View view) {
            super(view);
            this.myTextView = view.findViewById(R.id.text_more_list);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public String getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void changeAdapter(ArrayList<String> items){
        mData.clear();
        mData = items;
        notifyDataSetChanged();
    }

}