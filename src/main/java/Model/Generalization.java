/*
@author mkowol2s
 */

package Model;

import java.util.List;

public class Generalization extends Relation{
    /*
    public Boolean isSameType() {
        UMLComponent priorElement = connectedElements.get(0);
        for (UMLComponent connectedElement : connectedElements) {
            if (priorElement.getClass() != connectedElement.getClass()) {
                return false;
            }
            priorElement=connectedElement;
        }
        return true;
    */

    @Override
    public String toString() {
        return "Generalization{}";
    }
}
