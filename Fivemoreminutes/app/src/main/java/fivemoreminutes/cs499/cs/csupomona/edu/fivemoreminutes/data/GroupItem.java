package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data;

/**
 * Created by Calvin on 4/19/2015.
 */
public class GroupItem {

    private int _id;
    private String name;
    private boolean currentlyOn;

    public GroupItem() {
        this._id = 0;
        this.name = "";
    }
    public GroupItem(String _groupName, boolean _currentlyOn) {
        this._id = 0;
        this.name = _groupName;
        this.currentlyOn = _currentlyOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public boolean getCurrentlyOn() {
        return currentlyOn;
    }

    public void setCurrentlyOn(boolean currentlyOn) {
        this.currentlyOn = currentlyOn;
    }
}
