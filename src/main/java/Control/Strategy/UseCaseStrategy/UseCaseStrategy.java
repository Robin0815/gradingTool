/*
@author mkowol2s
 */

package Control.Strategy.UseCaseStrategy;

import Control.Strategy.Strategy;
import Model.Elements;
import Model.*;

import java.util.*;

public class UseCaseStrategy implements Strategy {

    private static Map<Elements, Integer> numberOfElements = new HashMap<>();
    private SyntaxChecker synchecker = new SyntaxChecker();
    private Boolean checksyntax = true;
    private Boolean checksimilarity = true;

    public UseCaseStrategy(Boolean checksyntax, Boolean checksimilarity){
        this.checksyntax=checksyntax;
        this.checksimilarity=checksimilarity;
    }

    @Override
    public void analyzeUML(List<UMLComponent> comps){
        //Implemenatation for automatic correction here

        //Checking syntax and semantics
        checkComponent(comps);
        //Calculating number of elements
        //Comparing with solution
        checkSimilarity();
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
        if (checksyntax) {
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
            }
        }
        if (checksimilarity){
            incrementElement(component.id());
        }
    }

    public void checkComponent(List<UMLComponent>comps){
        for (UMLComponent comp: comps) {
            checkComponent(comp);
            System.out.println(comp.toString());
        }
        Map<ErrorTypes, ErrorWrapper> tmpmap = synchecker.getNumberOfErrors();
        System.out.println(Collections.singletonList(tmpmap));
        System.out.println(Collections.singletonList(numberOfElements));
    }

    public void checkSimilarity(){
        //Checking similarity with solution
    }
}
