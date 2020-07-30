package Control.Strategy.ClassStrategy.Control;

import Model.Attribut;
import Model.Class;
import Model.Method;
import Model.UMLComponent;

import java.util.Comparator;

public class UMLComparator implements Comparator<UMLComponent> {


    @Override
    public int compare(UMLComponent o1, UMLComponent o2) {
        if(o1 instanceof Class & o2 instanceof Class){

        }
        if(o1 instanceof Method & o2 instanceof Method){

        }
        if(o1 instanceof Attribut & o2 instanceof Attribut){

        }
        if(o1.id().equals(o2.id())){
            return 0;
        }
        else return -1;
    }
}
