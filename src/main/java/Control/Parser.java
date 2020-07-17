/**
 * @author rfrank2s
 */
package Control;
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

public class Parser {
    private String InterfaceType = "interface";
    private String AbstractType = "abstract";
    public List<UMLComponent> parseFile(String file){
        UMLComponent dia;
        List comp = new ArrayList();
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

                    System.out.println("id : " + eElement.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Content : " + eElement.getElementsByTagName("panel_attributes").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<UMLComponent> panelPars(String panelAttr){
        List<UMLComponent> list = new ArrayList<>();


        return list;
    }




    public List<UMLComponent> startParse(){
        return null;
    }

}
