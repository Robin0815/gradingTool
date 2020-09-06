package ParserTest;


import static org.junit.Assert.assertTrue;

import Control.Parser.Parser;
import Model.UMLComponent;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ParserTest1 {
    @Test
    public void test1(){
        String file = "Adapter-UML.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        List<UMLComponent> l2 = a.parseFile(testFile);

        assertTrue(l.size()==l2.size());


    }
}
