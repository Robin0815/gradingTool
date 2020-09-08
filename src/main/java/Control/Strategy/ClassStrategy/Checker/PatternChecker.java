package Control.Strategy.ClassStrategy.Checker;

import Control.Strategy.ClassStrategy.Control.FeedbackGenerator;
import Control.Strategy.ClassStrategy.Control.GraphDBFunction;
import Control.Strategy.ClassStrategy.Control.PatternQueries;
import Model.UMLComponent;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternChecker implements Checker {
    private boolean passed = false;
    private final Map<String, Integer> usedPattern;

    public PatternChecker(int adap, int sing, int stra) {
        usedPattern = usedPatternMethod(adap, sing, stra);
    }

    @Override
    public boolean checkUML(List<UMLComponent> comps) {
        GraphDatabaseService graphDb = crateData(comps);
        String dbRes = runQuery(graphDb);
        String res = evaluateQuery(dbRes);
        FeedbackGenerator.getInstance().addRes(res);
        GraphDBFunction.getInstance().shutdown();
        return passed;
    }

    private String evaluateQuery(String dbRes) {
        String res = "--------------------------------------------------\nAuswertung der Pattern Compliance:\n--------------------------------------------------\n";
        String[] queryResultPerLine = dbRes.split("\n");
        boolean adapterOK = false;
        boolean singletonOK = false;
        boolean strategyOK = false;
        int adapter = 0;
        int singleton = 0;
        int strategy = 0;
        for (String line : queryResultPerLine) {
            int complianceLevel = 1;
            if (line.contains("AnzAdapter" + complianceLevel)) {
                adapter = getAnzPattern(line);
                adapterOK = usedPattern.get("Adapter").equals(adapter);
            }
            if (line.contains("AnzSingleton" + complianceLevel)) {
                singleton = getAnzPattern(line);
                singletonOK = usedPattern.get("Singleton").equals(singleton);
            }
            if (line.contains("AnzStrategy" + complianceLevel)) {
                strategy = getAnzPattern(line);
                strategyOK = usedPattern.get("Strategy").equals(strategy);
            }
        }
        if (!adapterOK) {
            res += "Fehler beim Adapter Pattern gefunden, es sind : " + adapter + " gefunden worden und : " + usedPattern.get("Adapter") + " verlangt\n";
        }
        if (adapterOK) {
            res += "Es sind : " + adapter + " korrekte Adapter Pattern gefunden worden und : " + usedPattern.get("Adapter") + " verlangt worden\n";
        }
        if (!singletonOK) {
            res += "Fehler beim Singleton Pattern gefunden, es sind : " + singleton + " gefunden worden und : " + usedPattern.get("Singleton") + " verlangt\n";
        }
        if (singletonOK) {
            res += "Es sind : " + singleton + " korrekte Singleton Pattern gefunden worden und : " + usedPattern.get("Singleton") + " verlangt worden\n";
        }
        if (!strategyOK) {
            res += "Fehler beim Strategy Pattern gefunden, es sind : " + strategy + " gefunden worden und : " + usedPattern.get("Strategy") + " verlangt\n";
        }
        if (strategyOK) {
            res += "Es sind: " + strategy + " korrekte Strategy Pattern gefunden worden und : " + usedPattern.get("Strategy") + " verlangt worden\n";
        }
        passed = adapterOK & singletonOK & strategyOK;
        if (passed) {
            res += "Keine Fehler gefunden\n--------------------------------------------------\nPattern Test erfolgreich abgeschlossen\n--------------------------------------------------\n";
        }
        if (!passed) {
            res += "\n--------------------------------------------------\nPattern Test fehlgeschlagen\n--------------------------------------------------\n";
        }
        return res;
    }

    private int getAnzPattern(String line) {
        return Integer.parseInt(line.substring(line.indexOf(':') + 2, line.indexOf(';')));
    }

    private Map<String, Integer> usedPatternMethod(int adap, int sing, int stra) {
        Map<String, Integer> res = new HashMap<>();
        res.put("Adapter", adap);
        res.put("Singleton", sing);
        res.put("Strategy", stra);
        return res;
    }

    private GraphDatabaseService crateData(List<UMLComponent> comps) {
        GraphDBFunction func = GraphDBFunction.getInstance();
        func.setUp(comps);
        return func.getGraphDb();
    }

    private String runQuery(GraphDatabaseService graphDb) {
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
