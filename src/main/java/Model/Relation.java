/**
 * @author rfrank2s
 */
package Model;

public abstract class Relation implements UMLComponent{
    private final String id = "Relation";
    private ConnectableComponent start = null;
    private ConnectableComponent end = null;

    public ConnectableComponent getStart() {
        return start;
    }

    public void setStart(ConnectableComponent start) {
        this.start = start;
    }

    public ConnectableComponent getEnd() {
        return end;
    }

    public void setEnd(ConnectableComponent end) {
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
