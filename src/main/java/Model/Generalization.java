/*
@author mkowol2s
 */

package Model;

import java.util.List;

public class Generalization implements UMLComponent{
    private List<UMLComponent> connectedElements;

    public List<UMLComponent> getConnectedElements() {
        return connectedElements;
    }

    public void setConnectedElements(List<UMLComponent> connectedElements) {
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

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String toString() {
        return "Generalization{" +
                "connectedElements=" + connectedElements +
                '}';
    }
}
