/*
@author mkowol2s
 */

package Model;

import java.util.List;

public class ConditionRelation implements UMLComponent{
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

    public boolean isConnectedToNote(){
        if(connectedElements.size()==1){
            return connectedElements.get(0) instanceof Note;
        }
        return false;
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String toString() {
        return "ConditionRelation{" +
                "connectedElements=" + connectedElements +
                '}';
    }
}
