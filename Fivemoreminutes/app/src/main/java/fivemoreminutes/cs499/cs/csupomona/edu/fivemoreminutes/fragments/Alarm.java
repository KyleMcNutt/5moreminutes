package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.AlarmItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class Alarm extends Fragment {

    private ArrayList<AlarmItem> alarmItems = new ArrayList<>();
    private AlarmItemAdapter listAdapter;
    private TimePickDialog tpd;


    public static Alarm newInstance(int page) {
        Bundle args = new Bundle();
        Alarm fragment = new Alarm();
        fragment.setArguments(args);
        return fragment;
    }

    public void addToList(int hourOfDay, int minute) {
        AlarmItem toAdd = new AlarmItem(hourOfDay, minute);
        alarmItems.add(toAdd);
        listAdapter.notifyDataSetChanged();
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

        return view;
    }


}
