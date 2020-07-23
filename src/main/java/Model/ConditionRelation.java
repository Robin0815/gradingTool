/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class ConditionRelation extends Relation{

    @Override
    public String toString() {
        return "ConditionRelation{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
