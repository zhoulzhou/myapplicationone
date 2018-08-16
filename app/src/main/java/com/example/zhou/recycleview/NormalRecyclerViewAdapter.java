package com.example.zhou.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhou.myapplication.R;

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder>{

    private final Context mContext;
    private String[] mTitles;

    public NormalRecyclerViewAdapter(Context context){
        mContext = context;
        mTitles = mContext.getResources().getStringArray(R.array.titles);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycleview_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;

        NormalTextViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_view);
        }
    }
}
