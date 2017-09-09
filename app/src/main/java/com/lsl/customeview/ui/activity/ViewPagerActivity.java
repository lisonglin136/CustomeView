package com.lsl.customeview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lsl.customeview.R;
import com.lsl.customeview.ui.fragment.TranslateFragment;
import com.lsl.customeview.widget.WelcompagerTransformer;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 15:44
 * 描述：viewpager各种切换效果
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class ViewPagerActivity extends AppCompatActivity {


    private ViewPager vp;
    private int[] layouts = {
            R.layout.welcome1,
            R.layout.welcome2,
            R.layout.welcome3
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        vp = (ViewPager)findViewById(R.id.vp);

        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        vp.setPageTransformer(true, new WelcompagerTransformer());
        vp.setAdapter(adapter);
    }

    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layouts[position]);
            f.setArguments(bundle );
            return f;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }

    }

}
