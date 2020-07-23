/**
 * @author rfrank2s
 */
package Model;

import Control.Visitor.Visitor;

public class Use extends Relation{
    @Override
    public String toString() {
        return "Use{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
