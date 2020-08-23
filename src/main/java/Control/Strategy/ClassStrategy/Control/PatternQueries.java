package Control.Strategy.ClassStrategy.Control;

import java.util.ArrayList;
import java.util.List;

public class PatternQueries {

    public static String adapterQuerry2(){
        return "Match (a:Class)-[:Association]->(b:Interface)" +
                "Match (c:Class)-[:Implements]->(b:Interface)" +
                "Match (c:Class)-[:Association]->(d:Class)" +
                "Match (b:Interface)-[:contains]->(m:Method)" +
                "Match (c:Class)-[:contains]->(x:Method)" +
                "Match (c:Class)-[:contains]->(q:Method)" +
                "Match (c:Class)-[:contains]->(y:Method)" +
                "Match (d:Class)-[:contains]->(z:Method)" +
                "Where m.OutPut = x.OutPut and m.InPut = x.InPut and y.OutPut = z.InPut and q.InPut = z.OutPut" +
                " " +
                "Return count(*) as AnzAdapter";
                //"Return y.OutPut, z.InPut";
    }
    public static String adapterQuerry(){
        return "Match (a:Class)-[:Association]->(b:Interface)" +
                "Match (c:Class)-[:Implements]->(b:Interface)" +
                "Match (c:Class)-[:Association]->(d:Class)" +
                "Return count(*) as AnzAdapter";
        //"Return y.OutPut, z.InPut";
    }

}
