package com.team.nee.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import com.umeng.message.PushAgent;



public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager viewpager;

    private List<Fragment> openFragments;
    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //isFirst();
        initData();
        initview();
        PushAgent.getInstance(MainApplication.getInstance()).onAppStart();
    }



    private void initData() {
        openFragments = new ArrayList<Fragment>();
//        Fragment  fragment1 = new BlankFragment_One();
//        Fragment  fragment2 = new Fragment_Two();
//        Fragment  fragment3 = new Fragment_Three();
        Fragment  fragment4 = new Fragment_Four();
        //Fragment  fragment5 = new Fragment_Five();

//        openFragments.add(fragment1);
//        openFragments.add(fragment2);
//        openFragments.add(fragment3);
        openFragments.add(fragment4);
        //openFragments.add(fragment5);

        linearLayout = ((LinearLayout) findViewById(R.id.linearLayout));

        int count = linearLayout.getChildCount();

        for (int i = 0; i < count; i++) {
            linearLayout.getChildAt(i).setEnabled(true);

            linearLayout.getChildAt(i).setOnClickListener(this);

            linearLayout.getChildAt(i).setTag(i);
        }
        
        linearLayout.getChildAt(0).setEnabled(false); 
    }

    private void initview() {
        viewpager = ((ViewPager) findViewById(R.id.viewPager));

        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = linearLayout.getChildCount();
                for (int i = 0; i < count; i++) {
                    linearLayout.getChildAt(i).setEnabled(true);
                }

                linearLayout.getChildAt(position).setEnabled(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int j = (Integer) v.getTag();

        int count = linearLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            linearLayout.getChildAt(i).setEnabled(true);
        }

        linearLayout.getChildAt(j).setEnabled(false);
        viewpager.setCurrentItem(j);

    }


    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    protected void onPause() {
        super.onPause(); 
    }


    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return openFragments.get(position);
        }

        @Override
        public int getCount() {
            return openFragments.size();
        }
    }
}