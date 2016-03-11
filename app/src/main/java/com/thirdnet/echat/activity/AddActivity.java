package com.thirdnet.echat.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.thirdnet.echat.R;
import com.thirdnet.echat.adapter.AddDrawerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：杨水强
 * 时间：2016/3/11
 */
public class AddActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.drawer_rv)
    RecyclerView mDrawerRv;


    AddDrawerAdapter mDrawerAdapter;
    /**
     * 左上角导航栏所用图标
     */
    private MaterialMenuDrawable mMaterialMenuDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mMaterialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mMaterialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.ARROW);
        mToolbar.setNavigationIcon(mMaterialMenuDrawable);

        mDrawerRv.setHasFixedSize(true);
        mDrawerRv.setLayoutManager(new LinearLayoutManager(this));
        mDrawerAdapter = new AddDrawerAdapter(this);
        mDrawerRv.setAdapter(mDrawerAdapter);
        mDrawerRv.setItemAnimator(new DefaultItemAnimator());
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
