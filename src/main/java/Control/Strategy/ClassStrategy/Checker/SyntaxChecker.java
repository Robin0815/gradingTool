package Control.Strategy.ClassStrategy.Checker;

import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
import Model.*;
import Model.Class;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SyntaxChecker implements Checker{
    String res1 = "";
    String res2 = "";
    @Override
    public boolean checkUML(List<UMLComponent> comps) {
        List<Class> listClass= new ArrayList<>();
        List<Relation> listRelation= new ArrayList<>();
        for(UMLComponent component : comps){
            if (component.id().equals(Elements.CLASS)){
                listClass.add((Class) component);
            }
            if(component.id().equals(Elements.AGGREGATION) || component.id().equals(Elements.ASSOCIATION2) ||
                    component.id().equals(Elements.COMPOSITION) || component.id().equals(Elements.DEPENDENCY) ||
                    component.id().equals(Elements.INHERITANCE) || component.id().equals(Elements.IMPLEMENTS) ||
                    component.id().equals(Elements.RELATION)){
                listRelation.add((Relation) component);
            }
        }
        res1 += "Auswertung der UML Syntax:\n--------------------------------------------------\n";
        boolean re1 = checkConnectableComp(listClass);
        boolean re2 = checkRelation(listRelation);
        if(re1 & re2){
            res2 += "Syntax Check erfolgreich\n";
        }else{
            res2 += "Syntax Check fehlgeschlagen, nochmal Versuchen ohne Grundsätzliche Fehler\n";
        }
        FeedbackGenerator.getInstance().addRes(res1+res2);
        return re1 && re2;
    }
    private boolean checkConnectableComp(List<Class> comps){
        boolean re = true;
        //List<UMLComponent> comps2 = new ArrayList<>(comps);
        for(int i = 0; i<comps.size();i++){
            for (int j = 0; j<comps.size(); j++){
                if(i!=j && comps.get(i).getName().equals( comps.get(j).getName())) {
                    res1 += "Feher bei Name der Klasse : " + comps.get(i).getName() + "\n";
                    re = false;
                }
            }
            if(comps.get(i).getName().replace(" ", "").equals("")){
                res1+= "Name einer Klasse vergessen\n";
                re = false;
            }
        }
        for( Class a : comps){
            for (UMLComponent component : a.getElements()){
                if(component.id().equals(Elements.ATTRIBUT)){
                    Attribut component2 = (Attribut) component;
                    if(component2.getName().replace(" ", "").equals("")){
                        res1+= "Name eines Attributs vergessen\n";
                        re = false;
                    }
                }
                if(component.id().equals(Elements.METHOD)){
                    Method component2 = (Method) component;
                    if(component2.getName().replace(" ", "").equals("")){
                        res1+= "Name einer Methode vergessen\n";
                        re = false;
                    }
                }
                if(!component.id().equals(Elements.METHOD)& !component.id().equals(Elements.ATTRIBUT)& !component.id().equals(Elements.CONSTRUCTOR)){
                    res1+= "Unbekanntes Element in der Klasse : "+a.getName()+" : "+ component.toString()+"\n";
                    re = false;
                }
            }
        }
        if(re){
            res1+= "Die Klassen sind von der Form korrekt\n--------------------------------------------------\n";
        }else{
            res1+= "Die Klassen sind von der Form fehlerhaft und müssen überarbeitet werden, \n" +
                    "Kontrollieren sie die Bennenung und die Verwendung der korrekten Syntax\n--------------------------------------------------\n";
        }

        return re;
    }
    private boolean checkRelation(List<Relation> comps){
        boolean res = true;

        for (Relation relation : comps){
            try {
                if (relation.getEnd() == null || relation.getStart() == null) {
                    res2 += "Nicht Binaere Relation gefunden :" + relation.toString();
                    res = false;
                }else{
                if (relation.id().equals(Elements.INHERITANCE)) {
                    if (relation.getEnd().id().equals(Elements.CLASS)) {
                        Class a = (Class) relation.getEnd();
                        if (a.getStereotype().equals("interface")) {
                            res2 += "Es wird von einem Interface geerbt";
                            res = false;
                        }
                    }
                }
                if (relation.id().equals(Elements.IMPLEMENTS)) {
                    if (relation.getEnd().id().equals(Elements.CLASS)) {
                        Class a = (Class) relation.getEnd();
                        if (a.getStereotype() == null ? true : a.getStereotype().equals("abstract")) {
                            res2 += "Es wird eine Klasse mit 'implements' genutzt";
                            res = false;
                        }
                    }
                }}
            }catch(Exception e){
                res = false;
                res2 += "Fehler beim Relationen Testen";
            }
        }
        if(res){
            res2+= "Die Relationen sind von der Form korrekt\n";
        }else{
            res2+= "Die Relationen sind nicht Korrekt,\nbitte prüfen sie die Relationen auf einen gültigen Start und End Punkt.\n";
        }
        res2+= "--------------------------------------------------\n";
        return res;
    }
}
