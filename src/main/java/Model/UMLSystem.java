/*
@author mkowol2s
 */

package Model;

import java.util.ArrayList;
import java.util.List;

public class UMLSystem implements UseCaseSystemElements{
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
    public String toString() {
        return "System{" +
                "name='" + name + '\'' +
                ", containedElements=" + containedElements +
                '}';
    }

    @Override
    public Elements id() {
        return Elements.UMLSYSTEM;
    }
}
