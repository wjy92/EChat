package com.thirdnet.echat.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.thirdnet.echat.activity.ApplyActivity;
import com.thirdnet.echat.activity.MainActivity;
import com.tr4android.support.extension.widget.CircleImageView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * 作者：杨水强
 * 时间：2016/2/19
 */
public class FragmentApplyListAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public FragmentApplyListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_fragment_apply_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ApplyViewHolder) holder).circleImageView.setImageResource(R.mipmap.test_ic_od);
        RxView.clicks(((ApplyViewHolder) holder).view)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                               @Override
                               public void call(Void aVoid) {
                                   if (Build.VERSION.SDK_INT >= 21)
                                       mContext.startActivity(new Intent(mContext, ApplyActivity.class)
                                               , ActivityOptions.makeSceneTransitionAnimation((MainActivity) mContext, ((MainActivity) mContext).mAppBar, "appbar").toBundle());
                                   else
                                       mContext.startActivity(new Intent(mContext, ApplyActivity.class));
                               }
                           }
                );

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ApplyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView;
        View view;

        public ApplyViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.portrait);
            textView = (TextView) itemView.findViewById(R.id.name);
            view = itemView;
        }
    }
}
