/*
@author mkowol2s
 */

package Control.Strategy.UseCaseStrategy.Control;

import Control.Strategy.Strategy;
import Control.Strategy.UseCaseStrategy.DTO.Solution;
import Model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseCaseStrategy implements Strategy {

    private Map<Elements, Integer> numberOfElements = new HashMap<>();
    private final SyntaxChecker synchecker = new SyntaxChecker();
    private final SimilarityChecker similarityChecker = new SimilarityChecker();
    private boolean first = true;
    private final boolean checksimilarity;
    private double beta = 0.2;
    private double alpha = 0.1;
    private double delta = 0.4;
    private int numberOfSubmissions = 0;
    private Solution tutorSolution;
    private String report = "";

    public UseCaseStrategy(double beta, int numberOfSubmissions){
        this.beta = beta;
        this.numberOfSubmissions= numberOfSubmissions;
        this.checksimilarity = false;
    }

    public UseCaseStrategy(double beta, double alpha, double delta, int numberOfSubmissions){
        this.beta = beta;
        this.alpha = alpha;
        this.delta = delta;
        this.numberOfSubmissions= numberOfSubmissions;
        this.checksimilarity = true;
    }

    public String getReport(){
        return report;
    }

    @Override
    public void analyzeUML(List<UMLComponent> comps){
        boolean passedsyn = true;
        this.numberOfSubmissions--;
        //Implemenatation for automatic correction here
        synchecker.prepareForNext();
        numberOfElements = new HashMap<>();
        //Checking syntax and semantics and counting elements
        checkComponent(comps);
        //Checking if not enough errors (first delta)
        if(!(first && checksimilarity)){
            passedsyn = synchecker.createSyntaxFeedback(beta);
        }
        this.first = false;
        //Generating true or false for passed
        if (checksimilarity && passedsyn) {
            Solution solution = new Solution();
            solution.setNumberOfElements((HashMap<Elements, Integer>) numberOfElements);
            solution.setReducedRelationIntegerMap(synchecker.getReducedRelationIntegerMap());
            //Checking if solution is set
            if (tutorSolution == null) {
                //Set solution
                tutorSolution = solution;
                return;
            }
            //Comparing with solution (second delta)
            similarityChecker.compareSolutions(tutorSolution, solution, alpha, delta);
            //Generating true or false for passed
        }
        //Check if last diagram
        if(this.numberOfSubmissions == 0) {
            //Generate Report
            report = ReportGenerator.createFeedbackOfResults(synchecker.returnResults());
            if (checksimilarity) {
                report += ReportGenerator.createFeedbackOfResults(similarityChecker.returnResults());
            }
            report += ReportGenerator.createReportSyntaxErrors(synchecker.getNumberOfAllErrors());
        }
    }

    private void incrementElement(Elements element){
        numberOfElements.merge(element, 1, Integer::sum);
    }

    private void checkComponent(UMLComponent component){
        if (component.id() == Elements.USECASE) {
            synchecker.applyRules((UseCase) component);
        } else if (component.id() == Elements.ACTOR) {
            synchecker.applyRules((Actor) component);
        } else if (component.id() == Elements.NONHUMANACTOR) {
            synchecker.applyRules((NonHumanActor) component);
        } else if (component.id() == Elements.UMLSYSTEM) {
            synchecker.applyRules((UMLSystem) component);
        } else if (component.id() == Elements.EXTENSIONPOINT) {
            synchecker.applyRules((ExtensionPoint) component);
        } else if (component.id() == Elements.NOTE) {
            synchecker.applyRules((Note) component);
        } else if (component.id() == Elements.ASSOCIATION) {
            synchecker.applyRules((Association) component);
        } else if (component.id() == Elements.GENERALIZATION) {
            synchecker.applyRules((Generalization) component);
        } else if (component.id() == Elements.EXTENDS) {
            synchecker.applyRules((Extends) component);
        } else if (component.id() == Elements.INCLUDES) {
            synchecker.applyRules((Includes) component);
        } else if (component.id() == Elements.CONDITIONRELATION) {
            synchecker.applyRules((ConditionRelation) component);
        } else {
            synchecker.applyRules();
        }
        if (checksimilarity){
            incrementElement(component.id());
        }
    }

    private void checkComponent(List<UMLComponent>comps){
        for (UMLComponent comp: comps) {
            checkComponent(comp);
        }
    }
}
