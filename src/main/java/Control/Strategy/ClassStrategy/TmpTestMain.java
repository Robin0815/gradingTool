package Control.Strategy.ClassStrategy;

import Control.Parser.Parser;
import Control.Strategy.ClassStrategy.Control.GraphDBFunc;
import Model.Elements;
import Model.UMLComponent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TmpTestMain {
    public static void main(String[] args) {

        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        //System.out.print(l.toString());
        List<UMLComponent> lClass = new ArrayList<>();
        List<UMLComponent> lRelation = new ArrayList<>();
        for(int i = 0; i<l.size();i++){
            if (l.get(i).id().equals(Elements.CLASS)) {
                lClass.add(l.get(i));
            }
            if (l.get(i).id().equals(Elements.RELATION)) {
                lRelation.add(l.get(i));
            }

        }
        GraphDBFunc func = GraphDBFunc.getInstance();
        func.test();


    }
}

