package Control.Strategy.ClassStrategy.Checker;

import Control.Parser.Parser;
import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
import Model.*;
import Model.Class;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimilarityChecker implements Checker {

    private String customCypher() {
        return "Match " +
                "Return count(*) as Passed";
    }

    private String mode;
    private double konv = 0.5;
    String res = "Vergleich mit einer Musterlösung :\n--------------------------------------------------\n";
    boolean re = true;
    //
    List<Class> musterClass = new ArrayList<>();
    List<Class> realClass = new ArrayList<>();
    double abweichungClass = 0;
    //
    List<Relation> musterRelation = new ArrayList<>();
    List<Relation> realRelation = new ArrayList<>();
    double abweichungRelation = 0;
    //
    List<Attribut> musterAttribut = new ArrayList<>();
    List<Attribut> realAttribut = new ArrayList<>();
    double abweichungAttribut = 0;
    //
    List<Method> musterMethod = new ArrayList<>();
    List<Method> realMethod = new ArrayList<>();
    double abweichungMethod = 0;
    //
    List<Constructor> musterConstructor = new ArrayList<>();
    List<Constructor> realConstructor = new ArrayList<>();
    double abweichungConstructor = 0;
//

    public SimilarityChecker(String mode) {
        this.mode = mode;
    }

    @Override
    public boolean checkUML(List<UMLComponent> comps) {
        if (mode.equals("Cypher")) {
            re = runCypher(comps);
        } else {
            if (mode.equals("Compare")) {
                re = runCompare(comps);
            } else {
                re = runNothing();
            }
        }
        FeedbackGenerator.getInstance().addRes(res);
        return re;
    }

    private boolean runNothing(){
        res += "Kein Vergleich gewünscht\n--------------------------------------------------\n";
        return true;
    }
    private boolean runCypher(List<UMLComponent> comps) {
        return true;
    }

    private boolean runCompare(List<UMLComponent> comps) {
        String homeDir = System.getProperty("user.home");
        homeDir = homeDir.replace("\\", "\\\\");
        //Musterloesung im home Verzeichnis unter /Korrektur/Ablegen
        File correctionFile = new File(homeDir + "\\Documents\\KorrekturMusterloesung\\Musterloesung.uxf");
        Parser a = new Parser();
        List<UMLComponent> musterList = a.parseFile(correctionFile);
        createComparisonSet(musterList, musterClass, musterRelation, musterAttribut, musterMethod, musterConstructor);
        createComparisonSet(comps, realClass, realRelation, realAttribut, realMethod, realConstructor);
        abweichungClass = (double) Math.abs(realClass.size() - musterClass.size()) / musterClass.size();
        res += "Die prozentuale Abweichung ziwschen der Anzahl an Klassen in Musterlösung und abgegebner Lösung ist : " +
                "" + abweichungClass + "\n";
        abweichungRelation = (double) Math.abs(realRelation.size() - musterRelation.size()) / musterRelation.size();
        res += "Die prozentuale Abweichung ziwschen der Anzahl an Relationen in Musterlösung und abgegebner Lösung ist : " +
                "" + abweichungRelation + "\n";
        abweichungAttribut = (double) Math.abs(realAttribut.size() - musterAttribut.size()) / musterAttribut.size();
        res += "Die prozentuale Abweichung ziwschen der Anzahl an Attributen in Musterlösung und abgegebner Lösung ist : " +
                "" + abweichungAttribut + "\n";
        abweichungMethod = (double) Math.abs(realMethod.size() - musterMethod.size()) / musterMethod.size();
        res += "Die prozentuale Abweichung ziwschen der Anzahl an Methoden in Musterlösung und abgegebner Lösung ist : " +
                "" + abweichungMethod + "\n";
        abweichungConstructor = (double) Math.abs(realConstructor.size() - musterConstructor.size()) / musterConstructor.size();
        res += "Die prozentuale Abweichung ziwschen der Anzahl an Konstruktoren in Musterlösung und abgegebner Lösung ist : " +
                "" + abweichungConstructor + "\n";
        double abweichung = (abweichungClass * 2 + abweichungRelation * 2 + abweichungAttribut + abweichungMethod + abweichungConstructor) / 5;
        if (abweichung > konv) {
            re = false;
            res += "--------------------------------------------------\n" +
                    "Die abgegbene Loesung weicht zu sehr von der Musterlösung ab, Abweichung : " + abweichung + "\n" +
                    "--------------------------------------------------\n";
        } else {
            res += "--------------------------------------------------\n" +
                    "Die abgegbene Loesung hat genug Ähnlichkeit mit der Musterlösung, Abweichung : " + abweichung + "\n" +
                    "--------------------------------------------------\n";
        }
        return re;
    }

    private void createComparisonSet(List<UMLComponent> comps, List<Class> realClass, List<Relation> realRelation, List<Attribut> realAttribut, List<Method> realMethod, List<Constructor> realConstructor) {
        for (UMLComponent component : comps) {
            if (component.id().equals(Elements.CLASS)) {
                realClass.add((Class) component);
            }
            if (component.id().equals(Elements.AGGREGATION) || component.id().equals(Elements.ASSOCIATION2) ||
                    component.id().equals(Elements.COMPOSITION) || component.id().equals(Elements.DEPENDENCY) ||
                    component.id().equals(Elements.INHERITANCE) || component.id().equals(Elements.IMPLEMENTS) ||
                    component.id().equals(Elements.RELATION)) {
                realRelation.add((Relation) component);
            }
        }
        for (Class cla : realClass) {
            for (UMLComponent component : cla.getElements()) {
                if (component.id().equals(Elements.ATTRIBUT)) {
                    realAttribut.add((Attribut) component);
                }
                if (component.id().equals(Elements.METHOD)) {
                    realMethod.add((Method) component);
                }
                if (component.id().equals(Elements.CONSTRUCTOR)) {
                    realConstructor.add((Constructor) component);
                }
            }
        }
    }

}
