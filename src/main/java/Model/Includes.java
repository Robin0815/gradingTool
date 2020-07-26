/*
@author mkowol2s
 */

package Model;

public class Includes extends Relation{
    @Override
    public String toString() {
        return "Includes{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.INCLUDES;
    }
}
