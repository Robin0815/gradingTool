/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class UnknownElement implements UMLComponent{
    public final String id = "UnknownElement";

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

    @Override
    public String id() {
        return id;
    }
}
