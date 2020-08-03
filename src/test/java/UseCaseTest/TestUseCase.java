/*
@author mkowol2s
 */

package UseCaseTest;

import static org.junit.Assert.assertTrue;

import Control.Parser.Parser;
import Control.Strategy.Strategy;
import Control.Strategy.UseCaseStrategy.Control.UseCaseStrategy;
import Model.UMLComponent;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TestUseCase {
    @Test
    public void testWorst(){
        String file = "WorstUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,1);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testOK(){
        String file = "GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,1);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testMultiple(){
        String file = "GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,0.4,0.7,4);
        strategy.analyzeUML(l);
        file = "WorstUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        file = "SimilarUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        file = "DifferentStructureUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testSame(){
        String file = "GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,0.1,0.4,2);
        strategy.analyzeUML(l);
        file = "GoodUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testSimilar(){
        String file = "GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,0.3,0.5,2);
        strategy.analyzeUML(l);
        file = "SimilarUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testDifferentStructure(){
        String file = "GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,0.99,0.99,2);
        strategy.analyzeUML(l);
        file = "DifferentStructureUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testOtherUC(){
        String file = "GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,0.5,0.8,2);
        strategy.analyzeUML(l);
        file = "BahnUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
        assertTrue(true);
    }
}