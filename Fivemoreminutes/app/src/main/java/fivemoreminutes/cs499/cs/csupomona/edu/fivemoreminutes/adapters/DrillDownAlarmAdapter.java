package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.fragments.Alarm;

/**
 * Created by Calvin on 5/2/2015.
 */
public class DrillDownAlarmAdapter extends FragmentPagerAdapter{
    final int PAGE_COUNT = 1;
    private String tabTitles[] = new String[] { "Alarms"};

    public DrillDownAlarmAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Alarm();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
