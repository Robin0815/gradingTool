/**
 * @author rfrank2s
 */
package Model;

public abstract class Relation implements UMLComponent{
    private final String id = "Relation";
    private UMLComponent start;
    private UMLComponent end;

    public UMLComponent getStart() {
        return start;
    }

    public void setStart(UMLComponent start) {
        this.start = start;
    }

    public UMLComponent getEnd() {
        return end;
    }

    public void setEnd(UMLComponent end) {
        this.end = end;
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String toString() {
        return "\n"+"Relation{" +
                "start=" + start +
                ", end=" + end +
                '}'+"\n";
    }

    @Override
    public Elements id() {
        return Elements.RELATION;
    }
}
