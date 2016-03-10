package com.thirdnet.echat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding.view.RxView;
import com.thirdnet.echat.R;
import com.thirdnet.echat.fragment.LinkmanFragment;
import com.thirdnet.echat.fragment.MessageFragment;
import com.tr4android.support.extension.widget.CircleImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.appbar)
    public AppBarLayout mAppBar;

    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.vp)
    ViewPager mVp;

    @Bind(R.id.tl)
    TabLayout tl;

    @Bind(R.id.toolbar_layout)
    public CollapsingToolbarLayout mCollapsingLayout;


    /**
     * TabLayout中的显示标题
     */
    private List<String> mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (mTitles == null)
            mTitles = Arrays.asList(getResources().getString(R.string.main_tab1), getResources().getString(R.string.main_tab2), getResources().getString(R.string.main_tab3));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        final CircleImageView portrait = (CircleImageView) mNavigationView.getHeaderView(0).findViewById(R.id.portrait);
        portrait.setImageResource(R.mipmap.portrait_test1);
        RxView.clicks(portrait).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });


        mVp.setOffscreenPageLimit(3);
        mVp.setAdapter(new VpAdapter(getSupportFragmentManager()));

        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setupWithViewPager(mVp);

    }


    class VpAdapter extends FragmentPagerAdapter {
        List<Fragment> mList;

        public VpAdapter(FragmentManager fm) {
            super(fm);
            mList = new ArrayList<>();
            mList.add(new MessageFragment());
            mList.add(new MessageFragment());
            mList.add(new LinkmanFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position % getCount());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.message) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

//        DrawerLayout drawer = $(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
