package Control.Strategy.ClassStrategy;

import Control.Strategy.ClassStrategy.Checker.PatternChecker;
import Control.Strategy.ClassStrategy.Checker.SimilarityChecker;
import Control.Strategy.ClassStrategy.Checker.SyntaxChecker;
import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Control.Strategy.Strategy;
import Model.UMLComponent;

import java.util.List;

public class ClassStrategy implements Strategy {
    private String result;
    @Override
    public void analyzeUML(List<UMLComponent> comps) {
        SyntaxChecker syntax = new SyntaxChecker();
        PatternChecker pattern = new PatternChecker();
        SimilarityChecker similarity = new SimilarityChecker();
        FeedbackGenerator feedback = FeedbackGenerator.getInstance();

        syntax.checkUML(comps);
        pattern.checkUML(comps);
        similarity.checkUML(comps);

        result = feedback.getRes();

    }
    public String getResult(){
        return result;
    }
}
