package Control.Strategy.ClassStrategy.DAO;

import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Model.Class;
import Model.Relation;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

public class RelationDAO {
    private GraphDatabaseService graphDb = GraphDBFunction.getInstance().getGraphDb();
    public void create(Relation a){
        try (Transaction tx = graphDb.beginTx()) {
            Class c;
            c= (Class) a.getStart();
            String s= c.getName();
            c= (Class) a.getEnd();
            String e=c.getName();
            tx.execute("MATCH (a {Name:'"+s+"'}), (b {Name:'"+e+"'})\n" +
                    "CREATE (a)-[:Relation]->(b)");

        }


    }

}
