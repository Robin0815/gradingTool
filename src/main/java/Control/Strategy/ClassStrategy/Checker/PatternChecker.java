package Control.Strategy.ClassStrategy.Checker;

import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Control.Strategy.ClassStrategy.Control.PatternQueries;
import Model.UMLComponent;
import org.neo4j.cypher.internal.expressions.In;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternChecker implements Checker{
    Map<String, Integer> pMap;

    @Override
    public boolean checkUML(List<UMLComponent> comps) {
        GraphDatabaseService graphDb = crateData(comps);
        String dbRes = runQuery(graphDb);
        System.out.println(dbRes);
        return true;
    }
    private Map<String, Integer> usedPattern(){
        Map<String, Integer> res = new HashMap<>();
        res.put("Adapter",1);
        res.put("Singleton",0);
        res.put("Strategy", 0);
        return res;
    }
    private GraphDatabaseService crateData(List<UMLComponent> comps){
        GraphDBFunction func = GraphDBFunction.getInstance();
        func.setUp(comps);
        return func.getGraphDb();
    }
    private String runQuery(GraphDatabaseService graphDb){
        String res = "";
        for (String query : PatternQueries.allQuery())
        try (Transaction tx = graphDb.beginTx();
             Result result = tx.execute(query)) {
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

}

/*    public boolean checkUML(List<UMLComponent> comps) {
        GraphDBFunction func = GraphDBFunction.getInstance();
        func.setUp(comps);
        GraphDatabaseService graphDb = func.getGraphDb();

        String res = "\n";
        try (Transaction tx = graphDb.beginTx();
             Result result = tx.execute(PatternQueries.adapterQuery2())) {
            while (result.hasNext()) {
                Map<String, Object> row = result.next();
                for (Map.Entry<String, Object> column : row.entrySet()) {
                    Node nod = (Node) column.getValue();
                    res += column.getKey() + " : " + nod.getProperty("Name") + ";";
                    res += column.getKey() + " : " + column.getValue() + ";";
                            }
                            res += "\n";
                            }
                            }
                            System.out.println(res);


                            return true;
                            }*/
