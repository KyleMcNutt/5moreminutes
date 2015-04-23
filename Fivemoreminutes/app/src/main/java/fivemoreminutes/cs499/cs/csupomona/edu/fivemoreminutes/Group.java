package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class Group extends Fragment {

    private ArrayList<GroupItem> groupItems = new ArrayList<>();


    private GroupItemAdapter listAdapter;


    public static Group newInstance(int page) {
        Bundle args = new Bundle();
        Group fragment = new Group();
        fragment.setArguments(args);
        return fragment;
    }

    private void addGroupItems(){
        groupItems.add(new GroupItem("Group"));
        groupItems.add(new GroupItem("Group1"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addGroupItems();
        // code which causes the crash is bellow
        getActivity().setContentView(R.layout.group_fragment);
        listAdapter = new GroupItemAdapter(getActivity(), groupItems);
        ListView listView = (ListView) getActivity().findViewById(R.id.lv_group);
        listView.setAdapter(listAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_fragment, container, false);
        return view;
    }
}
