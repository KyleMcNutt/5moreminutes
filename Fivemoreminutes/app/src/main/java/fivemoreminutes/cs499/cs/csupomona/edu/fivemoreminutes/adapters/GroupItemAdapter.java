package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.activities.DrillDownAlarmActivity;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model.SetGroupStateTask;

/**
 * Created by Calvin on 4/19/2015.
 */
public class GroupItemAdapter extends ArrayAdapter<GroupItem>{

    private Context context;
    private ArrayList<GroupItem> groupItems;

    public GroupItemAdapter(Context context, ArrayList<GroupItem> groupItems) {
        super(context, R.layout.group_item, groupItems);
        this.context = context;
        this.groupItems = groupItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_item, parent, false);

        final TextView nameText = (TextView) view.findViewById(R.id.name);
        nameText.setText(groupItems.get(position).getName());
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DrillDownAlarmActivity.class);
                intent.putExtra("GROUP_ID", groupItems.get(position).get_id());
                context.startActivity(intent);
            }
        });
        final Switch groupSwitch = (Switch) view.findViewById(R.id.group_switch);
        groupSwitch.setChecked(groupItems.get(position).getCurrentlyOn());
        groupSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean state = ((Switch) view).isChecked();
                int groupID = groupItems.get(position).get_id();
                Object[] params = {context, groupID, state};
                new SetGroupStateTask().execute(params);
            }
        });

        return view;
    }
}
