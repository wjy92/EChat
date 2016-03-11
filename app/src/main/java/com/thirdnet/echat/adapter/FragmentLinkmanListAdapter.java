package com.thirdnet.echat.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.thirdnet.echat.activity.MainActivity;
import com.thirdnet.echat.activity.ProfileActivity;
import com.tr4android.support.extension.widget.CircleImageView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * 作者：杨水强
 * 时间：2016/2/19
 */
public class FragmentLinkmanListAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public FragmentLinkmanListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinkmanViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_fragment_linkman_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((LinkmanViewHolder) holder).circleImageView.setImageResource(R.mipmap.portrait_test1);
        RxView.clicks(((LinkmanViewHolder) holder).view)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                               @Override
                               public void call(Void aVoid) {
                                   if (Build.VERSION.SDK_INT >= 21) {

                                       Intent intent = new Intent(mContext, ProfileActivity.class);
                                       ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((MainActivity) mContext
                                               , Pair.create(((LinkmanViewHolder) holder).view, "info")
                                               , Pair.create((View) ((MainActivity) mContext).mAppBar, "appbar")
                                               , Pair.create((View) ((MainActivity) mContext).mToolbar, "toolbar")
//                                               , Pair.create((View) ((LinkmanViewHolder) holder).circleImageView, "portrait")
//                                               , Pair.create((View) ((LinkmanViewHolder) holder).textView, "name")
                                               , Pair.create((View) ((MainActivity) mContext).mCollapsingLayout, "collapsing_layout")
                                       );
                                       mContext.startActivity(intent, activityOptions.toBundle());
                                   } else {
                                       mContext.startActivity(new Intent(mContext, ProfileActivity.class));
                                   }
                               }
                           }
                );

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class LinkmanViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView;
        View view;

        public LinkmanViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.portrait);
            textView = (TextView) itemView.findViewById(R.id.name);
            view = itemView;
        }
    }
}
