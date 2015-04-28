package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Calvin on 4/19/2015.
 */
public class AlarmItemAdapter extends ArrayAdapter<AlarmItem>{

    private Context context;
    private ArrayList<AlarmItem> alarmItems;

    public AlarmItemAdapter(Context context, ArrayList<AlarmItem> alarmItems) {
        super(context, R.layout.alarm_item, alarmItems);
        this.context = context;
        this.alarmItems = alarmItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alarm_item, parent, false);



        TextView nameText = (TextView) view.findViewById(R.id.name);
        nameText.setText(alarmItems.get(position).getName());
        Switch groupSwitch = (Switch) view.findViewById(R.id.alarm_switch);
        groupSwitch.setChecked(true);

        return view;
    }
}
