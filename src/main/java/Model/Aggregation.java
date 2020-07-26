/**
 * @author rfrank2s
 */
package Model;

public class Aggregation extends Relation{
    @Override
    public String toString() {
        return "Aggregation{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.AGGREGATION;
    }
}
