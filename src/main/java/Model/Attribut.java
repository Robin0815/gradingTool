/**
 * @author rfrank2s
 */
package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public class Attribut implements UMLComponent{
    private String name;
    private String outputType;
    private String visibility;
    private boolean isStatic;
    public final String id = "Attribut";

    public Attribut(String name, String outputType, String visibility, boolean isStatic) {
        this.name = name;
        this.outputType = outputType;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    @Override
    public String toString() {
        return "Attribut{" +
                "name='" + name + '\'' +
                ", outputType='" + outputType + '\'' +
                ", visibility='" + visibility + '\'' +
                ", isStatic=" + isStatic +
                '}';
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
    public String id() {
        return id;
    }
}
