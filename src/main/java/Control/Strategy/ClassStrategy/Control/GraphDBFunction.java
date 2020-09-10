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
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.neo4j.graphdb.factory.GraphDatabaseFactory;

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

    public void shutdown() {
        managementService.shutdown();

        try {
            FileUtils.deleteDirectory(new File("data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            if (comps.get(i).id().equals(Elements.RELATION) || comps.get(i).id().equals(Elements.DEPENDENCY)
                    || comps.get(i).id().equals(Elements.ASSOCIATION2) || comps.get(i).id().equals(Elements.IMPLEMENTS)
                    || comps.get(i).id().equals(Elements.INHERITANCE) || comps.get(i).id().equals(Elements.COMPOSITION)
                    || comps.get(i).id().equals(Elements.AGGREGATION)) {
                lRelation.add(comps.get(i));
            }
        }
        for (int i = 0; i < lClass.size(); i++) {
            Class a = (Class) lClass.get(i);
            ClassDAO dao = new ClassDAO();
            dao.create(a);
        }
        RelationDAO relDAO = new RelationDAO();
        for (int i = 0; i < lRelation.size(); i++) {
            Relation a = (Relation) lRelation.get(i);
            relDAO.create(a);
        }
    }

    public String toString() {
        String res = "";
        try (Transaction tx = graphDb.beginTx();
             //Result result = tx.execute( "MATCH (a)-[n]->(b) Return type(n), a, b")){
             Result result = tx.execute("MATCH (a)-[r]->(b)  Return a.Name, type(r), b.Name, b.OutPut")) {
            while (result.hasNext()) {
                Map<String, Object> row = result.next();
                for (Map.Entry<String, Object> column : row.entrySet()) {
                    /*Node nod = (Node) column.getValue();
                    res += column.getKey() + " : " + nod.getProperty("Name") + ";";*/
                    res += column.getKey() + " : " + column.getValue() + ";";
                }
                res += "\n";
            }
        }
        return res;
    }
}

