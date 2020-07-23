/**
 * @author rfrank2s
 * @author mkowol2s
 */
package Control.Parser;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Control.Strategy.Strategy;
import Model.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Model.Class;
import Model.UMLSystem;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;


public class Parser {
    private Strategy strategy;
    private List<TempComp> compPos = new ArrayList<>();

    public List<UMLComponent> parseFile(String file) {
        UMLComponent dia;
        List<UMLComponent> diaList = new ArrayList<>();

        try {

            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            /*
            https://www.codeflow.site/de/article/java__how-to-read-xml-file-in-java-dom-parser Quelle für die XML Pars Funktionen
             */
            dia = new Diagram(doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("element");
            //erster Durchlauf für alle Elemente die keine Abhängikeiten von anderen haben
            for (int runt = 0; runt < 2; runt++) {
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        //-----
                        UMLComponent a = null;
                        String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                        String x = eElement.getElementsByTagName("x").item(0).getTextContent();
                        String y = eElement.getElementsByTagName("y").item(0).getTextContent();
                        String w = eElement.getElementsByTagName("w").item(0).getTextContent();
                        String h = eElement.getElementsByTagName("h").item(0).getTextContent();
                        String panel_attributes = eElement.getElementsByTagName("panel_attributes").item(0).getTextContent();
                        if (id.equals(IdType.umlclass()) && runt == 0) {
                            a = classParse(panel_attributes);
                            diaList.add(a);
                            if (a.isConnectable()) {
                                compPos.add(new TempComp(a, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h)));
                            }
                        }
                        if (id.equals((IdType.usecase())) && runt == 0) {
                            a = useCaseParse(panel_attributes);
                            diaList.add(a);
                            if (a.isConnectable()) {
                                compPos.add(new TempComp(a, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h)));
                            }
                        }

                        if (id.equals((IdType.actor())) && runt == 0) {
                            a = actorParse(panel_attributes);
                            diaList.add(a);
                            if (a.isConnectable()) {
                                compPos.add(new TempComp(a, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h)));
                            }
                        }

                        if (id.equals((IdType.system())) && runt == 1) {
                            a = systemParse(panel_attributes, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h));
                            diaList.add(a);
                        }

