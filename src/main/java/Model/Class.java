/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

import java.util.List;

public class Class implements UMLComponent{
    private String name;
    private String stereotype;
    private List<UMLComponent> elements;
    public final String id = "Class";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStereotype() {
        return stereotype;
    }

    public void setStereotype(String stereotype) {
        this.stereotype = stereotype;
    }
    public void addElements(UMLComponent a){
        elements.add(a);
    }

    public List<UMLComponent> getElements() {
        return elements;
    }

    public void setElements(List<UMLComponent> elements) {
        this.elements = elements;
    }

    public Class(String name, String stereotype) {
        this.name = name;
        this.stereotype = stereotype;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", stereotype='" + stereotype + '\'' +
                ", elements=" + elements +
                '}';
    }
}
