package com.thirdnet.echat.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.thirdnet.echat.adapter.SessionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class ConversationActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    /**
     * 消息主体RecyclerView
     */
    @Bind(R.id.rv)
    RecyclerView mRv;

    /**
     * 发送按钮
     */
    @Bind(R.id.send)
    ImageView mSend;

    /**
     * 消息内容编辑文本
     */
    @Bind(R.id.message_edittext)
    EditText mMessage;

    /**
     * 左上角导航栏所用图标
     */
    private MaterialMenuDrawable mMaterialMenuDrawable;


    private SessionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mMaterialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mToolbar.setNavigationIcon(mMaterialMenuDrawable);
        mMaterialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.BURGER);
        mMaterialMenuDrawable.setTransformationDuration(600);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mMaterialMenuDrawable.animateIconState(MaterialMenuDrawable.IconState.ARROW);
            }
        });

        RxView.clicks(mSend).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (!TextUtils.isEmpty(mMessage.getText())) {
                    mAdapter.newMessage(mMessage.getText().toString());
                    mMessage.setText("");
                }
            }
        });


        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SessionAdapter(this, mRv);
        mRv.setAdapter(mAdapter);
        mRv.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onBackPressed() {
        mMaterialMenuDrawable.animateIconState(MaterialMenuDrawable.IconState.BURGER);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_conversation, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
