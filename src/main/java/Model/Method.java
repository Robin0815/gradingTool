/**
 * @author rfrank2s
 */
package Model;

import java.util.List;

public class Method implements UMLComponent{
    private String name;
    private List<String> inputType;
    private String outputType;
    private boolean isStatic;
    private String visibility;

    public Method(String name, List<String> inputType, String outputType, boolean isStatic, String visibility) {
        this.name = name;
        this.inputType = inputType;
        this.outputType = outputType;
        this.isStatic = isStatic;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType.add(inputType);
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getVisability() {
        return visibility;
    }

    public void setVisability(String visability) {
        this.visibility = visability;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", inputType=" + inputType +
                ", outputType='" + outputType + '\'' +
                ", isStatic=" + isStatic +
                ", visibility='" + visibility + '\'' +
                '}';
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public Elements id() {
        return Elements.METHOD;
    }
}
