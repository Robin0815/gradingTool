/*
@author mkowol2s
 */

package Control.Strategy.UseCaseStrategy;

import Control.Strategy.Strategy;
import Model.*;
import Model.System;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseCaseStrategy implements Strategy {

    private static Map<String, Integer> synerrors = new HashMap<String, Integer>();
    private static Map<Elements, Integer> numberOfElements = new HashMap<Elements, Integer>();

    @Override
    public void analyzeUML(List<UMLComponent> comps){
        //Implemenatation for automatic correction here

        //Checking syntax and semantics
        checkSyntax(comps);
        //Calculating number of elements
        calculateElements(comps);
        //Comparing with solution
        checkSimilarity();
    }

    public void calculateElements(List<UMLComponent> comps){
        // Calculating number of all elements in the diagramm
        for (UMLComponent comp: comps) {
            if (comp instanceof UseCase){
                incrementElement(Elements.USECASE);
            } else if (comp instanceof Actor){
                incrementElement(Elements.ACTOR);
            } else if (comp instanceof System){
                incrementElement(Elements.SYSTEM);
            } else if (comp instanceof ExtensionPoint){
                incrementElement(Elements.EXTENSIONPOINT);
            } else if (comp instanceof Note){
                incrementElement(Elements.NOTE);
            } else if (comp instanceof Association){
                incrementElement(Elements.ASSOCIATION);
            } else if (comp instanceof Extends){
                incrementElement(Elements.EXTENDS);
            } else if (comp instanceof Includes){
                incrementElement(Elements.INCLUDES);
            } else if (comp instanceof ConditionRelation){
                incrementElement(Elements.CONDITIONRELATION);
            } else if (comp instanceof Generalization){
                incrementElement(Elements.GENERALIZATION);
            } else {
                incrementElement(Elements.UNKNOWNELEMENT);
            }
        }
    }

    public void incrementElement(Elements element){
        Integer count = numberOfElements.get(element);
        if (count == null) {
            numberOfElements.put(element, 1);
        }
        else {
            numberOfElements.put(element, count + 1);
        }
    }

    public void checkSyntax(List<UMLComponent>comps){
        for (UMLComponent comp: comps) {
            if (comp instanceof Relation){
                checkRelation(comp);
            } else {
                checkEntity(comp);
            }
        }
    }

    public void checkSimilarity(){
        //Checking Similarity with solution
    }

    public void checkRelation(UMLComponent comp){
        //Checking syntax and semantics of relations

    }

    public void checkEntity(UMLComponent comp){
        //Checking syntax and semantics of use cases and actors

    }
}
