/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class UseCase implements UMLComponent {
    public final String id = "UseCase";
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "UseCase{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String id() {
        return id;
    }
}
