/*
@author mkowol2s
 */

package Model;

import java.util.List;

public class Association  implements UMLComponent{
    private List<UMLComponent> connectedElements;
    private String type = null;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isDifferentType() {
        UMLComponent priorElement = connectedElements.get(0);
        for (UMLComponent connectedElement : connectedElements) {
            if (priorElement.getClass() == connectedElement.getClass()) {
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
        return "Association{" +
                "connectedElements=" + connectedElements +
                ", type='" + type + '\'' +
                '}';
    }
}
