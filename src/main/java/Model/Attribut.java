/**
 * @author rfrank2s
 */
package Model;

import java.io.Serializable;

public class Attribut implements UMLComponent {
    private String name;
    private String outputType;
    private String visibility;
    private boolean isStatic;

    public Attribut(String name, String outputType, String visibility, boolean isStatic) {
        this.name = name;
        this.outputType = outputType;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
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
    public Elements id() {
        return Elements.ATTRIBUT;
    }
}
