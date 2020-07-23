/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ExtensionPoint implements UMLComponent {
    public final String id = "ExtensionPoint";
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ExtensionPoint{" +
                "name='" + name + '\'' +
                ", extpoints=" + extpoints +
                '}';
    }

    @Override
    public String id() {
        return id;
    }
}
