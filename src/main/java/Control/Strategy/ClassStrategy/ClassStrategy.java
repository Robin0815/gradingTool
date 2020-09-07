package Control.Strategy.ClassStrategy;

import Control.Strategy.ClassStrategy.Checker.PatternChecker;
import Control.Strategy.ClassStrategy.Checker.SimilarityChecker;
import Control.Strategy.ClassStrategy.Checker.SyntaxChecker;
import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
import Control.Strategy.Strategy;
import Model.UMLComponent;

import java.util.List;

public class ClassStrategy implements Strategy {
    private String result;
    private boolean passed = false;
    private final String mode;
    private final int adap;
    private final int sing;
    private final int stra;

    public ClassStrategy(String mode,int adap, int sing, int stra){
        this.mode = mode;
        this.adap = adap;
        this.sing = sing;
        this.stra = stra;
    }
    @Override
    public void analyzeUML(List<UMLComponent> comps) {
        SyntaxChecker syntax = new SyntaxChecker();
        PatternChecker pattern = new PatternChecker(adap,sing,stra);
        SimilarityChecker similarity = new SimilarityChecker(mode);
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
            }else{
                if(similarity.checkUML(comps)){
                    feedback.addRes("\nPattern Test nicht bestanden, trotzdem gute Ähnlichkeit zu der Musterlösung\n");
                    //passed = true;
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
    public boolean getPassed(){
        return passed;
    }
    public String passed(){
        if(passed){
            return "Bestanden";
        }else{
            return "Nicht Bestanden";
        }
    }
}
