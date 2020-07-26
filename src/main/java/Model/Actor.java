/*
@author mkowol2s
 */

package Model;

public class Actor implements UMLComponent {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Elements id() {
        return Elements.ACTOR;
    }
}
