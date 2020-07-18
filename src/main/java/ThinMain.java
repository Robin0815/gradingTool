import Control.IdType;
import Control.Parser;
import Model.UMLComponent;

import java.util.List;

public class ThinMain {

    public static void main(String[] args) {
        //System.out.println("Start");

        String file = "Adapter-UML.uxf";
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(file);
        System.out.println(l.toString());
    }
}