package Model;

public class UnknownElement implements UMLComponent{

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String toString() {
        return "UnknownElement{}";
    }
}
