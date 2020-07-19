/*
@author Michael Kowollik
 */

package Model;

import java.util.List;

public class System implements UMLComponent{
    private String name;
    private List<UMLComponent> containedElements;

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

    public Boolean noActorsContained() {
        for (UMLComponent containedElement : containedElements) {
            if (containedElement instanceof Actor) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String toString() {
        return "System{" +
                "name='" + name + '\'' +
                ", containedElements=" + containedElements +
                '}';
    }
}
