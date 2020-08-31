package Control.Strategy.ClassStrategy;

import Control.Parser.Parser;
import Control.Strategy.ClassStrategy.Checker.PatternChecker;
import Control.Strategy.ClassStrategy.Checker.SyntaxChecker;
import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
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
        ClassStrategy classStrategy = new ClassStrategy();
        classStrategy.analyzeUML(l);
        System.out.println(FeedbackGenerator.getInstance().getRes());



    }
}

