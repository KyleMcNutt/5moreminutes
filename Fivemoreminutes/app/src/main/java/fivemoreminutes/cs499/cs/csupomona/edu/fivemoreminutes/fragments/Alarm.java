package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.AlarmItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.AddGroupAlarm;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.GetGroupAlarms;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.GetGroupsTask;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class Alarm extends Fragment {

    private ArrayList<AlarmItem> alarmItems = new ArrayList<>();
    private AlarmItemAdapter listAdapter;
    private TimePickDialog tpd;
    private int groupID;


    public static Alarm newInstance(int page) {
        Bundle args = new Bundle();
        Alarm fragment = new Alarm();
        fragment.setArguments(args);
        return fragment;
    }

    public void addToList(int hourOfDay, int minute) {
        AlarmItem toAdd = new AlarmItem(hourOfDay, minute, this.groupID);
        alarmItems.add(toAdd);
        listAdapter.notifyDataSetChanged();
        //fire async method for adding AlarmItem to group_alarm table
        if(this.getTag().equals("android:switcher:2131361862:0")) {
            Object[] params = { getActivity(), toAdd.getHour(), toAdd.getMinute(), toAdd.getGroupKey() };
            new AddGroupAlarm().execute(params);
        }
    }

    public void setAlarmItems(ArrayList<AlarmItem> alarmItems) {
        this.alarmItems = alarmItems;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_fragment, container, false);
        if(this.getTag().equals("android:switcher:2131361862:0")) {
            int groupID = getArguments().getInt("GROUP_ID");
            this.groupID = groupID;
        }


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.alarm_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpd = new TimePickDialog();
                tpd.show(getFragmentManager(), "timePick");

            }
        });

        ListView listView = (ListView) view.findViewById(R.id.lv_alarm);
        listAdapter = new AlarmItemAdapter(this.getActivity(), alarmItems);
        listView.setAdapter(listAdapter);

        if(this.getTag().equals("android:switcher:2131361862:0")) {
            Object[] params = { getActivity(), alarmItems, listAdapter, this.groupID };
            new GetGroupAlarms().execute(params);
        }

        return view;
    }


}
