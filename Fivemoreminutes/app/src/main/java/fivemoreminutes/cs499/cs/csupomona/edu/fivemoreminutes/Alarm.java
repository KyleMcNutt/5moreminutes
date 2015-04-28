package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.app.ListFragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class Alarm extends Fragment {

    private ArrayList<AlarmItem> alarmItems = new ArrayList<>();
    private AlarmItemAdapter listAdapter;
    private int count = 1;

    public static Alarm newInstance(int page) {
        Bundle args = new Bundle();
        Alarm fragment = new Alarm();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.alarm_fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alarmItems.add(new AlarmItem("Alarm " + count));
                count++;
                listAdapter.notifyDataSetChanged();
            }
        });

        ListView listView = (ListView) view.findViewById(R.id.lv_alarm);
        listAdapter = new AlarmItemAdapter(this.getActivity(), alarmItems);
        listView.setAdapter(listAdapter);

        return view;
    }

}
