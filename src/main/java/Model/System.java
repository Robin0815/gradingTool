/*
@author Michael Kowollik
 */

package Model;

import java.util.ArrayList;

public class System implements UMLComponent{
    private String name;
    private ArrayList<UMLComponent> containedElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UMLComponent> getContainedElements() {
        return containedElements;
    }

    public void setContainedElements(ArrayList<UMLComponent> containedElements) {
        this.containedElements = containedElements;
    }

    public void addContainedElement(UMLComponent element) {
        this.containedElements.add(element);
    }

    public void removeContainedElement(UMLComponent element) {
        this.containedElements.remove(element);
    }

    public Boolean noActorsContaines() {
        for (UMLComponent containedElement : containedElements) {
            if (containedElement instanceof Actor) {
                return false;
            }
        }
        return true;
    }
}
