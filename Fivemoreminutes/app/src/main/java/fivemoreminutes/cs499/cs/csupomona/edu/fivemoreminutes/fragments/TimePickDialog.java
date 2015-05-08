package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * Created by Calvin on 5/4/2015.
 */
public class TimePickDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));


// Create a new instance of TimePickerDialog and return it
        return tpd;
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        if (view.isShown()) {
           if(getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:2131361862:1") == null){
               Alarm alarmFrag = (Alarm) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:2131361862:0");
               alarmFrag.addToList(hourOfDay, minute);
           }else {
               Alarm alarmFrag = (Alarm) getActivity().getSupportFragmentManager().findFragmentByTag("android:switcher:2131361862:1");
               alarmFrag.addToList(hourOfDay, minute);
           }
        }
    }
}