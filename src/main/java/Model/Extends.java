/*
@author mkowol2s
 */

package Model;

public class Extends extends Relation{
    @Override
    public String toString() {
        return "Extends{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.EXTENDS;
    }
}
