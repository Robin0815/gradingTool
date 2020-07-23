/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Dependency extends Relation{
    public final String id = "Dependency";
    @Override
    public String toString() {
        return "Dependency{ "+super.toString()+" }";
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
