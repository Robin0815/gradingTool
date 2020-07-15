package Model;

import java.util.ArrayList;

public class ExtensionPoint {
    private String name;
    private ArrayList<String> extpoints;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addExtpoint(String s) {
        extpoints.add(s);
    }

    public ArrayList<String> getExtpoints() {
        return extpoints;
    }
}
