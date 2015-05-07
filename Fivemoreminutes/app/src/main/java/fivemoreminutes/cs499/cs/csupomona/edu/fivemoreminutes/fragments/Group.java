package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.activities.DrillDownAlarmActivity;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.GroupItemAdapter;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class Group extends Fragment {

    private ArrayList<AlarmItem> test = new ArrayList<>();
    private ArrayList<GroupItem> groupItems = new ArrayList<>();
    private GroupItemAdapter listAdapter;
    private ListView listView;

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
        final View view = inflater.inflate(R.layout.group_fragment, container, false);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.group_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText editText = (EditText) view.findViewById(R.id.group_name);
                editText.setVisibility(View.VISIBLE);
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            String groupName = editText.getText().toString();

                            if (!isGroup(groupName)) {
                                groupItems.add(new GroupItem(groupName));
                                listAdapter.notifyDataSetChanged();
                            }
                            handled = true;
                            editText.setVisibility(View.INVISIBLE);
                            editText.setText("");
                        }
                        return handled;
                    }
                });
            }
        });

        listView = (ListView) view.findViewById(R.id.lv_group);
        listAdapter = new GroupItemAdapter(this.getActivity(), groupItems);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DrillDownAlarmActivity.class);
                getActivity().startActivity(intent);
            }
        });
        listView.setAdapter(listAdapter);
        return view;
    }

    private boolean isGroup(String groupName) {
        //checks if a string is in the group arraylist.
        for (int i = 0; i < groupItems.size(); i++) {
            if (groupName.equals(groupItems.get(i).getName())) {
                return true;
            }
        }
        return false;
    }


}
