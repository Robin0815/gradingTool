/*
@author mkowol2s
 */

package Model;

public class Association  extends Relation{
    @Override
    public String toString() {
        return "Association{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.ASSOCIATION;
    }
}
