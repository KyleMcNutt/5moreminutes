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

        TextView nameText = (TextView) view.findViewById(R.id.name);
        nameText.setText(groupItems.get(position).getName());
        Switch groupSwitch = (Switch) view.findViewById(R.id.group_switch);
        groupSwitch.setChecked(true);

        return view;
    }
}
