package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

/**
 * Created by Calvin on 4/23/2015.
 */
public class AlarmItem {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public AlarmItem(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
