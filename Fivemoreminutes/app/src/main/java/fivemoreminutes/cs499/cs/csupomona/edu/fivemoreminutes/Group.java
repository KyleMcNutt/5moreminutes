package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

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
//        SwitchItem[] temp = new SwitchItem[2];
//        temp[0] = new SwitchItem("group");
//        System.out.println("SwitchItem1 Added");
//        temp[1] = new SwitchItem("group1");
//        System.out.println("SwitchItem2 Added");
//        ArrayAdapterItem adapter = new ArrayAdapterItem(this.getActivity().getApplicationContext(), R.layout.group_fragment,temp);
        ListView lv = (ListView)getActivity().findViewById(R.id.lv_group);
//        lv.setAdapter(adapter);
        return lv;
    }
}