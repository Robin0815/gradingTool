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
    private Map<ErrorTypes, ErrorWrapper> numberOfAllErrors = new HashMap<>();
    private final SyntaxChecker synchecker = new SyntaxChecker();
    private final ReportGenerator reportGenerator = new ReportGenerator();
    private Boolean checksimilarity = true;
    private double delta = 0.1;
    private Solution solution;

    public UseCaseStrategy(Boolean checksimilarity){
        this.checksimilarity=checksimilarity;
    }

    @Override
    public void analyzeUML(List<UMLComponent> comps){
        //Implemenatation for automatic correction here
        synchecker.prepareForNext();
        numberOfElements.clear();
        //Checking syntax and semantics and counting elements
        checkComponent(comps);
        //Checking if not enough errors (first delta)
        //Generating true or false for passed
        if (checksimilarity) {
            //Checking if solution is set
            if (solution == null) {
                //Set solution
                solution = new Solution();
                solution.setNumberOfElements(numberOfElements);
                solution.setReducedRelationIntegerMap(synchecker.getReducedRelationIntegerMap());
                //System.out.println(solution.toString());
                return;
            }
            //Comparing with solution (second delta)
            //Generating true or false for passed
        }
        //Check if last diagram
        //Generate Report
        reportGenerator.createReportSyntaxErrors(synchecker.getNumberOfAllErrors());
    }

    public void incrementElement(Elements element){
        Integer count = numberOfElements.get(element);
        if (count == null) {
            numberOfElements.put(element, 1);
        } else {
            numberOfElements.put(element, count + 1);
        }
    }

    public void checkComponent(UMLComponent component){
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
            incrementElement(Elements.TOTALELEMTS);
        }
    }

    public void checkComponent(List<UMLComponent>comps){
        for (UMLComponent comp: comps) {
            checkComponent(comp);
        }
        //reducedRelationIntegerMap = synchecker.getReducedRelationIntegerMap();
        //numberOfAllErrors = synchecker.getNumberOfErrors();
        //System.out.println(Collections.singletonList(numberOfAllErrors));
        //System.out.println(Collections.singletonList(numberOfElements));
        //System.out.println(Collections.singletonList(reducedRelationIntegerMap));
    }
}
