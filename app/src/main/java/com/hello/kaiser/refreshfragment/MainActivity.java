package com.hello.kaiser.refreshfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        CustomFragmentAdapter adapter = new CustomFragmentAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(10);
        mViewPager.setAdapter(adapter);
    }

    public class CustomFragmentAdapter extends FragmentPagerAdapter {


        public CustomFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return OneFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
