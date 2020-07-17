/**
 * @author rfrank2s
 */
package Control;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Model.Diagram;
import Model.UMLComponent;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

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
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    //-----
                    String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String x = eElement.getElementsByTagName("x").item(0).getTextContent();
                    String y = eElement.getElementsByTagName("y").item(0).getTextContent();
                    String w = eElement.getElementsByTagName("w").item(0).getTextContent();
                    String h = eElement.getElementsByTagName("h").item(0).getTextContent();
                    String panel_attributes = eElement.getElementsByTagName("panel_attributes").item(0).getTextContent();
                    if (id.equals(IdType.umlclass())){

                    }
                    //Weitere Komponenten erkennen




                    if (id.equals(IdType.relation())){

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<UMLComponent> classParse(String panelAttr){
        List<UMLComponent> list = new ArrayList<>();


        return list;
    }




    public List<UMLComponent> startParse(){
        return null;
    }

}
