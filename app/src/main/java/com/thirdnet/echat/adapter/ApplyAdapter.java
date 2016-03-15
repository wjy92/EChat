package com.thirdnet.echat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.tr4android.support.extension.widget.CircleImageView;

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
        ((ApplyViewHolder) holder).textViewContent.setText("李世石已报警");
        RxView.clicks(((ApplyViewHolder) holder).view)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                               @Override
                               public void call(Void aVoid) {
                               }
                           }
                );
    }


    @Override
    public int getItemCount() {
        return 50;
    }

    class ApplyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewContent;
        CircleImageView circleIv1, circleIv2, circleIv3, circleIv4;
        ProgressBar progressBar;
        View view;

        public ApplyViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewContent = (TextView) itemView.findViewById(R.id.content);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            circleIv1 = (CircleImageView) itemView.findViewById(R.id.circleIv1);
            circleIv2 = (CircleImageView) itemView.findViewById(R.id.circleIv2);
            circleIv3 = (CircleImageView) itemView.findViewById(R.id.circleIv3);
            circleIv4 = (CircleImageView) itemView.findViewById(R.id.circleIv4);
            circleIv1.setImageResource(R.mipmap.portrait_test0);
            circleIv2.setImageResource(R.mipmap.portrait_test1);
            circleIv3.setImageResource(R.mipmap.portrait_test2);
            circleIv4.setImageResource(R.mipmap.portrait_test0);
            view = itemView;
        }
    }
}
