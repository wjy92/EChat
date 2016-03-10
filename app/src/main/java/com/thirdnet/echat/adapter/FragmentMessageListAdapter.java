package com.thirdnet.echat.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.thirdnet.echat.activity.ConversationActivity;
import com.thirdnet.echat.activity.MainActivity;
import com.tr4android.support.extension.widget.CircleImageView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * 作者：杨水强
 * 时间：2016/2/19
 */
public class FragmentMessageListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private Random mRandom;
    private int[] mResIDs = new int[]{R.mipmap.portrait_test0, R.mipmap.portrait_test1, R.mipmap.portrait_test2};

    public FragmentMessageListAdapter(Context context) {
        mContext = context;
        mRandom = new Random();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_fragment_message_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageViewHolder) holder).circleImageView.setImageResource(mResIDs[mRandom.nextInt(3)]);
        RxView.clicks(((MessageViewHolder) holder).view)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (Build.VERSION.SDK_INT >= 21)
                            mContext.startActivity(new Intent(mContext, ConversationActivity.class)
                                    , ActivityOptions.makeSceneTransitionAnimation((MainActivity) mContext, ((MainActivity) mContext).mAppBar, "appbar").toBundle());
                        else
                            mContext.startActivity(new Intent(mContext, ConversationActivity.class));
                    }
                });

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        View view;

        public MessageViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.portrait);
            view = itemView;
        }
    }
}
