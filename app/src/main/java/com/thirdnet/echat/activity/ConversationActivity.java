package com.thirdnet.echat.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.thirdnet.echat.R;
import com.thirdnet.echat.adapter.SessionAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConversationActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.rv)
    RecyclerView mRv;

    private MaterialMenuDrawable mMaterialMenuDrawable;

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


        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(new SessionAdapter(this));

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
