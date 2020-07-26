/**
 * @author rfrank2s
 */
package Model;

public class Inheritance extends Relation{
    @Override
    public String toString() {
        return "Inheritance{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.INHERITANCE;
    }
}
