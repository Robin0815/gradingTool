/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Composition extends Relation{
    public final String id = "Composition";
    @Override
    public String toString() {
        return "Composition{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
