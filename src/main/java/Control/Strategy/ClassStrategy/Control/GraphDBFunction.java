package Control.Strategy.ClassStrategy.Control;


import Control.Strategy.ClassStrategy.DAO.ClassDAO;
import Control.Strategy.ClassStrategy.DAO.RelationDAO;
import Model.Class;
import Model.Elements;
import Model.Relation;
import Model.UMLComponent;
import org.apache.commons.io.FileUtils;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.*;
//import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import javax.swing.table.TableRowSorter;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class GraphDBFunction {
    private static GraphDBFunction instance = null;

    private GraphDBFunction() {
    }

    public static GraphDBFunction getInstance() {
        if (instance == null) {
            synchronized (GraphDBFunction.class) {
                if (instance == null) {
                    instance = new GraphDBFunction();
                }
            }
        }
        return instance;
    }

    /*
        private GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
        private GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
                new File("data/db"));
    */
    private GraphDatabaseService graphDb;
    private DatabaseManagementService managementService;

    public GraphDatabaseService getGraphDb() {
        return graphDb;
    }

    private static void registerShutdownHook(final DatabaseManagementService managementService) {
        Runtime.getRuntime().addShutdownHook((new Thread() {
            @Override
            public void run() {
                managementService.shutdown();
                try {
                    FileUtils.deleteDirectory(new File("data"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    /*
        public void deleteGraphDb() {
            graphDb.shutdown();
            try {
                FileUtils.deleteDirectory(new File("data"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    */
    public void setUp(List<UMLComponent> comps) {
        managementService = new DatabaseManagementServiceBuilder(new File("data/db")).build();
        graphDb = managementService.database("neo4j");
        registerShutdownHook(managementService);

        List<UMLComponent> lClass = new ArrayList<>();
        List<UMLComponent> lRelation = new ArrayList<>();
        for (int i = 0; i < comps.size(); i++) {
            if (comps.get(i).id().equals(Elements.CLASS)) {
                lClass.add(comps.get(i));
            }
            if (comps.get(i).id().equals(Elements.RELATION) || comps.get(i).id().equals(Elements.DEPENDENCY) || comps.get(i).id().equals(Elements.ASSOCIATION2)
                    || comps.get(i).id().equals(Elements.IMPLEMENTS) || comps.get(i).id().equals(Elements.INHERITANCE) || comps.get(i).id().equals(Elements.COMPOSITION) ||
                    comps.get(i).id().equals(Elements.AGGREGATION)) {
                lRelation.add(comps.get(i));
            }
        }

        for (int i = 0; i < lClass.size(); i++) {
            Class a = (Class) lClass.get(i);
            ClassDAO dao = new ClassDAO();
            dao.create(a);
        }
        for (int i = 0; i < lRelation.size(); i++) {
            Relation a = (Relation) lRelation.get(i);
            RelationDAO dao = new RelationDAO();
            System.out.println(a.toString());
            dao.create(a);
        }


    }

    public String toString() {
        String res = "";
        try (Transaction tx = graphDb.beginTx();
             //Result result = tx.execute( "MATCH (a)-(n:Relation)-(b) Return type(n), a, b"))
             Result result = tx.execute("MATCH (a)-[n:Relation]->(b) Return n,a,b")) {
            while (result.hasNext()) {
                Map<String, Object> row = result.next();
                for (Map.Entry<String, Object> column : row.entrySet()) {
                    res += column.getKey() + " : " + column.getValue() + ";";
                }
                res += "\n";
            }
        }
        return res;
    }


        /*public void test () {

        graphDb.beginTx();
        Node Class = graphDb.createNode(Label.label("Class"));
        Class.setProperty("Name", "Adapter");

        Node Class2 = graphDb.createNode(Label.label("Class"));
        Class2.setProperty("Name", "Legacy");

        Class.createRelationshipTo(Class2, RelationshipType.withName("contains"));

        deleteGraphDb();
        //System.out.println(graphDb.schema().toString());
    }*/
}

