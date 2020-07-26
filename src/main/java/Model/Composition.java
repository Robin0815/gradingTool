/**
 * @author rfrank2s
 */
package Model;

public class Composition extends Relation{
    @Override
    public String toString() {
        return "Composition{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.COMPOSITION;
    }
}
