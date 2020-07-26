/*
@author mkowol2s
 */

package Model;

public class Generalization extends Relation{
    @Override
    public String toString() {
        return "Generalization{ "+super.toString()+" }";
    }

    @Override
    public Elements id() {
        return Elements.GENERALIZATION;
    }
}
