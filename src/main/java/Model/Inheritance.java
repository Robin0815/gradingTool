/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Inheritance extends Relation{
    public final String id = "Inheritance";
    @Override
    public String toString() {
        return "Inheritance{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
