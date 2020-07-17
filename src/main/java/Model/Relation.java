package Model;

import java.util.ArrayList;
import java.util.List;

public class Relation implements UMLComponent{
    private String type;
    private List<UMLComponent> list = new ArrayList<>();
    private String info;

    public Relation(String type, List<UMLComponent> list, String info) {
        this.type = type;
        this.list = list;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "type='" + type + '\'' +
                ", list=" + list +
                ", info='" + info + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<UMLComponent> getList() {
        return list;
    }

    public void setList(List<UMLComponent> list) {
        this.list = list;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
