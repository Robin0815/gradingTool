/*
@author mkowol2s
 */

package Model;

import Control.Visitor.Visitor;

public class Extends extends Relation{

    @Override
    public String toString() {
        return "Extends{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
