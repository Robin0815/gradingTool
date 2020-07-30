/**
 * @author rfrank2s
 */
package Model;

public class Association2 extends Relation{
    @Override
    public String toString() {
        return "Association{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.ASSOCIATION2;
    }
}
