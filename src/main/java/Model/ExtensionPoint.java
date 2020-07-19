/*
@author Michael Kowollik
 */

package Model;

import java.util.List;

public class ExtensionPoint implements UMLComponent {
    private String name;
    private List<String> extpoints;

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
}
