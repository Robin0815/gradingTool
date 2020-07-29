/*
 * @author rfrank2s
 * @author mkowol2s
 */
package Control.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import Model.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;


public class Parser {
    //private List<TempComp> compPos = new ArrayList<>();
    UMLFactory factory = new UMLFactory();

    public List<UMLComponent> parseFile(File file) {
        //UMLComponent dia;
        List<UMLComponent> diaList = new ArrayList<>();

        Document doc = null;
        try {
            doc = fileReader(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        doc.getDocumentElement().normalize();
            /*
            https://www.codeflow.site/de/article/java__how-to-read-xml-file-in-java-dom-parser Quelle für die XML Pars Funktionen
             */
        //dia = new Diagram(doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("element");
        //erster Durchlauf für alle Elemente die keine Abhängikeiten von anderen haben
        for (int runt = 0; runt < 2; runt++) {
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //Variablen Einlesen
                    UMLComponent a;
                    String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String x = eElement.getElementsByTagName("x").item(0).getTextContent();
                    String y = eElement.getElementsByTagName("y").item(0).getTextContent();
                    String w = eElement.getElementsByTagName("w").item(0).getTextContent();
                    String h = eElement.getElementsByTagName("h").item(0).getTextContent();
                    String panel_attributes = eElement.getElementsByTagName("panel_attributes").item(0).getTextContent();
                    if (id.equals(IdType.umlclass()) && runt == 0) {
                        a = factory.makeClass(panel_attributes);
                        diaList.add(a);
                        if (a.isConnectable()) {
                            factory.makeTempComp(a, x, y, w, h);
                        }
                    }
                    if (id.equals((IdType.usecase())) && runt == 0) {
                        a = factory.makeUseCase(panel_attributes);
                        diaList.add(a);
                        if (a.isConnectable()) {
                            factory.makeTempComp(a, x, y, w, h);
                        }
                    }

                    if (id.equals((IdType.actor())) && runt == 0) {
                        a = factory.makeActor(panel_attributes);
                        diaList.add(a);
                        if (a.isConnectable()) {
                            factory.makeTempComp(a, x, y, w, h);
                        }
                    }

                    if (id.equals((IdType.system()))) {
                        a = factory.makeSystem(panel_attributes, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h), runt);
                        if (a != null) {
                            diaList.add(a);
                            if (a.isConnectable()) {
                                factory.makeTempComp(a, x, y, w, h);
                            }
                        }

                    }

                    if (id.equals((IdType.note())) && runt == 0) {
                        a = factory.makeNote(panel_attributes);
                        diaList.add(a);
                        if (a.isConnectable()) {
                            factory.makeTempComp(a, x, y, w, h);
                        }

                        //Weitere Komponenten erkennen


                        //Nur die Benötigten Elemente in die TempComp für die bestimmung der Assoziationen.

                        //Relationen erst beim 2. durchlauf betrachten
                        if (id.equals(IdType.relation()) && runt == 1) {
                            String additional_attributes = eElement.getElementsByTagName("additional_attributes").item(0).getTextContent();
                            a = factory.makeRelation(panel_attributes, additional_attributes, Integer.parseInt(x), Integer.parseInt(y));
                            diaList.add(a);
                        }

                        //Alle nicht in IdType definierten Elemente als Unknown Element anlegen
                        if (!(id.equals(IdType.note()) || id.equals(IdType.system()) || id.equals(IdType.umlclass()) || id.equals(IdType.actor()) || id.equals(IdType.usecase()) || id.equals(IdType.relation())) && runt == 1) {
                            a = factory.makeUnknownElement();
                            diaList.add(a);
                        }
                    }
                }
            }
        }

            return diaList;
        }




    private Document fileReader(File file)  throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        return doc;
    }


}
