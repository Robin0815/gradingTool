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

        //delegate("NennEsSelbstRichtig.uxf");
        //delegate("uml-Olli.uxf");
        delegate("Adapter-UML.uxf");

    }

    public static void delegate(String hi) {
        String file = hi;
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        ClassStrategy classStrategy = new ClassStrategy("Cypher", 1, 1, 0);
        classStrategy.analyzeUML(l);
        System.out.println(FeedbackGenerator.getInstance().getRes());
    }
}

