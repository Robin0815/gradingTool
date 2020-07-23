/**
 * @author rfrank2s
 */
package Model;

import Control.Visitor.Visitor;

public class Inheritance extends Relation{
    @Override
    public String toString() {
        return "Inheritance{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
