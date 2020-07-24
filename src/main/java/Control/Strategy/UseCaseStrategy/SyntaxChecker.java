package Control.Strategy.UseCaseStrategy;

import Model.*;

import java.util.HashMap;
import java.util.Map;

public class SyntaxChecker {
    private Map<ErrorTypes, ErrorWrapper> numberOfErrors = new HashMap<>();
    private Map<ErrorTypes, ErrorWrapper> numberOfAllErrors = new HashMap<>();

    public Map<ErrorTypes, ErrorWrapper> getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(Map<ErrorTypes, ErrorWrapper> numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    public Map<ErrorTypes, ErrorWrapper> getNumberOfAllErrors() {
        return numberOfAllErrors;
    }

    public void setNumberOfAllErrors(Map<ErrorTypes, ErrorWrapper> numberOfAllErrors) {
        this.numberOfAllErrors = numberOfAllErrors;
    }

    public void prepareForNext(){
        numberOfErrors.clear();
    }

    private void incrementFails(ErrorTypes errorType){
        incrementFails(errorType,numberOfErrors);
        incrementFails(errorType,numberOfAllErrors);

    }

    private void incrementSucesses(ErrorTypes errorType){
        incrementSucesses(errorType,numberOfErrors);
        incrementSucesses(errorType,numberOfAllErrors);

    }

    private void incrementFails(ErrorTypes errorType, Map<ErrorTypes, ErrorWrapper> tmpmap){
        ErrorWrapper count = tmpmap.get(errorType);
        if (count == null) {
            ErrorWrapper newcount = new ErrorWrapper();
            newcount.setFails(1);
            newcount.setSucesses(0);
            tmpmap.put(errorType,newcount);
        } else {
            count.setFails(count.getFails()+1);
            tmpmap.put(errorType,count);
        }
    }

    private void incrementSucesses(ErrorTypes errorType, Map<ErrorTypes, ErrorWrapper> tmpmap){
        ErrorWrapper count = tmpmap.get(errorType);
        if (count == null) {
            ErrorWrapper newcount = new ErrorWrapper();
            newcount.setFails(0);
            newcount.setSucesses(1);
            tmpmap.put(errorType,newcount);
        } else {
            count.setSucesses(count.getSucesses()+1);
            tmpmap.put(errorType,count);
        }
    }

    public void applyRules(UseCase useCase){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if name is set
        if (useCase.getName().equals("")){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.USECASENAME);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.USECASENAME);
            incrementSucesses(ErrorTypes.TOTALERRORS);

        }
    }

    public void applyRules(Actor actor){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if name is set
        if (actor.getName().equals("")){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.ACTORNAME);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.ACTORNAME);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(NonHumanActor nonHumanActor){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if name is set
        if (nonHumanActor.getName().equals("")){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.ACTORNAME);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.ACTORNAME);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(UMLSystem umlSystem){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if name is set
        if (umlSystem.getName().equals("")){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.SYSTEMNAME);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.SYSTEMNAME);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        }
        //Check if no Actors are contained
        for (UMLComponent comp : umlSystem.getContainedElements()) {
            if (comp.id() == Elements.ACTOR || comp.id() == Elements.NONHUMANACTOR) {
                incrementFails(ErrorTypes.ACTORSCONTAINED);
                incrementFails(ErrorTypes.TOTALERRORS);
            } else {
                //incrementSucesses(ErrorTypes.ACTORSCONTAINED);
            }
        }
    }

    public void applyRules(ExtensionPoint extensionPoint){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if name is set
        if (extensionPoint.getName().equals("")){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.USECASENAME);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.ENTITYNAME);
            incrementSucesses(ErrorTypes.USECASENAME);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        }
        //Check if at least one extension point is defined
        if (extensionPoint.getExtpoints().isEmpty()){
            incrementFails(ErrorTypes.EMPTYEXTENSIONPOINT);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.EMPTYEXTENSIONPOINT);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(Note note){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if note is not empty
        if (note.getText().equals("")){
            incrementFails(ErrorTypes.EMPTYNOTE);
            incrementFails(ErrorTypes.TOTALERRORS);
        } else {
            incrementSucesses(ErrorTypes.EMPTYNOTE);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(Association association){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if relation is binary
        if (association.getStart() != null && association.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYASSOCIATION);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYASSOCIATION);
            incrementFails(ErrorTypes.TOTALERRORS);
            return;
        }
        //Check if relation only connects use cases and actors
        if (((association.getStart().id() == Elements.USECASE || association.getStart().id() == Elements.EXTENSIONPOINT) && (association.getEnd().id() == Elements.ACTOR || association.getEnd().id() == Elements.NONHUMANACTOR)) || ((association.getStart().id() == Elements.ACTOR || association.getStart().id() == Elements.NONHUMANACTOR) && (association.getEnd().id() == Elements.USECASE || association.getEnd().id() == Elements.EXTENSIONPOINT))){
            incrementSucesses(ErrorTypes.NOTSAMETYPEASSOCIATION);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEASSOCIATION);
            incrementFails(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(Generalization generalization){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if relation is binary
        if (generalization.getStart() != null && generalization.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYGENERALIZATION);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYGENERALIZATION);
            incrementFails(ErrorTypes.TOTALERRORS);
            return;
        }
        //Check if relation either connects use cases and actors
        if (((generalization.getStart().id() == Elements.USECASE || generalization.getStart().id() == Elements.EXTENSIONPOINT) && (generalization.getEnd().id() == Elements.USECASE || generalization.getEnd().id() == Elements.EXTENSIONPOINT)) || ((generalization.getStart().id() == Elements.ACTOR || generalization.getStart().id() == Elements.NONHUMANACTOR) && (generalization.getEnd().id() == Elements.ACTOR || generalization.getEnd().id() == Elements.NONHUMANACTOR))){
            incrementSucesses(ErrorTypes.NOTSAMETYPEGENERALIZATION);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEGENERALIZATION);
            incrementFails(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(Includes includes){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if relation is binary
        if (includes.getStart() != null && includes.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYINCLUDES);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYINCLUDES);
            incrementFails(ErrorTypes.TOTALERRORS);
            return;
        }
        //Check if relation only connects use cases or extension points
        if ((includes.getStart().id() == Elements.USECASE || includes.getStart().id() == Elements.EXTENSIONPOINT) && (includes.getEnd().id() == Elements.USECASE || includes.getEnd().id() == Elements.EXTENSIONPOINT)){
            incrementSucesses(ErrorTypes.NOTSAMETYPEINCLUDES);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEINCLUDES);
            incrementFails(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(Extends extend){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if relation is binary
        if (extend.getStart() != null && extend.getEnd() != null){
            incrementSucesses(ErrorTypes.NONBINARYRELATION);
            incrementSucesses(ErrorTypes.NONBINARYEXTENDS);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NONBINARYRELATION);
            incrementFails(ErrorTypes.NONBINARYEXTENDS);
            incrementFails(ErrorTypes.TOTALERRORS);
            return;
        }
        //Check if relation only connects use cases or extension points
        if ((extend.getStart().id() == Elements.USECASE || extend.getStart().id() == Elements.EXTENSIONPOINT) && (extend.getEnd().id() == Elements.USECASE || extend.getEnd().id() == Elements.EXTENSIONPOINT)){
            incrementSucesses(ErrorTypes.NOTSAMETYPEEXTENDS);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.NOTSAMETYPEEXTENDS);
            incrementFails(ErrorTypes.TOTALERRORS);
        }
    }

    public void applyRules(ConditionRelation conditionRelation){
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        //Check if relation is NOT binary
        if (conditionRelation.getStart() == null && conditionRelation.getEnd() != null){

            incrementSucesses(ErrorTypes.BINARYCONDITIONRELATION);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.BINARYCONDITIONRELATION);
            incrementFails(ErrorTypes.TOTALERRORS);
            return;
        }
        //Check if relation only connects to note
        if (conditionRelation.getStart() == null || conditionRelation.getEnd().id()== Elements.NOTE ){
            incrementSucesses(ErrorTypes.CONDITIONRELATIONNONOTE);
            incrementSucesses(ErrorTypes.TOTALERRORS);
        } else {
            incrementFails(ErrorTypes.CONDITIONRELATIONNONOTE);
            incrementFails(ErrorTypes.TOTALERRORS);

        }
    }

    public void applyRules(UMLComponent component){
        incrementFails(ErrorTypes.UNKNOWNELEMENT);
    }
}
