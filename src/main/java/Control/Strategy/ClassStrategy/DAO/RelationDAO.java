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
        try (Transaction tx = graphDb.beginTx()) {
            Class c;
            c = (Class) a.getStart();
            String s = c.getName();
            c = (Class) a.getEnd();
            String e = c.getName();
            //System.out.println(a.id());
            Map<String, Object> params = new HashMap<>();
            params.put("start", s);
            params.put("end", e);
            String res = null;
            if (a.id().equals(Elements.ASSOCIATION2)) {
                res = "MATCH (a {Name: $start}), (b {Name: $end}) CREATE (a)-[r:Association]->(b)";
            }
            if (a.id().equals(Elements.DEPENDENCY)) {
                res = "MATCH (a {Name: $start}), (b {Name: $end}) CREATE (a)-[r:Dependency]->(b)";
            }
            if (a.id().equals(Elements.IMPLEMENTS)) {
                res = "MATCH (a {Name: $start}), (b {Name: $end}) CREATE (a)-[r:Implements]->(b)";
            }
            if (a.id().equals(Elements.AGGREGATION)) {
                res = "MATCH (a {Name: $start}), (b {Name: $end}) CREATE (a)-[r:Aggregation]->(b)";
            }
            if (a.id().equals(Elements.COMPOSITION)) {
                res = "MATCH (a {Name: $start}), (b {Name: $end}) CREATE (a)-[r:Composition]->(b)";
            }
            if (a.id().equals(Elements.INHERITANCE)) {
                res = "MATCH (a {Name: $start}), (b {Name: $end}) CREATE (a)-[r:Inheritance]->(b)";
            }
            tx.execute(res, params);
            tx.commit();
        }
    }

    public List<Node> getNode() {
        Result result = null;
        try {
            Transaction tx = graphDb.beginTx();
            //Result result = tx.execute( "MATCH (a)-[n]->(b) Return type(n), a, b")){
            result = tx.execute("MATCH (n:Class) Return n UNION MATCH (n:Interface) Return n UNION MATCH (n:Abstract) Return n");
        } catch (QueryExecutionException exc) {
            exc.printStackTrace();
        }
        assert result != null;
        List<Node> nodeList = new ArrayList<>();
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            for (Map.Entry<String, Object> column : row.entrySet()) {
                Node node = (Node) column.getValue();
                nodeList.add(node);
            }
        }
        return nodeList;
    }
}
