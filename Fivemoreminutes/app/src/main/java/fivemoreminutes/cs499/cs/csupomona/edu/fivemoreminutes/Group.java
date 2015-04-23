package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

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
public class Group extends Fragment {

    private ArrayList<GroupItem> groupItems = new ArrayList<>();
    private GroupItemAdapter listAdapter;
    private int count = 1;

    public static Group newInstance(int page) {
        Bundle args = new Bundle();
        Group fragment = new Group();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.group_fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                groupItems.add(new GroupItem("Group " + count));
                count++;
                listAdapter.notifyDataSetChanged();
            }
        });

        ListView listView = (ListView) view.findViewById(R.id.lv_group);
        listAdapter = new GroupItemAdapter(this.getActivity(), groupItems);
        listView.setAdapter(listAdapter);

        return view;
    }

}
