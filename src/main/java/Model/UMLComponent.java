/*
@author mkowol2s
 */

package Model;

import Control.Visitor.Visitor;

public interface UMLComponent {
    boolean isConnectable();
    void accept(Visitor visitor);
}
