package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data;

/**
 * Created by Calvin on 4/23/2015.
 */
public class AlarmItem {

    private int hour;
    private int minute;
    private int groupKey;
    private int _id;

    public int getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(int groupKey) {
        this.groupKey = groupKey;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public AlarmItem(int hour, int minute, int groupKey){
        this.hour = hour;
        this.minute = minute;
        this.groupKey = groupKey;
    }

    public AlarmItem() {
        this._id = 0;
        this.groupKey = 0;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
