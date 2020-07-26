package UseCaseTest;


import static org.junit.Assert.assertTrue;

import Control.Parser.Parser;
import Control.Strategy.Strategy;
import Control.Strategy.UseCaseStrategy.UseCaseStrategy;
import Model.UMLComponent;
import org.junit.Test;

import java.util.List;

public class TestUseCase {
    @Test
    public void testWorst(){
        String file = "WorstUseCase.uxf";
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(file);
        Strategy strategy = new UseCaseStrategy(false);
        strategy.analyzeUML(l);
        assertTrue(true);
    }

    @Test
    public void testOK(){
        String file = "GoodUseCase.uxf";
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(file);
        Strategy strategy = new UseCaseStrategy(false);
        strategy.analyzeUML(l);
        assertTrue(true);
    }
    @Test
    public void testMultiple(){
        String file = "GoodUseCase.uxf";
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(file);
        Strategy strategy = new UseCaseStrategy(true);
        strategy.analyzeUML(l);
        file = "WorstUseCase.uxf";
        l = a.parseFile(file);
        strategy.analyzeUML(l);
        assertTrue(true);
    }
    @Test
    public void testSame(){
        String file = "GoodUseCase.uxf";
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(file);
        Strategy strategy = new UseCaseStrategy(true);
        strategy.analyzeUML(l);
        file = "GoodUseCase.uxf";
        l = a.parseFile(file);
        strategy.analyzeUML(l);
        assertTrue(true);
    }
}