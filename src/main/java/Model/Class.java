/**
 * @author rfrank2s
 */
package Model;

import java.io.Serializable;
import java.util.List;

public class Class extends ConnectableComponent {
    private String name;
    private String stereotype;
    private List<UMLComponent> elements;

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
    public String toString() {
        return "\n"+"Class{" +
                "name='" + name + '\'' +
                ", stereotype='" + stereotype + '\'' +
                ", elements=" + elements +
                '}'+"\n";
    }

    @Override
    public Elements id() {
        return Elements.CLASS;
    }
}
