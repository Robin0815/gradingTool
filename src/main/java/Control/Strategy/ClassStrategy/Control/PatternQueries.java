package Control.Strategy.ClassStrategy.Control;

public class PatternQueries {

    public static String adapterQuery2(){
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
                "Return count(*) as AnzAdapter2";
                //"Return y.OutPut, z.InPut";
    }
    public static String adapterQuery1(){
        return "Match (a:Class)-[:Association]->(b:Interface)" +
                "Match (c:Class)-[:Implements]->(b:Interface)" +
                "Match (c:Class)-[:Association]->(d:Class)" +
                "Return count(*) as AnzAdapter1";
        //"Return y.OutPut, z.InPut";
    }

    public static String singletonQuery2(){
        return "Match" +
                "Return count(*) as AnzSingleton2";
    }
    public static String singletonQuery1(){
        return "Match" +
                "Return count(*) as AnzSingleton1";

    }
    public static String strategyQuery2(){
        return "Match" +
                "Return count(*) as AnzStrategy2";
    }
    public static String strategyQuery1(){
        return "Match" +
                "Return count(*) as AnzStrategy1";
    }
}
