package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class NonHumanActor implements UMLComponent{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public String toString() {
        return "NonHumanActor{" +
                "name='" + name + '\'' +
                '}';
    }
}
