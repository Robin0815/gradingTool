/*
@author Michael Kowollik
 */

package Model;

import java.util.ArrayList;

public class Generalization {
    private ArrayList<UMLComponent> connectedElements;

    public ArrayList<UMLComponent> getConnectedElements() {
        return connectedElements;
    }

    public void setConnectedElements(ArrayList<UMLComponent> connectedElements) {
        this.connectedElements = connectedElements;
    }

    public void addConnectedElements(UMLComponent component) {
        this.connectedElements.add(component);
    }

    public void removeConnectedElements(UMLComponent component) {
        this.connectedElements.remove(component);
    }

    public Boolean isBinary() {
        return this.connectedElements.size()==2;
    }

    public Boolean isSameType() {
        UMLComponent priorElement = connectedElements.get(0);
        for (UMLComponent connectedElement : connectedElements) {
            if (priorElement.getClass() != connectedElement.getClass()) {
                return false;
            }
            priorElement=connectedElement;
        }
        return true;
    }
}
