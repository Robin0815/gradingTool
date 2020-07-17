package Control;

import Model.UMLComponent;
import java.awt.Rectangle;

public class TempComp {
    private UMLComponent comp;
    private Rectangle rec;

    public TempComp(UMLComponent comp, int x, int y, int w, int h){
        this.comp = comp;
        rec = new Rectangle(x,y,w,h);
    }

}
