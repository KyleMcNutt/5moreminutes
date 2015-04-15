package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import android.app.Activity;
import android.content.Context;
import android.widget.Switch;

/**
 * Created by Calvin on 4/12/2015.
 */
public class SwitchItem extends Activity{
    String text;
    Switch aSwitch;

    public SwitchItem(String text){
        this.text = text;
        aSwitch = new Switch(this);
        System.out.println("got to switch item");
        aSwitch.setText(text);
    }
}
