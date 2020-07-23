/**
 * @author rfrank2s
 */
package Model;

import Control.Visitor.Visitor;

public class Aggregation extends Relation{
    public final String id = "Aggregation";
    @Override
    public String toString() {
        return "Aggregation{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
