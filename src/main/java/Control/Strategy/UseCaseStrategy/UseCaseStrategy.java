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

    @Override
    public void analyzeUML(List<UMLComponent> comps){
        //Implemenatation for automatic correction here

        //Checking syntax and semantics
        checkSyntax(comps);
        //Calculating number of elements
        calculateElements(comps);
        //Comparing with solution
    }

    public Map<Elements, Integer> calculateElements(List<UMLComponent> comps){
        // Calculating number of all elements in the diagramm
        Map<Elements, Integer> numberOfElements = new HashMap<Elements, Integer>();
        for (UMLComponent comp: comps) {
            if (comp instanceof UseCase){
                Integer count = numberOfElements.get(Elements.USECASE);
                if (count == null) {
                    numberOfElements.put(Elements.USECASE, 1);
                }
                else {
                    numberOfElements.put(Elements.USECASE, count + 1);
                }
            } else if (comp instanceof Actor){
                Integer count = numberOfElements.get(Elements.ACTOR);
                if (count == null) {
                    numberOfElements.put(Elements.ACTOR, 1);
                }
                else {
                    numberOfElements.put(Elements.ACTOR, count + 1);
                }
            } else if (comp instanceof System){
                Integer count = numberOfElements.get(Elements.SYSTEM);
                if (count == null) {
                    numberOfElements.put(Elements.SYSTEM, 1);
                }
                else {
                    numberOfElements.put(Elements.SYSTEM, count + 1);
                }
            } else if (comp instanceof ExtensionPoint){
                Integer count = numberOfElements.get(Elements.EXTENSIONPOINT);
                if (count == null) {
                    numberOfElements.put(Elements.EXTENSIONPOINT, 1);
                }
                else {
                    numberOfElements.put(Elements.EXTENSIONPOINT, count + 1);
                }
            }

        }


        return numberOfElements;
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
        // shecking Similarity with solution
    }

    public void checkRelation(UMLComponent comp){
        //Checking syntax and semantics of relations

    }

    public void checkEntity(UMLComponent comp){
        //Checking syntax and semantics of use cases and actors

    }
}
