package Model;

public class NonHumanActor extends ConnectableComponent{
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
    public String toString() {
        return "NonHumanActor{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Elements id() {
        return Elements.NONHUMANACTOR;
    }
}
