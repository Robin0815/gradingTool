package Control.Strategy.UseCaseStrategy;

import Model.Elements;

import java.util.Map;

public class ReportGenerator {
    public void createReportSyntaxErrors(Map<ErrorTypes, ErrorWrapper> errormap){
        ErrorWrapper errorWrapper;
        String synResponse = "SYNTAX/SEMANTIK ANALYSE\n" +
                "-------------------------------------------------------------------------------------------------------\n";

        errorWrapper = errormap.get(ErrorTypes.TOTALERRORS);
        if (errorWrapper != null){
            synResponse += "Der Anteil der fehlerhaften Syntaxprüfungen beträgt: " + errorWrapper.getPercentage() + "%\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.UNKNOWNELEMENT);
        if (errorWrapper != null){
            synResponse += "Es wurden zu " + errorWrapper.getPercentage() +"% Use Case fremde Elemente verwendet.\n";
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
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.NONBINARYRELATION);
        if (errorWrapper != null){
            synResponse += "Relationen hatten zu " + errorWrapper.getPercentage() +"% keinen definierten Start und Endpunkt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NONBINARYASSOCIATION);
        if (errorWrapper != null){
            synResponse += "Assoziationen hatten zu " + errorWrapper.getPercentage() +"% keinen definierten Start und Endpunkt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NONBINARYINCLUDES);
        if (errorWrapper != null){
            synResponse += "Includes hatten zu " + errorWrapper.getPercentage() +"% keinen definierten Start und Endpunkt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NONBINARYEXTENDS);
        if (errorWrapper != null){
            synResponse += "Extends hatten zu " + errorWrapper.getPercentage() +"% keinen definierten Start und Endpunkt.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NONBINARYGENERALIZATION);
        if (errorWrapper != null){
            synResponse += "Generalisierungen/Spezialisierungen hatten zu " + errorWrapper.getPercentage() +"% keinen definierten Start und Endpunkt.\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.NOTSAMETYPEASSOCIATION);
        if (errorWrapper != null){
            synResponse += "Assoziationen haben zu " + errorWrapper.getPercentage() +"% nicht Akteure und Use Cases verbunden.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NOTSAMETYPEEXTENDS);
        if (errorWrapper != null){
            synResponse += "Extends haben zu " + errorWrapper.getPercentage() +"% nicht nur Use Cases verbunden.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NOTSAMETYPEINCLUDES);
        if (errorWrapper != null){
            synResponse += "Includes haben zu " + errorWrapper.getPercentage() +"% nicht nur Use Cases verbunden.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.NOTSAMETYPEGENERALIZATION);
        if (errorWrapper != null){
            synResponse += "Generalisierungen/Spezialisierungen haben zu " + errorWrapper.getPercentage() +"% nicht nur Typgleiche Elemente verbunden.\n";
        }
        synResponse += "-------------------------------------------------------------------------------------------------------\n";
        errorWrapper = errormap.get(ErrorTypes.BINARYCONDITIONRELATION);
        if (errorWrapper != null){
            synResponse += "Beziehungen um Conditions anzuhängen haben zu " + errorWrapper.getPercentage() +"% nicht nur ein Element verbunden.\n";
        }
        errorWrapper = errormap.get(ErrorTypes.CONDITIONRELATIONNONOTE);
        if (errorWrapper != null){
            synResponse += "Beziehungen um Conditions anzuhängen haben zu " + errorWrapper.getPercentage() +"% nicht Notizen verbunden.\n";
        }
        System.out.println(synResponse);
    }


    public void createInstructionNumberOfElements(Elements element){

    }
}
