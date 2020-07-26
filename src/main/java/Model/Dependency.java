/**
 * @author rfrank2s
 */
package Model;

public class Dependency extends Relation{
    @Override
    public String toString() {
        return "Dependency{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.DEPENDENCY;
    }
}
