/**
 * @author rfrank2s
 */
package Control;
import java.lang.System;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Model.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Model.Class;
import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import Control.IdType;

public class Parser {
    private String InterfaceType = "interface";
    private String AbstractType = "abstract";
    public List<UMLComponent> parseFile(String file){
        UMLComponent dia;
        List<UMLComponent> diaList = new ArrayList<>();
        List<TempComp> compPos = new ArrayList<>();
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
                    if (id.equals(IdType.umlclass())){
                        a = classParse(panel_attributes);
                        diaList.add(a);
                    }
                    //Weitere Komponenten erkennen


                    //Nur die Benötigten Elemente in die TempComp für die bestimmung der Assoziationen.
                    if(a instanceof ConnectableComp){
                        compPos.add(new TempComp(a, Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(w),Integer.parseInt(h)));
                    }
                }
            }
            //Relationen bestimmen
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
                    System.out.print((StringEscapeUtils.escapeJava(panel_attributes)));
                    if (id.equals(IdType.relation())){

                    }
                    //Weitere Komponenten erkennen

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    private UMLComponent classParse(String panelAttr){
        String name = "";
        String stereotype = null;

        List<UMLComponent> list = new ArrayList<>();
        System.out.println(panelAttr);
        String[] a = panelAttr.split("\n");
        if(a[0].equals("&lt;&lt;interface&gt;&gt;")){
            stereotype = StereoType.interf();
            name = a[1];
        }else
        if(a[0].equals("&lt;&lt;abstract&gt;&gt;")){
            stereotype = StereoType.abstrac();
            name = a[1];
        }else {
            name = a[0];
        }
        String[] c = panelAttr.split("--");
        for(int i = stereotype== null? 1: 2; i<c.length;i++){
            String s = a[i];
            String[] b =s.split("\n");
            for(int j = 0; j<b.length; j++){
                boolean isStatic = false;
                String visibility = "";
                String outputType;
                String inputType;
                String am = b[j];
                if(am.charAt(0) == '_'){
                    isStatic = true;
                    visibility = ""+am.charAt(1);
                }else{
                    visibility = ""+am.charAt(0);
                }
                if (am.contains("(")){
                    list.add(new Method(name, am.substring(am.indexOf('('),am.indexOf(')')), am.substring(am.indexOf(':')),isStatic,visibility);

                }else{

                    list.add(new Attribut(name, am.substring(':'), visibility, isStatic));

                }
                Class res = new Class(name, stereotype);
                res.setElements(list);
                return res;
            }
        }
        UMLComponent res= new Class(name, stereotype);


        return res;
    }




    public List<UMLComponent> startParse(){
        return null;
    }

}
