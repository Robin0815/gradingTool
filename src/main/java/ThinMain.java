import Control.Parser.Parser;
import Model.UMLComponent;
import Model.Use;

import java.util.List;

public class ThinMain {

    public static void main(String[] args) {
        //System.out.println("Start");

        String file = "Adapter-UML.uxf";
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(file);
        System.out.println(l.toString());
        /*for (int i = 0; i< l.size();i++){
            UMLComponent tmp = l.get(i);
            if(tmp instanceof Use){
                Use tmp2 = (Use) tmp;
                System.out.println(tmp2.getStart());
            }
        }*/
        /*for(int i = 0; i<l.size();i++){
            UMLComponent com = l.get(i);
            if(com instanceof Class){
                List<UMLComponent> l2 = ((Class) com).getElements();
                for(int j = 0; j<l2.size();j++){
                    System.out.println(l2.get(j).toString());
                }
            }
        }*/
    }
}