/**
 * @author rfrank2s
 */
package Model;

import Control.Visitor.Visitor;

public class Composition extends Relation{

    @Override
    public String toString() {
        return "Composition{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
