/**
 * @author rfrank2s
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class Relation implements UMLComponent{
    private String type;
    private UMLComponent start;
    private UMLComponent end;

    public Relation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
    public String toString() {
        return "Relation{" +
                "type='" + type + '\'' +
                ", start=" + start +"\n"+
                ", end=" + end +"\n"+
                '}';
    }

    @Override
    public boolean isConnectable() {
        return false;
    }
}
