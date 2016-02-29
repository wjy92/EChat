package com.thirdnet.echat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thirdnet.echat.MyApp;
import com.thirdnet.echat.R;
import com.thirdnet.echat.bean.MessageBean;
import com.thirdnet.echat.bean.PersonBean;
import com.tr4android.support.extension.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：杨水强
 * 时间：2016/2/29
 */
public class SessionAdapter extends RecyclerView.Adapter {

    /**
     * 消息列表
     */
    private List<MessageBean> mMessages;

    private PersonBean mTargetPerson;

    private Context mContext;

    private static final int TYPE_OTHERS = 100;

    private static final int TYPE_MINE = 101;

    public SessionAdapter(Context context) {
        mContext = context;
        mTargetPerson = new PersonBean(1l, R.mipmap.portrait_test1);
        mMessages = new ArrayList<>();
        MessageBean messageBean = new MessageBean(mTargetPerson, "你想和我聊什么？");
        mMessages.add(messageBean);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessages.get(position).getPersonBean().getId() == ((MyApp) mContext.getApplicationContext()).getMyInfo().getId()) {
            return TYPE_MINE;
        } else {
            return TYPE_OTHERS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_MINE) {
            return new SessionMineViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_activity_session_message_mine, parent, false));
        } else {
            return new SessionOthersViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_activity_session_message_others, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SessionMineViewHolder) {
            ((SessionMineViewHolder) holder).portrait.setImageResource(mMessages.get(position).getPersonBean().getPortrait());
            ((SessionMineViewHolder) holder).message.setText(mMessages.get(position).getMsg());
        } else {
            ((SessionOthersViewHolder) holder).portrait.setImageResource(mMessages.get(position).getPersonBean().getPortrait());
            ((SessionOthersViewHolder) holder).message.setText(mMessages.get(position).getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    class SessionOthersViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        CircleImageView portrait;

        public SessionOthersViewHolder(View itemView) {
            super(itemView);
            portrait = (CircleImageView) itemView.findViewById(R.id.portrait);
            message = (TextView) itemView.findViewById(R.id.message);
        }
    }

    class SessionMineViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        CircleImageView portrait;

        public SessionMineViewHolder(View itemView) {
            super(itemView);
            portrait = (CircleImageView) itemView.findViewById(R.id.portrait);
            message = (TextView) itemView.findViewById(R.id.message);
        }
    }
}
