package Control.Strategy.ClassStrategy;

import Control.Parser.Parser;
import Model.UMLComponent;

import java.io.File;
import java.util.List;

public class TmpTestMain {
    public static void main(String[] args) {
        //System.out.println("Start");

        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        System.out.print(l.toString());
        //System.out.println(l.toString());
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
        //Strategy strategy = new UseCaseStrategy(0.2,1);
        //strategy.analyzeUML(l);
    }
}
