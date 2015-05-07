package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data;

/**
 * Created by Calvin on 4/23/2015.
 */
public class AlarmItem {
    private int hour;

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

    private int minute;

    public AlarmItem( int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

}
