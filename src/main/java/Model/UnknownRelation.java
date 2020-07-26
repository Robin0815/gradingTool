package Model;

public class UnknownRelation extends Relation{
    @Override
    public String toString() {
        return "UnknownRelation{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.UNKNOWNRELATION;
    }
}
