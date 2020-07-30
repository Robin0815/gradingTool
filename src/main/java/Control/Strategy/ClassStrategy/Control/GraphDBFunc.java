package Control.Strategy.ClassStrategy.Control;


import Model.Elements;
import Model.UMLComponent;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GraphDBFunc {
    private GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
    private GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
            new File("data/db"));
    public void test() {

        graphDb.beginTx();
        Node Class = graphDb.createNode(Label.label("Class"));
        Class.setProperty("Name", "Adapter");

        Node Class2 = graphDb.createNode(Label.label("Class"));
        Class2.setProperty("Name", "Legacy");

        Class.createRelationshipTo(Class2, RelationshipType.withName("Associattion"));

        //System.out.println(graphDb.schema().toString());
    }
    public void setUp(List<UMLComponent> comps){
        List<UMLComponent> lClass = new ArrayList<>();
        List<UMLComponent> lRelation = new ArrayList<>();
        for(int i = 0; i<comps.size();i++){
            if (comps.get(i).id().equals(Elements.CLASS)) {
                lClass.add(comps.get(i));
            }
            if (comps.get(i).id().equals(Elements.RELATION)) {
                lRelation.add(comps.get(i));
            }
        }

    }


}
