/**
 * @author rfrank2s
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Relation implements UMLComponent{
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
}
