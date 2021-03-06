package cc.metapro.nfc;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by Boollean on 2017/7/19.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitle;
    private Context mContext;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> title, Context context) {
        super(fm);
        setAdapter(fragments,title,context);
    }

    public void setAdapter(List<Fragment> fragments,List<String> title,Context context){
        mFragments = fragments;
        mTitle = title;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}

