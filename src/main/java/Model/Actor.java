/*
@author Michael Kowollik
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
}
