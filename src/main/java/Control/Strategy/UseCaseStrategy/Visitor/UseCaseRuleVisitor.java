package Control.Strategy.UseCaseStrategy.Visitor;

import Model.*;
import Model.Class;
import Model.UMLSystem;

import java.util.HashMap;
import java.util.Map;

public class UseCaseRuleVisitor implements Visitor{
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

    @Override
    public void visit(Actor actor) {
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        if (actor.getName() == null){
            incrementFails(ErrorTypes.ACTORNAME);
            incrementFails(ErrorTypes.ENTITYNAME);
        }else{
            incrementSucesses(ErrorTypes.ACTORNAME);
            incrementSucesses(ErrorTypes.ENTITYNAME);
        }
    }

    @Override
    public void visit(Aggregation aggregation) {
    }

    @Override
    public void visit(Association association) {

    }

    @Override
    public void visit(Attribut attribut) {

    }

    @Override
    public void visit(Class umlclass) {

    }

    @Override
    public void visit(Composition composition) {

    }

    @Override
    public void visit(ConditionRelation conditionRelation) {

    }

    @Override
    public void visit(Dependency dependency) {

    }

    @Override
    public void visit(Diagram diagram) {

    }

    @Override
    public void visit(Extends extRel) {

    }

    @Override
    public void visit(ExtensionPoint extensionPoint) {
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        if (extensionPoint.getName() == null){
            incrementFails(ErrorTypes.USECASENAME);
            incrementFails(ErrorTypes.ENTITYNAME);
        }else{
            incrementSucesses(ErrorTypes.USECASENAME);
            incrementSucesses(ErrorTypes.ENTITYNAME);
        }

    }

    @Override
    public void visit(Generalization generalization) {

    }

    @Override
    public void visit(Implements implRel) {

    }

    @Override
    public void visit(Includes includes) {

    }

    @Override
    public void visit(Inheritance inheritance) {

    }

    @Override
    public void visit(Method method) {

    }

    @Override
    public void visit(Note note) {

    }

    @Override
    public void visit(UMLSystem system) {
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        if (system.getName() == null){
            incrementFails(ErrorTypes.SYSTEMNAME);
            incrementFails(ErrorTypes.ENTITYNAME);
        }else{
            incrementSucesses(ErrorTypes.SYSTEMNAME);
            incrementSucesses(ErrorTypes.ENTITYNAME);
        }

    }

    @Override
    public void visit(UnknownElement unknownElement) {

    }

    @Override
    public void visit(UnknownRelation unknownRelation) {

    }

    @Override
    public void visit(Use use) {

    }

    @Override
    public void visit(UseCase useCase) {
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        if (useCase.getName() == null){
            incrementFails(ErrorTypes.USECASENAME);
            incrementFails(ErrorTypes.ENTITYNAME);
        }else{
            incrementSucesses(ErrorTypes.USECASENAME);
            incrementSucesses(ErrorTypes.ENTITYNAME);
        }

    }

    @Override
    public void visit(NonHumanActor nonHumanActor) {
        incrementSucesses(ErrorTypes.UNKNOWNELEMENT);
        if (nonHumanActor.getName() == null){
            incrementFails(ErrorTypes.ACTORNAME);
            incrementFails(ErrorTypes.ENTITYNAME);
        }else{
            incrementSucesses(ErrorTypes.ACTORNAME);
            incrementSucesses(ErrorTypes.ENTITYNAME);
        }
    }
}
