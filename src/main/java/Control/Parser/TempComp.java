package Control.Parser;

import Model.UMLComponent;

import java.awt.*;

public class TempComp {
    private final UMLComponent comp;
    private final Rectangle rec;

    public TempComp(UMLComponent comp, int x, int y, int w, int h){
        this.comp = comp;
        rec = new Rectangle(x,y,w,h);
    }

    public UMLComponent getComp() {
        return comp;
    }

    /*public void setComp(UMLComponent comp) {
        this.comp = comp;
    }*/

    public Rectangle getRec() {
        return rec;
    }

    /*public void setRec(Rectangle rec) {
        this.rec = rec;
    }*/
}
