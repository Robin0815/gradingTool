package Control.Strategy.UseCaseStrategy;

import Model.Elements;

import java.util.Map;

public class InstructionGenerator {
    public void createResponseSyntaxErrors(Map<ErrorTypes, ErrorWrapper> errormap){
        ErrorWrapper errorWrapper;
        String synResponse = "SYNTAX/SEMANTIK ANALYSE\n" +
                "-------------------------------------------------------------------------------------------------------\n";

        errorWrapper = errormap.get(ErrorTypes.TOTALERRORS);
        if (errorWrapper != null){
            synResponse += "Der Anteil der fehlerhaften Syntaxprüfungen beträgt: " + errorWrapper.getPercentage() + "%\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.ENTITYNAME);
        if (errorWrapper != null){
            synResponse += "Subjekte/Objekte wurden zu " + errorWrapper.getPercentage() +"% nicht benannt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.ACTORNAME);
        if (errorWrapper != null){
            synResponse += "Akteure wurden zu " + errorWrapper.getPercentage() +"% nicht benannt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.USECASENAME);
        if (errorWrapper != null){
            synResponse += "Use Cases wurden zu " + errorWrapper.getPercentage() +"% nicht benannt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.SYSTEMNAME);
        if (errorWrapper != null){
            synResponse += "Systeme wurden zu " + errorWrapper.getPercentage() +"% nicht benannt.\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.ACTORSCONTAINED);
        if (errorWrapper != null){
            synResponse += "Akteure wurden zu " + errorWrapper.getPercentage() +"% innerhalb von Systemen plaziert.\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.EMPTYNOTE);
        if (errorWrapper != null){
            synResponse += "Conditions innerhalb von Note Elementen wurden zu " + errorWrapper.getPercentage() +"% nicht spezifiziert.\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.EMPTYEXTENSIONPOINT);
        if (errorWrapper != null){
            synResponse += "Innerhalb von erweiterten Use Cases wurden zu " + errorWrapper.getPercentage() +"% keine Extension Points spezifiziert.\n";
        }
        System.out.println(synResponse);
    }


    public void createInstructionNumberOfElements(Elements element){

    }
}
