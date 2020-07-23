/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Generalization extends Relation{
    @Override
    public String toString() {
        return "Generalization{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Elements id() {
        return Elements.GENERALIZATION;
    }
}
