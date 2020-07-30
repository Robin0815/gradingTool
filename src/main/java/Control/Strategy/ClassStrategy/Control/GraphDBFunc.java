package Control.Strategy.ClassStrategy.Control;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

public class GraphDBFunc {
    public void test() {
        GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
                new File("data/db"));
        graphDb.beginTx();
        Node Class = graphDb.createNode(Label.label("Class"));
        Class.setProperty("Name", "Adapter");

        Node Class2 = graphDb.createNode(Label.label("Class"));
        Class2.setProperty("Name", "Legacy");

        Class.createRelationshipTo(Class2, RelationshipType.withName("Associattion"));

        System.out.println(graphDb.toString());
    }


}
