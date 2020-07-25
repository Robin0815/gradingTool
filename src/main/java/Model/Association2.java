/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Association2 extends Relation{
    @Override
    public String toString() {
        return "Use{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Elements id() {
        return Elements.USE;
    }
}
