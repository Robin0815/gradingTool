/*
@author mkowol2s
 */

package Model;

import java.util.ArrayList;
import java.util.List;

public class ExtensionPoint extends ConnectableComponent{
    private String name;
    private List<String> extpoints = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addExtpoint(String s) {
        extpoints.add(s);
    }

    public List<String> getExtpoints() {
        return extpoints;
    }
    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "ExtensionPoint{" +
                "name='" + name + '\'' +
                ", extpoints=" + extpoints +
                '}';
    }

    @Override
    public Elements id() {
        return Elements.EXTENSIONPOINT;
    }
}
