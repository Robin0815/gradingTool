/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Association  extends Relation{
    public final String id = "Association";

    @Override
    public String toString() {
        return "Association{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String id() {
        return id;
    }
}
