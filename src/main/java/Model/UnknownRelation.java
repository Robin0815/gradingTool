package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class UnknownRelation extends Relation{
    public final String id = "UnknownRelation";

    @Override
    public String toString() {
        return "UnknownRelation{ "+super.toString()+" }";
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
