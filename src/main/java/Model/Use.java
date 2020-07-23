/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Use extends Relation{
    public final String id = "Use";
    @Override
    public String toString() {
        return "Use{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
