package com.thirdnet.echat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirdnet.echat.R;
import com.tr4android.support.extension.widget.CircleImageView;

/**
 * 作者：杨水强
 * 时间：2016/2/29
 */
public class AddDrawerAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public AddDrawerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_activity_add_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class AddViewHolder extends RecyclerView.ViewHolder {
        CircleImageView mPortrait;

        public AddViewHolder(View itemView) {
            super(itemView);
            mPortrait = (CircleImageView) itemView.findViewById(R.id.portrait);
            mPortrait.setImageResource(R.mipmap.portrait_test0);
        }
    }
}
