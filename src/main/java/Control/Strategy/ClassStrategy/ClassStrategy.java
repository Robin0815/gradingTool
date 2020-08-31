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
    @Override
    public void analyzeUML(List<UMLComponent> comps) {
        SyntaxChecker syntax = new SyntaxChecker();
        PatternChecker pattern = new PatternChecker();
        SimilarityChecker similarity = new SimilarityChecker();
        FeedbackGenerator feedback = FeedbackGenerator.getInstance();
        if (syntax.checkUML(comps)){
            if(pattern.checkUML(comps)){
                if(similarity.checkUML(comps)) {
                    feedback.addRes("\nAlle Tests bestanden\n");
                }else{
                    feedback.addRes("\nNicht alle Tests bestanden\n");
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
