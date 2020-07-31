package Control.Strategy.ClassStrategy.DAO;

import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Model.Class;
import Model.Relation;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.Map;

public class RelationDAO {
    private GraphDatabaseService graphDb = GraphDBFunction.getInstance().getGraphDb();
    public void create(Relation a){
        try (Transaction tx = graphDb.beginTx()) {
            Class c;
            c= (Class) a.getStart();
            String s= c.getName();
            c= (Class) a.getEnd();
            String e=c.getName();
            Map<String, Object> params= new HashMap<>();
            params.put("start", s);
            params.put("end", e);
            String res = "MATCH (a), (b) WHERE a.Name = $start AND b.Name = $end CREATE (a)-[r:Relation]->(b)";
            tx.execute(res, params);

        }


    }

}
