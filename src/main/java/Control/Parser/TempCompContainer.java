package Control.Parser;

import Model.UMLComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TempCompContainer {
    private final List<TempComp> compPos = new ArrayList<>();
    private static TempCompContainer instance = null;

    private TempCompContainer(){
    }
    public static TempCompContainer getInsance(){
        if (instance == null) {
            synchronized (TempCompContainer.class) {
                if (instance == null) {
                    instance = new TempCompContainer();
                }
            }
        }
        return instance;
    }

    /*public List<TempComp> getCompPos() {
        return compPos;
    }

    public void setCompPos(List<TempComp> compPos) {
        this.compPos = compPos;
    }*/
    public void addComp(TempComp a){
        compPos.add(a);
    }
    public List<UMLComponent> contains(Point a){
        List<UMLComponent> res = new ArrayList<>();
        for (TempComp tmp : compPos) {
            Rectangle rec = tmp.getRec();

            if (a.getX() >= rec.getMinX() & a.getX() <= rec.getMaxX() & a.getY() >= rec.getMinY() & a.getY() <= rec.getMaxY()) {
                res.add(tmp.getComp());
            }
        }
        return res;
    }
    public List<UMLComponent> contains(Rectangle a){
        List<UMLComponent> res = new ArrayList<>();
        for (TempComp tmp : compPos) {
            Rectangle rec = tmp.getRec();

            if (a.contains(rec)) {
                res.add(tmp.getComp());
            }
        }
        return res;
    }
}
