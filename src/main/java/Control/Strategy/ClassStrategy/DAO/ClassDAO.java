package Control.Strategy.ClassStrategy.DAO;

import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Model.*;
import Model.Class;
import org.neo4j.graphdb.*;


import java.util.List;

public class ClassDAO {
    private GraphDatabaseService graphDb = GraphDBFunction.getInstance().getGraphDb();

    public void create(Class a) {
        try (Transaction tx = graphDb.beginTx()) {
            Node c;
            if (a.getStereotype() == null) {
                c = tx.createNode(Label.label("Class"));
                c.setProperty("Name", a.getName());
            } else if (a.getStereotype().equals("interface")) {
                c = tx.createNode(Label.label("Interface"));
                c.setProperty("Name", a.getName());
            } else if (a.getStereotype().equals("abstract")) {
                c = tx.createNode(Label.label("Abstract"));
                c.setProperty("Name", a.getName());
            } else {
                c = tx.createNode(Label.label("Unknown"));
            }
            List<UMLComponent> l = a.getElements();
            for (UMLComponent umlComponent : l) {
                if (umlComponent.id().equals(Elements.ATTRIBUT)) {
                    Attribut k = (Attribut) umlComponent;
                    Node d = tx.createNode(Label.label("Attribute"));
                    d.setProperty("Name", k.getName());
                    d.setProperty("Visibility", k.getVisibility());
                    d.setProperty("Static", "" + k.isStatic());
                    d.setProperty("OutPut", k.getOutputType());
                    c.createRelationshipTo(d, RelationshipType.withName("contains"));
                }
                if (umlComponent.id().equals(Elements.METHOD)) {
                    Method k = (Method) umlComponent;
                    Node d = tx.createNode(Label.label("Method"));
                    d.setProperty("Name", k.getName());
                    d.setProperty("Visibility", k.getVisibility());
                    d.setProperty("Static", "" + k.isStatic());
                    d.setProperty("OutPut", k.getOutputType());
                    if (k.getInputType() != null && !k.getInputType().equals("")) {
                        String input = k.getInputType().get(0);
                        for (int h = 1; h < k.getInputType().size(); h++) {
                            input = input + ", " + k.getInputType().get(h);
                        }
                        d.setProperty("InPut", input);
                    }
                    c.createRelationshipTo(d, RelationshipType.withName("contains"));
                }
                if (umlComponent.id().equals(Elements.CONSTRUCTOR)) {
                    Constructor k = (Constructor) umlComponent;
                    Node d = tx.createNode(Label.label("Constructor"));
                    d.setProperty("Name", c.getProperty("Name"));
                    d.setProperty("Visibility", k.getVisibility());
                    if (k.getInputType() != null && !k.getInputType().equals("")) {
                        String input = "";
                        for (int h = 0; h < k.getInputType().size(); h++) {
                            input = input + ", " + k.getInputType().get(h);
                        }
                        d.setProperty("InPut", input);
                    }

                    c.createRelationshipTo(d, RelationshipType.withName("contains"));
                }

            }
            tx.commit();
        }

        //System.out.println(graphDb.schema().toString());


    }
}