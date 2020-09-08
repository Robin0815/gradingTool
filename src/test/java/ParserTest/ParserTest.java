package ParserTest;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Control.Parser.Parser;
import Model.UMLComponent;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ParserTest {
    @Test
    public void test1(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        List<UMLComponent> l2 = a.parseFile(testFile);

        assertTrue(l.size()==l2.size());
    }
    @Test
    public void test2(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        assertFalse(l.isEmpty());
    }
    @Test
    public void test3(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        Parser b = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        List<UMLComponent> l2 = b.parseFile(testFile);

        assertTrue(l.size()==l2.size());
    }
    @Test
    public void test4(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Parser b = new Parser();
        List<UMLComponent> l2 = b.parseFile(testFile);

        assertTrue(l.size()==l2.size());
    }
}
