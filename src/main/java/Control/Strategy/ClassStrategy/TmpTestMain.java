package Control.Strategy.ClassStrategy;

import Control.Parser.Parser;
import Control.Strategy.ClassStrategy.Checker.PatternChecker;
import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
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

        GraphDBFunction func = GraphDBFunction.getInstance();
        func.setUp(l);
        System.out.println(func.toString());

        PatternChecker p = new PatternChecker();
        p.checkUML(l);




    }
}

