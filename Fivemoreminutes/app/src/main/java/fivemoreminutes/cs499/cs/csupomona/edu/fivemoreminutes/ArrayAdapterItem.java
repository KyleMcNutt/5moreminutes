package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import java.util.List;

/**
 * Created by Calvin on 4/14/2015.
 */
public class ArrayAdapterItem extends ArrayAdapter<SwitchItem> {

    Context _context;
    int LayoutResourceId;
    SwitchItem[] data;

    public ArrayAdapterItem(Context context, int resource, SwitchItem[] objects) {
        super(context, resource, objects);
        System.out.println("got to arrayAdapter");
        _context = context;
        LayoutResourceId = resource;
        data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = ((Activity) _context).getLayoutInflater();
            convertView = inflater.inflate(LayoutResourceId, parent, false);
        }
        SwitchItem switchitem = data[position];
        System.out.println("got to getview" + position);

        Switch swi = switchitem.aSwitch;
        return convertView;
    }

}
