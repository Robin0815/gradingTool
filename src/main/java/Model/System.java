/*
@author mkowol2s
 */

package Model;

import Control.Visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class System implements UMLComponent{
    private String name;
    private List<UMLComponent> containedElements = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UMLComponent> getContainedElements() {
        return containedElements;
    }

    public void setContainedElements(List<UMLComponent> containedElements) {
        this.containedElements = containedElements;
    }

    public void addContainedElement(UMLComponent element) {
        this.containedElements.add(element);
    }

    public void removeContainedElement(UMLComponent element) {
        this.containedElements.remove(element);
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "System{" +
                "name='" + name + '\'' +
                ", containedElements=" + containedElements +
                '}';
    }
}
