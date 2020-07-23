/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

import java.util.List;

public class Diagram implements UMLComponent{
    private String name;
    private List<UMLComponent> comp;

    public Diagram(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UMLComponent> getComp() {
        return comp;
    }

    public void setComp(List<UMLComponent> comp) {
        this.comp = comp;
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Elements id() {
        return Elements.DIAGRAM;
    }
}
