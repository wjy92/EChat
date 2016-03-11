package com.thirdnet.echat.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.thirdnet.echat.R;
import com.thirdnet.echat.adapter.ApplyAdapter;
import com.tr4android.support.extension.widget.FloatingActionMenu;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ApplyActivity extends AppCompatActivity {

    private static final int MENU_NOTHING = 0;

    private static final int MENU_ADD = 1;


    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.fab_menu)
    FloatingActionMenu floatingActionMenu;

    @Bind(R.id.appbar)
    AppBarLayout mAppBar;


    /**
     * 悬浮展开按钮
     */
    @Bind(R.id.float_action_button1)
    FloatingActionButton mFloatButton1;

    /**
     * 消息主体RecyclerView
     */
    @Bind(R.id.rv)
    RecyclerView mRv;
    /**
     * 左上角导航栏所用图标
     */
    private MaterialMenuDrawable mMaterialMenuDrawable;

    private ApplyAdapter mAdapter;

    private int mFloatMenuClickPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
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
        mAdapter = new ApplyAdapter(this);
        mRv.setAdapter(mAdapter);
        mRv.setItemAnimator(new DefaultItemAnimator());

        floatingActionMenu.setupWithDimmingView(findViewById(R.id.dimming_view), Color.parseColor("#42000000"));
        floatingActionMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {

            }

            @Override
            public void onMenuCollapsed() {
                if (mFloatMenuClickPosition == MENU_ADD) {
                    mFloatMenuClickPosition = MENU_NOTHING;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT < 21)
                                startActivity(new Intent(ApplyActivity.this, AddActivity.class));
                            else
                                startActivity(new Intent(ApplyActivity.this, AddActivity.class), ActivityOptions.makeSceneTransitionAnimation(ApplyActivity.this, mAppBar, "appbar").toBundle());
                        }
                    }, 300);

                }
            }
        });
        mFloatButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatMenuClickPosition = MENU_ADD;
                floatingActionMenu.collapse();
            }
        });

        


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