                        if (id.equals((IdType.note())) && runt == 1) {
                            a = noteParse(panel_attributes);
                            diaList.add(a);
                            if (a.isConnectable()) {
                                compPos.add(new TempComp(a, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h)));
                            }
                        }

                        //Weitere Komponenten erkennen


                        //Nur die Benötigten Elemente in die TempComp für die bestimmung der Assoziationen.

                        //Relationen erst beim 2. durchlauf betrachten
                        if (id.equals(IdType.relation()) && runt == 1) {
                            String additional_attributes = eElement.getElementsByTagName("additional_attributes").item(0).getTextContent();
                            a = relationParse(panel_attributes, additional_attributes, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h));
                            diaList.add(a);
                        }

                        //Alle nicht in IdType definierten Elemente als Unknown Element anlegen
                        if (!(id.equals(IdType.note()) || id.equals(IdType.system()) || id.equals(IdType.umlclass()) || id.equals(IdType.actor()) || id.equals(IdType.usecase()) || id.equals(IdType.relation())) && runt == 1) {
                            a = new UnknownElement();
                            diaList.add(a);
                        }
                    }
                }
            }
            //Relationen bestimmen
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diaList;
    }

    private UMLComponent systemParse(String panelAttr, int x, int y, int w, int h) {
        if (panelAttr.contains("valign=")) {
            NonHumanActor res = new NonHumanActor();
            res.setName(panelAttr.split("\n")[0]);
            return res;
        } else {
            UMLSystem res = new UMLSystem();
            res.setName(panelAttr.split("\n")[0]);
            Rectangle sy = new Rectangle(x,y,w,h);
            for (int i = 0; i < compPos.size(); i++) {
                TempComp tmp = compPos.get(i);
                Rectangle rec = tmp.getRec();

                if (sy.contains(rec)) {
                    res.addContainedElement(tmp.getComp());
                }
            }
            return res;
        }
    }

    private UMLComponent actorParse(String panelAttr) {
        Actor res = new Actor();
        res.setName(panelAttr);
        return res;
    }

    private UMLComponent noteParse(String panelAttr) {
        Note res = new Note();
        res.setText(panelAttr);
        return res;
    }

    private UMLComponent useCaseParse(String panelAttr) {

        if (panelAttr.contains("--")) {
            ExtensionPoint res = new ExtensionPoint();
            String[] s = panelAttr.split("--");
            String[] s1 = s[0].split("\n");
            res.setName(s1[0]);
            s1 = s[1].split("\n");
            for (int i = 0; i < s1.length; i++) {
                if (!s1[i].contains("valign") & !s1[i].isEmpty()) {
                    res.addExtpoint(s1[i]);
                }
            }
            return res;
        } else {
            UseCase res = new UseCase();
            res.setName(panelAttr);
            return res;
        }


    }

    private UMLComponent relationParse(String panelAttr, String addAttr, int x, int y, int w, int h) {
        Relation res;
        Point start = null;
        Point end = null;
        String[] s = addAttr.split(";");
        int x2 = (int) Double.parseDouble(s[0]);
        int y2 = (int) Double.parseDouble(s[1]);
        int x1 = (int) Double.parseDouble(s[s.length - 2]);
        int y1 = (int) Double.parseDouble(s[s.length - 1]);
        start = new Point(x + x1, y + y1);
        end = new Point(x + x2, y + y2);
        //Relation erkennen
        if (panelAttr.contains("lt=<-")) {
            res = new Use();
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


        for (int i = 0; i < compPos.size(); i++) {
            TempComp tmp = compPos.get(i);
            Rectangle rec = tmp.getRec();
            /*Class cl = (Class) tmp.getComp();
            System.out.println("Rechteck der Klasse: "+ cl.getName()+" "+rec.getMinX() + " "+rec.getMaxX() + " "+rec.getMinY() + " "+rec.getMaxY());
            System.out.println("Start: "+start);
            System.out.println("End: "+end);*/
            /*if( rec.contains(start)){
                res.setStart(tmp.getComp());
            }
            if(rec.contains(end)){
                res.setEnd(tmp.getComp());
            }*/
            if (start.getX() >= rec.getMinX() & start.getX() <= rec.getMaxX() & start.getY() >= rec.getMinY() & start.getY() <= rec.getMaxY()) {
                res.setStart(tmp.getComp());
            }
            if (end.getX() >= rec.getMinX() & end.getX() <= rec.getMaxX() & end.getY() >= rec.getMinY() & end.getY() <= rec.getMaxY()) {
                res.setEnd(tmp.getComp());
            }
        }
        return res;
    }


    private UMLComponent classParse(String panelAttr) {
        String name = "";
        String stereotype = null;

        List<UMLComponent> list = new ArrayList<>();
        String[] a = panelAttr.split("\n");
        if (a[0].contains("<<interface>>")) {
            stereotype = StereoType.interf();
            name = a[1];
        } else if (a[0].contains("<<abstract>>")) {
            stereotype = StereoType.abstrac();
            name = a[1];
        } else {
            name = a[0];
        }
        Class res = new Class(name, stereotype);
        for (int i = stereotype == null ? 2 : 3; i < a.length; i++) {
            String s = a[i];
            String[] b = s.split("\n");
            for (int j = 0; j < b.length; j++) {
                boolean isStatic = false;
                String visibility = "";
                String am = b[j];
                am = am.replaceAll(" ", "");
                if (am.charAt(0) == '_') {
                    isStatic = true;
                    visibility = "" + am.charAt(1);
                    am = am.substring(1, am.length() - 1);
                } else {
                    visibility = "" + am.charAt(0);
                }
                if (am.contains("(")) {
                    list.add(new Method(am.substring(1, am.indexOf('(')), am.substring(am.indexOf('('), am.indexOf(')')).contains(":") ? am.substring(am.indexOf(':') + 1, am.indexOf(')')) : am.substring(am.indexOf('('), am.indexOf(')')), am.substring(am.lastIndexOf(':') + 1), isStatic, visibility));
                } else if (am.contains(":")) {

                    list.add(new Attribut(am.substring(1, am.indexOf(':')), am.substring(am.indexOf(':') + 1), visibility, isStatic));

                }
            }

        }

        res.setElements(list);

        return res;
    }


    public List<UMLComponent> startParse() {
        return null;
    }

}
