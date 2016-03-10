package com.thirdnet.echat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dd.morphingbutton.impl.IndeterminateProgressButton;
import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * 作者：杨水强
 * 时间：2016/2/29
 */
public class ApplyAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public ApplyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_activity_apply, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RxView.clicks(((ApplyViewHolder) holder).view)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                               @Override
                               public void call(Void aVoid) {
                                   ((ApplyViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
                                   ((ApplyViewHolder) holder).view.setTranslationZ(2f);
                               }
                           }
                );

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ApplyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewContent;
        ProgressBar progressBar;
        View view;

        public ApplyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewContent = (TextView) itemView.findViewById(R.id.content);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            view = itemView;
        }
    }
}
