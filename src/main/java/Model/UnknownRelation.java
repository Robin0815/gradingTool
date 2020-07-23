package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class UnknownRelation extends Relation{
    @Override
    public String toString() {
        return "UnknownRelation{ "+super.toString()+" }";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
