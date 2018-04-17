package com.example.shriji.testapp1;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class ScrollingActivity extends AppCompatActivity {

    private AppCompatImageView mAppCompatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppCompatImageView = (AppCompatImageView) findViewById(R.id.img11);

        appBarLayout.addOnOffsetChangedListener(mOnOffsetChangedListener);


/*
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());

            }
        });
*/

    }

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 30;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;

    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            float percentage = 1 - ((float)Math.abs(verticalOffset)/appBarLayout.getTotalScrollRange());
            Log.d("TAG", "==== onOffsetChanged: "+percentage);
            //mAppCompatImageView.setAlpha(percentage);
            if(percentage >= 0.4){
                mAppCompatImageView.setScaleX(percentage);
                mAppCompatImageView.setScaleY(percentage);
                mAppCompatImageView.setAlpha((float) (percentage+(0.9)));
            }


            //mAppCompatImageView.setScaleY(percentage/100);
        }
    };

    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener0 = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (mMaxScrollSize == 0)
                mMaxScrollSize = appBarLayout.getTotalScrollRange();

            int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
                mIsAvatarShown = false;

                mAppCompatImageView.animate()
                        .scaleY(percentage).scaleX(0)
                        .setDuration(300)
                        .start();

            }

            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
                mIsAvatarShown = true;

                mAppCompatImageView.animate()
                        .scaleY(1).scaleX(1)
                        //.setDuration(300)
                        .start();

            }
        }
    };



    AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener1 = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            verticalOffset = Math.abs(verticalOffset);
            float halfScrollRange = (int) (appBarLayout.getTotalScrollRange() * 0.5f);
            float ratio = (float) verticalOffset / halfScrollRange;
            ratio = Math.max(0f, Math.min(1f, ratio));
            ratio = 1-ratio;
            ViewCompat.setAlpha(mAppCompatImageView, ratio);

        }
    };



}
