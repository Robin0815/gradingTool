/*
@author mkowol2s
 */

package Model;

import Control.Visitor.Visitor;

public class UnknownElement implements UMLComponent{

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "UnknownElement{}";
    }
}
