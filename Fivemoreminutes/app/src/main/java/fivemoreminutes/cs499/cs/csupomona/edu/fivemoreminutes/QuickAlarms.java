package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Calvin on 4/11/2015.
 */
// In this case, the fragment displays simple text based on the page
public class QuickAlarms extends Fragment {


    public static QuickAlarms newInstance(int page) {
        Bundle args = new Bundle();
        QuickAlarms fragment = new QuickAlarms();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm_fragment, container, false);
        return view;
    }
}