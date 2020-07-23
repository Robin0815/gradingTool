/*
@author mkowol2s
 */

package Model;

import Control.Visitor.Visitor;

public class Association  extends Relation{

    @Override
    public String toString() {
        return "Association{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
