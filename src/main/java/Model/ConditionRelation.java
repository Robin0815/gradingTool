/*
@author mkowol2s
 */

package Model;

public class ConditionRelation extends Relation{
    @Override
    public String toString() {
        return "ConditionRelation{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.CONDITIONRELATION;
    }
}
