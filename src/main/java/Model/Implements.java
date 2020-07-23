/**
 * @author rfrank2s
 */
package Model;

import Control.Visitor.Visitor;

public class Implements extends Relation{
    public final String id = "Implements";
    @Override
    public String toString() {
        return "Implements{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
