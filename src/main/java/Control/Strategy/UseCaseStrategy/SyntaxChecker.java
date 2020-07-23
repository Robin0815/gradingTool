package Control.Strategy.UseCaseStrategy;

import Model.*;

import java.util.HashMap;
import java.util.Map;

public class SyntaxChecker {
    private Map<ErrorTypes, ErrorWrapper> numberOfErrors = new HashMap<>();

    public Map<ErrorTypes, ErrorWrapper> getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(Map<ErrorTypes, ErrorWrapper> numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    public void incrementFails(ErrorTypes errorType){
        ErrorWrapper count = numberOfErrors.get(errorType);
        if (count == null) {
            ErrorWrapper newcount = new ErrorWrapper();
            newcount.setFails(1);
            newcount.setSucesses(0);
            numberOfErrors.put(errorType,newcount);
        } else {
            count.setFails(count.getFails()+1);
            numberOfErrors.put(errorType,count);
        }
    }

    public void incrementSucesses(ErrorTypes errorType){
        ErrorWrapper count = numberOfErrors.get(errorType);
        if (count == null) {
            ErrorWrapper newcount = new ErrorWrapper();
            newcount.setFails(0);
            newcount.setSucesses(1);
            numberOfErrors.put(errorType,newcount);
        } else {
            count.setSucesses(count.getSucesses()+1);
            numberOfErrors.put(errorType,count);
        }
    }

    public void checkSyntax(UMLComponent component){
        if (component.id() == Elements.USECASE){
            applyRules((UseCase) component);
        } else if (component.id() == Elements.ACTOR){
            applyRules((Actor) component);
        } else if (component.id() == Elements.NONHUMANACTOR){
            applyRules((NonHumanActor) component);
        } else if (component.id() == Elements.UMLSYSTEM){
            applyRules((UMLSystem) component);
        } else if (component.id() == Elements.EXTENSIONPOINT){
            applyRules((ExtensionPoint) component);
        } else if (component.id() == Elements.NOTE){
            applyRules((Note) component);
        } else if (component.id() == Elements.ASSOCIATION){
            applyRules((Association) component);
        } else if (component.id() == Elements.GENERALIZATION){
            applyRules((Generalization) component);
        } else if (component.id() == Elements.EXTENDS){
            applyRules((Extends) component);
        } else if (component.id() == Elements.INCLUDES){
            applyRules((Includes) component);
        }
    }

    private void applyRules(UseCase useCase){
        //Check if name is set
        if (useCase.getName() == ""){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.USECASENAME);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.USECASENAME);
        }
    }

    private void applyRules(Actor actor){
        //Check if name is set
        if (actor.getName() == ""){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.ACTORNAME);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.ACTORNAME);
        }
    }

    private void applyRules(NonHumanActor nonHumanActor){
        //Check if name is set
        if (nonHumanActor.getName() == ""){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.ACTORNAME);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.ACTORNAME);
        }
    }

    private void applyRules(UMLSystem umlSystem){
        //Check if name is set
        if (umlSystem.getName() == ""){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.SYSTEMNAME);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.SYSTEMNAME);
        }
        //Check if no Actors are contained
        for (UMLComponent comp : umlSystem.getContainedElements()) {
            System.out.println(comp.toString());
            if (comp.id() == Elements.ACTOR || comp.id() == Elements.NONHUMANACTOR) {
                incrementFails(ErrorTypes.ACTORSCONTAINED);
            } else {
                incrementSucesses(ErrorTypes.ACTORSCONTAINED);
            }
        }
    }

    private void applyRules(ExtensionPoint extensionPoint){
        //Check if name is set
        if (extensionPoint.getName() == ""){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.USECASENAME);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.USECASENAME);
        }
        //Check if at least one extension point is defined
        if (extensionPoint.getExtpoints().isEmpty()){
            incrementFails(ErrorTypes.EMPTYEXTENSIONPOINT);
        } else {
            incrementSucesses(ErrorTypes.EMPTYEXTENSIONPOINT);
        }
    }

    private void applyRules(Note note){
        //Check if note is not empty
        if (note.getText() == ""){
            incrementFails(ErrorTypes.EMPTYNOTE);
        } else {
            incrementSucesses(ErrorTypes.EMPTYNOTE);
        }
    }

    private void applyRules(Association association){
        //Check if relation is binary
        if (association.getStart() != null && association.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYASSOCIATION);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYASSOCIATION);
            return;
        }
        //Check if relation only connects use cases and actors
        if (((association.getStart().id() == Elements.USECASE || association.getStart().id() == Elements.EXTENSIONPOINT) && (association.getEnd().id() == Elements.ACTOR || association.getEnd().id() == Elements.NONHUMANACTOR)) || ((association.getStart().id() == Elements.ACTOR || association.getStart().id() == Elements.NONHUMANACTOR) && (association.getEnd().id() == Elements.USECASE || association.getEnd().id() == Elements.EXTENSIONPOINT))){
            incrementSucesses(ErrorTypes.NOTSAMETYPEASSOCIATION);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEASSOCIATION);
        }
    }

    private void applyRules(Generalization generalization){
        //Check if relation is binary
        if (generalization.getStart() != null && generalization.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYGENERALIZATION);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYGENERALIZATION);
            return;
        }
        //Check if relation either connects use cases and actors
        if (((generalization.getStart().id() == Elements.USECASE || generalization.getStart().id() == Elements.EXTENSIONPOINT) && (generalization.getEnd().id() == Elements.USECASE || generalization.getEnd().id() == Elements.EXTENSIONPOINT)) || ((generalization.getStart().id() == Elements.ACTOR || generalization.getStart().id() == Elements.NONHUMANACTOR) && (generalization.getEnd().id() == Elements.ACTOR || generalization.getEnd().id() == Elements.NONHUMANACTOR))){
            incrementSucesses(ErrorTypes.NOTSAMETYPEGENERALIZATION);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEGENERALIZATION);
        }
    }

    private void applyRules(Includes includes){
        //Check if relation is binary
        if (includes.getStart() != null && includes.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYINCLUDES);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYINCLUDES);
            return;
        }
        //Check if relation only connects use cases or extension points
        if ((includes.getStart().id() == Elements.USECASE || includes.getStart().id() == Elements.EXTENSIONPOINT) && (includes.getEnd().id() == Elements.USECASE || includes.getEnd().id() == Elements.EXTENSIONPOINT)){
            incrementSucesses(ErrorTypes.NOTSAMETYPEINCLUDES);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEINCLUDES);
        }
    }

    private void applyRules(Extends extend){
        //Check if relation is binary
        if (extend.getStart() != null && extend.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYEXTENDS);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYEXTENDS);
            return;
        }
        //Check if relation only connects use cases or extension points
        if ((extend.getStart().id() == Elements.USECASE || extend.getStart().id() == Elements.EXTENSIONPOINT) && (extend.getEnd().id() == Elements.USECASE || extend.getEnd().id() == Elements.EXTENSIONPOINT)){
            incrementSucesses(ErrorTypes.NOTSAMETYPEEXTENDS);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEEXTENDS);
        }
    }

    private void applyRules(ConditionRelation conditionRelation){
        //Check if relation is NOT binary
        if ((conditionRelation.getStart() == null && conditionRelation.getEnd() != null) || (conditionRelation.getStart() != null && conditionRelation.getEnd() == null)){
            incrementSucesses(ErrorTypes.BINARYCONDITIONRELATION);
        } else {
            incrementFails(ErrorTypes.BINARYCONDITIONRELATION);
            return;
        }
        //Check if relation only connects to note
        if (conditionRelation.getStart().id()== Elements.NOTE || conditionRelation.getEnd().id()== Elements.NOTE ){
            incrementSucesses(ErrorTypes.CONDITIONRELATIONNONOTE);
        } else {
            incrementFails(ErrorTypes.CONDITIONRELATIONNONOTE);
        }
    }
}
