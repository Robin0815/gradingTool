package Control.Strategy.ClassStrategy.DAO;

import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Model.Class;
import Model.Elements;
import Model.Relation;
import org.neo4j.graphdb.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationDAO {
    private GraphDatabaseService graphDb = GraphDBFunction.getInstance().getGraphDb();

    public void create(Relation a) {
        Result result = null;
        Class c;
        c = (Class) a.getStart();
        String start = c.getName();
        c = (Class) a.getEnd();
        String end = c.getName();
        try {
            Transaction tx = graphDb.beginTx();
            //Result result = tx.execute( "MATCH (a)-[n]->(b) Return type(n), a, b")){
            result = tx.execute("MATCH (n:Class) Return n UNION MATCH (n:Interface) Return n UNION MATCH (n:Abstract) Return n");
        } catch (QueryExecutionException exc) {
            exc.printStackTrace();
        }
        assert result != null;
        List<Node> nodeList = new ArrayList<>();
        try (Transaction tx = graphDb.beginTx()) {
            while (result.hasNext()) {
                Map<String, Object> row = result.next();
                for (Map.Entry<String, Object> column : row.entrySet()) {
                    Node node = (Node) column.getValue();
                    nodeList.add(node);
                }
            }
            Node nodeStart = null;
            Node nodeEnd = null;
            for (Node node : nodeList) {
                if (node.getProperty("Name").equals(start)) {
                    nodeStart = node;
                }
                if (node.getProperty("Name").equals(end)) {
                    nodeEnd = node;
                }
            }
            assert nodeStart != null && nodeEnd != null;
            if (a.id().equals(Elements.ASSOCIATION2)) {
                nodeStart.createRelationshipTo(nodeEnd, RelationshipType.withName("Association"));
            }
            if (a.id().equals(Elements.DEPENDENCY)) {
                nodeStart.createRelationshipTo(nodeEnd, RelationshipType.withName("Dependency"));
            }
            if (a.id().equals(Elements.IMPLEMENTS)) {
                nodeStart.createRelationshipTo(nodeEnd, RelationshipType.withName("Implements"));
            }
            if (a.id().equals(Elements.AGGREGATION)) {
                nodeStart.createRelationshipTo(nodeEnd, RelationshipType.withName("Aggregation"));
            }
            if (a.id().equals(Elements.COMPOSITION)) {
                nodeStart.createRelationshipTo(nodeEnd, RelationshipType.withName("Composition"));
            }
            if (a.id().equals(Elements.INHERITANCE)) {
                nodeStart.createRelationshipTo(nodeEnd, RelationshipType.withName("Inheritance"));
            }



/*
        try (Transaction tx = graphDb.beginTx()) {

            Class c;
            c= (Class) a.getStart();
            String s= c.getName();
            c= (Class) a.getEnd();
            String e=c.getName();
            System.out.println(a.id());
            Map<String, Object> params= new HashMap<>();
            params.put("start", s);
            params.put("end", e);
            String res = "MATCH (a), (b) WHERE a.Name = $start AND b.Name = $end CREATE (a)-[r:Relation]->(b)";
            tx.execute(res, params);

        }*/


        }

    }
}
