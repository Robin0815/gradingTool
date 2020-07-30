package Control.Parser;

import Model.*;
import Model.Class;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UMLFactory {

    public void makeTempComp (UMLComponent u,String x, String y, String w, String h){
        TempCompContainer.getInsance().addComp(new TempComp(u, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h)));
    }
    public UnknownElement makeUnknownElement(){
        return new UnknownElement();
    }
    public Relation makeRelation(String panelAttr){
        Relation res;
        if (panelAttr.contains("lt=<-")) {
            res = new Association2();
        } else if (panelAttr.contains("lt=<.")) {
            res = new Dependency();
        } else if (panelAttr.contains("lt=<<.")) {
            res = new Implements();
        } else if (panelAttr.contains("lt=<<-")) {
            res = new Inheritance();
        } else if (panelAttr.contains("lt=<<<<<-")) {
            res = new Composition();
        } else if (panelAttr.contains("lt=<<<<-")) {
            res = new Aggregation();
        } else if (panelAttr.contains("lt=.>") && panelAttr.contains("<<includes>>")) {
            res = new Includes();
        } else if (panelAttr.contains("lt=.>") && panelAttr.contains("<<extends>>")) {
            res = new Extends();
        } else if (!panelAttr.contains("=")) {
            res = new Association();
        } else if (panelAttr.contains("lt=.()")) {
            res = new ConditionRelation();
        } else if (panelAttr.contains("lt=->>")) {
            res = new Generalization();
        } else {
            res = new UnknownRelation();
        }
        return res;
    }
    public UMLComponent makeSystem(String panelAttr, int x, int y, int w, int h, int runt) {
        if (panelAttr.contains("valign=") && runt == 0) {
            NonHumanActor res = new NonHumanActor();
            String tmp = panelAttr.split("\n")[0];
            if(tmp.contains("valign")){
                res.setName("");
                return res;
            }
            res.setName(tmp);
            return res;
        } else if (panelAttr.contains("halign=") && runt == 1) {
            UMLSystem res = new UMLSystem();
            String tmpstring = panelAttr.split("\n")[0];
            if(tmpstring.contains("halign")){
                res.setName("");
            } else {
                res.setName(tmpstring);
            }
            Rectangle sy = new Rectangle(x,y,w,h);
            res.setContainedElements(TempCompContainer.getInsance().contains(sy));
            return res;
        }
        return null;
    }

    public UMLComponent makeActor(String panelAttr) {
        Actor res = new Actor();
        res.setName(panelAttr);
        return res;
    }

    public UMLComponent makeNote(String panelAttr) {
        Note res = new Note();
        res.setText(panelAttr);
        return res;
    }

    public UMLComponent makeUseCase(String panelAttr) {

        if (panelAttr.contains("--")) {
            ExtensionPoint res = new ExtensionPoint();
            String[] s = panelAttr.split("--");
            String[] s1 = s[0].split("\n");
            if ( s1.length == 0){
                res.setName("");
                return res;
            }
            res.setName(s1[0]);
            s1 = s[1].split("\n");
            for (String value : s1) {
                if (!value.contains("valign") & !value.isEmpty()) {
                    res.addExtpoint(value);
                }
            }
            return res;
        } else {
            UseCase res = new UseCase();
            res.setName(panelAttr);
            return res;
        }


    }

    public UMLComponent makeRelation(String panelAttr, String addAttr, int x, int y) {
        //Relation erkennen
        Relation res = makeRelation(panelAttr);
        Point start;
        Point end;
        String[] s = addAttr.split(";");
        int x2 = (int) Double.parseDouble(s[0]);
        int y2 = (int) Double.parseDouble(s[1]);
        int x1 = (int) Double.parseDouble(s[s.length - 2]);
        int y1 = (int) Double.parseDouble(s[s.length - 1]);
        start = new Point(x + x1, y + y1);
        end = new Point(x + x2, y + y2);
        res.setStart(TempCompContainer.getInsance().contains(start).isEmpty() ? null : TempCompContainer.getInsance().contains(start).get(0));
        res.setEnd(TempCompContainer.getInsance().contains(end).isEmpty() ? null : TempCompContainer.getInsance().contains(end).get(0));
        return res;
    }


    public UMLComponent makeClass(String panelAttr) {
        String name;
        String stereotype = null;

        java.util.List<UMLComponent> list = new ArrayList<>();
        String[] a = panelAttr.split("\n");
        if (a[0].contains("<<interface>>")) {
            stereotype = "interface";
            name = a[1];
        } else if (a[0].contains("<<abstract>>")) {
            stereotype = "abstract";
            name = a[1];
        } else {
            name = a[0];
        }
        Class res = new Class(name, stereotype);
        for (int i = stereotype == null ? 2 : 3; i < a.length; i++) {
            String s = a[i];
            String[] b = s.split("\n");
            for (String value : b) {
                boolean isStatic = false;
                String visibility;
                String am = value;
                am = am.replaceAll(" ", "");
                if (am.isEmpty()) {
                    break;
                }
                if (am.charAt(0) == '_') {
                    isStatic = true;
                    visibility = "" + am.charAt(1);
                    am = am.substring(1, am.length() - 1);
                } else {
                    visibility = "" + am.charAt(0);
                }
                if (am.contains("(")) {
                    List<String> input = new ArrayList<>();
                    String[] ab = (am.substring(am.indexOf('(') + 1, am.indexOf(')')).split(":"));
                    for (int p = 1; p < ab.length - 1; p++) {
                        //System.out.println(ab[p]);
                        input.add(ab[p].replace(" ", "").substring(0, ab[p].indexOf(',')));
                    }
                    input.add(ab[ab.length - 1].replace(" ", ""));
                    if (!am.substring(am.indexOf(')')).contains(":")) {
                        list.add(new Constructor(visibility, input));
                    }
                    else {
                        list.add(new Method(am.substring(1, am.indexOf('(')), input, am.substring(am.lastIndexOf(':') + 1), isStatic, visibility));
                    }
                } else if (am.contains(":")) {

                    list.add(new Attribut(am.substring(1, am.indexOf(':')), am.substring(am.indexOf(':') + 1), visibility, isStatic));

                }
            }

        }

        res.setElements(list);

        return res;
    }

}
