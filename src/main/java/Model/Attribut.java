package Model;

public class Attribut implements UMLComponent{
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

    @Override
    public String toString() {
        return "Attribut{" +
                "name='" + name + '\'' +
                ", outputType='" + outputType + '\'' +
                ", visibility='" + visibility + '\'' +
                ", isStatic=" + isStatic +
                '}';
    }
}
