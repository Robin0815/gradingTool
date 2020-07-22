/*
@author mkowol2s
 */

package Model;

public class UseCase implements UMLComponent {
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
        return "UseCase{" +
                "name='" + name + '\'' +
                '}';
    }
}
