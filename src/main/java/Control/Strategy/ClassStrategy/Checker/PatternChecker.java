package Control.Strategy.ClassStrategy.Checker;

import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Control.Strategy.ClassStrategy.Control.PatternQueries;
import Model.UMLComponent;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.util.List;
import java.util.Map;

public class PatternChecker implements Checker{
    @Override
    public boolean checkUML(List<UMLComponent> comps) {
        GraphDBFunction func = GraphDBFunction.getInstance();
        func.setUp(comps);
        GraphDatabaseService graphDb = func.getGraphDb();

        String res = "\n";
        try (Transaction tx = graphDb.beginTx();
             Result result = tx.execute(PatternQueries.adapterQuery2())) {
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
        System.out.println(res);


        return true;
    }
}
