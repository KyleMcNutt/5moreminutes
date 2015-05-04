package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes;

import java.util.UUID;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class GroupModel {

    private int _id;
    private String _groupName;

    public GroupModel() {
        this._id = 0;
        this._groupName = "";
    }

    public GroupModel(String _groupName) {
        this._id = 0;
        this._groupName = _groupName;
    }

    public String get_groupName() {
        return _groupName;
    }

    public void set_groupName(String _groupName) {
        this._groupName = _groupName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
