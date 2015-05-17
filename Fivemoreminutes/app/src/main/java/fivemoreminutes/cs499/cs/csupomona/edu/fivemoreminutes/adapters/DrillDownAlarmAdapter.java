package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters;

import android.os.Bundle;
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
    private int groupID;

    public DrillDownAlarmAdapter(FragmentManager fm, int groupID) {
        super(fm);
        this.groupID = groupID;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Bundle bundle = new Bundle();
                bundle.putInt("GROUP_ID", groupID);
                Alarm alarm = new Alarm();
                alarm.setArguments(bundle);
                return alarm;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
