/*
@author Michael Kowollik
 */

package Model;

import java.util.List;

public class Includes implements UMLComponent{
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

    public Boolean connectsUseCases() {
        for (UMLComponent connectedElement : connectedElements) {
            if (!(connectedElement instanceof UseCase || connectedElement instanceof ExtensionPoint)) {
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
        return "Includes{" +
                "connectedElements=" + connectedElements +
                '}';
    }
}
