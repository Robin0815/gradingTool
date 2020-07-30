package Control.Strategy.ClassStrategy.Control;


import Control.Parser.TempCompContainer;
import Model.Elements;
import Model.UMLComponent;
import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class GraphDBFunc {
    private static GraphDBFunc instance = null;
    private GraphDBFunc(){}
    public static GraphDBFunc getInstance(){
        if (instance == null) {
            synchronized (GraphDBFunc.class) {
                if (instance == null) {
                    instance = new GraphDBFunc();
                }
            }
        }
        return instance;
    }
    private GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
    private GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
            new File("data/db"));
    public GraphDatabaseService getGraphDb(){
        return graphDb;
    }
    public void deleteGraphDb(){
        graphDb.shutdown();
        try {
            FileUtils.deleteDirectory(new File("data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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









    public void test() {

        graphDb.beginTx();
        Node Class = graphDb.createNode(Label.label("Class"));
        Class.setProperty("Name", "Adapter");

        Node Class2 = graphDb.createNode(Label.label("Class"));
        Class2.setProperty("Name", "Legacy");

        Class.createRelationshipTo(Class2, RelationshipType.withName("contains"));

        deleteGraphDb();
        //System.out.println(graphDb.schema().toString());
    }
}
