package TestClass;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import Control.Parser.Parser;
import Control.Strategy.ClassStrategy.ClassStrategy;
import Model.UMLComponent;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestClass {


    @Test
    public void testClassGood(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        ClassStrategy classStrategy = new ClassStrategy("", 1,1,0);
        classStrategy.analyzeUML(l);
        //System.out.println(classStrategy.getResult() + Boolean.parseBoolean(classStrategy.passed()));
        assertTrue(classStrategy.getPassed());
    }
    @Test
    public void testClassBad(){
        String file = "All-Elements.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        ClassStrategy classStrategy = new ClassStrategy("", 0,0,0);
        classStrategy.analyzeUML(l);
        //System.out.println(classStrategy.getResult() + Boolean.parseBoolean(classStrategy.passed()));
        assertFalse(classStrategy.getPassed());
    }
    @Test
    public void testClassCompare(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        ClassStrategy classStrategy = new ClassStrategy("Compare", 1,1,0);
        classStrategy.analyzeUML(l);
        assertTrue(classStrategy.getPassed());
    }
    @Test
    public void testClassCypher(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        ClassStrategy classStrategy = new ClassStrategy("Cypher", 1,1,0);
        classStrategy.analyzeUML(l);
        assertTrue(classStrategy.getPassed());
    }
}
