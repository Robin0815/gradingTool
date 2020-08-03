/*
@author mkowol2s
 */

package Model;

import java.util.ArrayList;
import java.util.List;

public class UMLSystem implements UseCaseSystemElement{
    private String name;
    private List<UseCaseSystemElement> containedElements = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UseCaseSystemElement> getContainedElements() {
        return containedElements;
    }

    public void setContainedElements(List<UseCaseSystemElement> containedElements) {
        this.containedElements = containedElements;
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
