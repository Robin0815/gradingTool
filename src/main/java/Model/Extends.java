/*
@author mkowol2s
 */

package Model;

import java.util.List;

public class Extends extends Relation{

    /*public Boolean connectsUseCases() {
        for (UMLComponent connectedElement : connectedElements) {
            if (!(connectedElement instanceof UseCase || connectedElement instanceof ExtensionPoint)) {
                return false;
            }
        }
        return true;
    }*/

    @Override
    public String toString() {
        return "Extends{}";
    }
}
