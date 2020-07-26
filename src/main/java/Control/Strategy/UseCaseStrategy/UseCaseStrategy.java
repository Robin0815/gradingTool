/*
@author mkowol2s
 */

package Control.Strategy.UseCaseStrategy;

import Control.Strategy.Strategy;
import Model.Elements;
import Model.*;

import java.util.*;

public class UseCaseStrategy implements Strategy {

    private Map<Elements, Integer> numberOfElements = new HashMap<>();
    private final SyntaxChecker synchecker = new SyntaxChecker();
    private final ComparingEngine comparingEngine = new ComparingEngine();
    private boolean first = true;
    private Boolean checksimilarity = true;
    private double beta = 0.2;
    private double alpha = 0.1;
    private double delta = 0.4;
    private Solution tutorSolution;

    public UseCaseStrategy(Boolean checksimilarity){
        this.checksimilarity=checksimilarity;
    }

    @Override
    public void analyzeUML(List<UMLComponent> comps){
        //Implemenatation for automatic correction here
        synchecker.prepareForNext();
        numberOfElements = new HashMap<>();
        //Checking syntax and semantics and counting elements
        checkComponent(comps);
        //Checking if not enough errors (first delta)
        if (!first && checksimilarity){
            synchecker.createSyntaxFeedback(beta);
        } else {
            first = false;
        }
        //Generating true or false for passed
        if (checksimilarity) {
            Solution solution = new Solution();
            solution.setNumberOfElements((HashMap<Elements, Integer>) numberOfElements);
            solution.setReducedRelationIntegerMap(synchecker.getReducedRelationIntegerMap());
            //Checking if solution is set
            if (tutorSolution == null) {
                //Set solution
                tutorSolution=solution;
                return;
            }
            //Comparing with solution (second delta)
            comparingEngine.compareSolutions(tutorSolution, solution, alpha, delta);
            //Generating true or false for passed
        }
        synchecker.createSyntaxFeedback(beta);
        //Check if last diagram
        //Generate Report
        ReportGenerator.createFeedbackOfResults(synchecker.returnResults());
        if (checksimilarity) {
            ReportGenerator.createFeedbackOfResults(comparingEngine.returnResults());
        }
        ReportGenerator.createReportSyntaxErrors(synchecker.getNumberOfAllErrors());
    }

    private void incrementElement(Elements element){
        Integer count = numberOfElements.get(element);
        if (count == null) {
            numberOfElements.put(element, 1);
        } else {
            numberOfElements.put(element, count + 1);
        }
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
            synchecker.applyRules(component);
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
