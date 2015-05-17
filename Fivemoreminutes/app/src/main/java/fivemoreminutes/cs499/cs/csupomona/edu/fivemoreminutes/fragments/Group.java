package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;


import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.AddGroupTask;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.DBHandler;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.GetGroupsTask;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class Group extends Fragment {

    private DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);
    private ArrayList<GroupItem> groupItems = new ArrayList<GroupItem>();
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("New Alarm Group");
                alert.setMessage("Enter the Alarm Group's Name");

                // Set an EditText view to get user input
                final EditText input = new EditText(getActivity());
                input.setSingleLine();
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        addToGroup(value);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });

        ListView listView = (ListView) view.findViewById(R.id.lv_group);
        listAdapter = new GroupItemAdapter(this.getActivity(), groupItems);
        listView.setAdapter(listAdapter);

        Object[] params = { getActivity(), groupItems, listAdapter };
        new GetGroupsTask().execute(params);
        return view;
    }

    public void addToGroup(String groupName) {
        groupItems.add(new GroupItem(groupName, true, groupItems.size()+1));
        listAdapter.notifyDataSetChanged();
        Object[] params = {getActivity(), groupItems, groupName, true, groupItems.size()+1};
        new AddGroupTask().execute(params);
    }
}