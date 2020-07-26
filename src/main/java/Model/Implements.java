/**
 * @author rfrank2s
 */
package Model;

public class Implements extends Relation{
    @Override
    public String toString() {
        return "Implements{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.IMPLEMENTS;
    }
}
