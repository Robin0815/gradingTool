/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Aggregation extends Relation{
    @Override
    public String toString() {
        return "Aggregation{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Elements id() {
        return Elements.AGGREGATION;
    }
}
