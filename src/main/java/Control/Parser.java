package Control;
import java.util.ArrayList;
import java.util.List;
import Model.UMLComponent;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Parser {
    public List<UMLComponent> parseFile(String file){
        try {

            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("element");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

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
    public List<UMLComponent> startParse(){
        return null;
    }

}
