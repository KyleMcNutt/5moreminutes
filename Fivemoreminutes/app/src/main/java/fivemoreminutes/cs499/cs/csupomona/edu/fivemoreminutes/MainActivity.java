package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.PagerAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.GetNextAlarmTask;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setTextColor(Color.parseColor("#FFFFFF"));
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }
}

