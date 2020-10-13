package Control.Strategy.ClassStrategy.Control;

import java.util.ArrayList;
import java.util.List;

public class PatternQueries {
    public static List<String> allQuery() {
        List<String> res = new ArrayList<>();
        res.add(adapterQuery1());
        res.add(adapterQuery2());
        res.add(singletonQuery1());
        res.add(singletonQuery2());
        res.add(strategyQuery1());
        res.add(strategyQuery2());
        return res;
    }

    public static String adapterQuery2() {
        return "Match (a:Class)-[:Association|:Dependency]->(b:Interface)" +
                "Match (c:Class)-[:Implements]->(b:Interface)" +
                "Match (c:Class)-[:Association]->(d:Class)" +
                "Match (b:Interface)-[:contains]->(m:Method)" +
                "Match (c:Class)-[:contains]->(x:Method)" +
                "Match (c:Class)-[:contains]->(q:Method)" +
                "Match (c:Class)-[:contains]->(y:Method)" +
                "Match (d:Class)-[:contains]->(z:Method)" +
                "Where m.OutPut = x.OutPut and m.InPut = x.InPut and y.OutPut = z.InPut and q.InPut = z.OutPut" +
                " " +
                "Return count(*) as AnzAdapter2";
        //"Return y.OutPut, z.InPut";
    }

    public static String adapterQuery1() {
        return "Match (a:Class)-[:Association|:Dependency]->(b:Interface)" +
                "Match (c:Class)-[:Implements]->(b:Interface)" +
                "Match (c:Class)-[:Association]->(d:Class)" +
                "Return count(*) as AnzAdapter1";
        //"Return y.OutPut, z.InPut";
    }

    public static String singletonQuery2() {
        return "Match (c:Class)-[:contains]->(x:Constructor)" +
                "Match (c:Class)-[:contains]->(b:Method)" +
                "Match (c:Class)-[:contains]->(d:Attribute)" +
                "Where x.Visibility = '-' and b.OutPut = c.Name and b.Static = 'true' and d.OutPut = c.Name and " +
                "d.Static = 'true'" +
                " " +
                "Return count(*) as AnzSingleton2";
    }

    public static String singletonQuery1() {
        return "Match (c:Class)-[:contains]->(x:Constructor)" +
                "Match (c:Class)-[:contains]->(b:Method)" +
                "Match (c:Class)-[:contains]->(d:Attribute)" +
                "Where x.Visibility = '-' and b.Static = 'true'  and d.Static = 'true'" +
                " " +
                "Return count(*) as AnzSingleton1";

    }

    public static String strategyQuery2() {
        return "Match (client:Class)-[:Association]->(strat:Interface)" +
                "Match (client:Class)-[:Association]->(kont:Class)" +
               
                "Match (rstart1:Class)-[:Implements]->(strat:Interface)" +
                "Match (rstart2:Class)-[:Implements]->(strat:Interface)" +
                "Return count(*) as AnzStrategy2";
    }

    public static String strategyQuery1() {
        return "Match (client:Class)-[:Association]->(strat:Interface)" +
                "Match (client:Class)-[:Association]->(kont:Class)" +
                "Match (kont:Class)-[:Aggregation]->(strat:Interface)" +
                "Match (rstart1:Class)-[:Implements]->(strat:Interface)" +
                "Return count(*) as AnzStrategy1";
    }
}
