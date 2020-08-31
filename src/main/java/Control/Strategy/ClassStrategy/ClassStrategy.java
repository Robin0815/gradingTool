package Control.Strategy.ClassStrategy;

import Control.Strategy.ClassStrategy.Checker.Checker;
import Control.Strategy.ClassStrategy.Checker.PatternChecker;
import Control.Strategy.ClassStrategy.Checker.SimilarityChecker;
import Control.Strategy.ClassStrategy.Checker.SyntaxChecker;
import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Control.Strategy.Strategy;
import Model.UMLComponent;

import java.awt.*;
import java.util.List;

public class ClassStrategy implements Strategy {
    private String result;
    private boolean passed = false;
    @Override
    public void analyzeUML(List<UMLComponent> comps) {
        SyntaxChecker syntax = new SyntaxChecker();
        PatternChecker pattern = new PatternChecker(1,0,0);
        SimilarityChecker similarity = new SimilarityChecker("Compare");
        FeedbackGenerator feedback = FeedbackGenerator.getInstance();
        feedback.reset();
        if (syntax.checkUML(comps)){
            if(pattern.checkUML(comps)){
                if(similarity.checkUML(comps)) {
                    feedback.addRes("\nAlle Tests bestanden\n");
                    passed = true;
                }else{
                    feedback.addRes("\nNicht bestanden\n");
                }
            }
        }

        result = feedback.getRes();
        //System.out.println(result);

    }
    public String getResult(){
        return result;
    }
}
