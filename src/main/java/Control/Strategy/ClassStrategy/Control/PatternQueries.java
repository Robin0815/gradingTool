package Control.Strategy.ClassStrategy.Control;

import java.util.ArrayList;
import java.util.List;

public class PatternQueries {
    public static String adapterQuerry(){
        return "Match (a:Class)-[:Association]->(b:Interface)" +
                "Match (c:Class)-[:Implements]->(b:Interface)" +
                "Match (c:Class)-[:Association]->(d:Class)" +
                "Return count(*)";
    }
}
