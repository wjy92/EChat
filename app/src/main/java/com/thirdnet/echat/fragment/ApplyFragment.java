package com.thirdnet.echat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirdnet.echat.R;
import com.thirdnet.echat.adapter.FragmentApplyListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：杨水强
 * 时间：2016/3/10
 */
public class ApplyFragment extends Fragment {

    @Bind(R.id.rv)
    RecyclerView mRv;

    public ApplyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apply, container, false);
        ButterKnife.bind(this, rootView);
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.setAdapter(new FragmentApplyListAdapter(getContext()));
        mRv.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
