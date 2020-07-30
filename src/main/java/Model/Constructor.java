/**
 * @author rfrank2s
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class Constructor implements UMLComponent{
    private List<String> inputType = new ArrayList<>();
    private String visibility;

    public Constructor(String visibility, List<String> inputType) {
        this.inputType = inputType;
        this.visibility = visibility;
    }

    public List<String> getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType.add(inputType);
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visability) {
        this.visibility = visability;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "inputType=" + inputType +
                ", visibility='" + visibility + '\'' +
                '}';
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public Elements id() {
        return Elements.CONSTRUCTOR;
    }
}
