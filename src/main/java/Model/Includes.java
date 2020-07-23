/*
@author mkowol2s
 */

package Model;

import Control.Visitor.Visitor;

public class Includes extends Relation{

    @Override
    public String toString() {
        return "Includes{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
