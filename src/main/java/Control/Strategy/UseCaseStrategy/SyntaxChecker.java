package Control.Strategy.UseCaseStrategy;

import Control.Strategy.UseCaseStrategy.Visitor.ErrorTypes;
import Control.Strategy.UseCaseStrategy.Visitor.ErrorWrapper;
import Model.ConditionRelation;
import Model.UMLComponent;
import Model.UseCase;

import java.util.HashMap;
import java.util.List;
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
        if (component.id() =="UseCase"){
            applyRules((UseCase) component);
        }
    }

    private void applyRules(UseCase useCase){
        if ( useCase.getName() == null){
            incrementFails(ErrorTypes.ENTITYNAME);
            incrementFails(ErrorTypes.USECASENAME);
        }
        incrementSucesses(ErrorTypes.ENTITYNAME);
        incrementSucesses(ErrorTypes.USECASENAME);
    }
}
