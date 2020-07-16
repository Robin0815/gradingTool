/*
@author Michael Kowollik
 */

package Model;

import java.util.ArrayList;

public class Includes {
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

    public Boolean connectsUseCases() {
        for (UMLComponent connectedElement : connectedElements) {
            if (!(connectedElement instanceof UseCase || connectedElement instanceof ExtensionPoint)) {
                return false;
            }
        }
        return true;
    }
}
