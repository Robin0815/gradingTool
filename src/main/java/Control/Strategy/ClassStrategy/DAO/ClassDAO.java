package Control.Strategy.ClassStrategy.DAO;

import Control.Strategy.ClassStrategy.Control.GraphDBFunc;
import Model.Attribut;
import Model.Class;
import Model.Constructor;
import Model.Method;
import org.neo4j.dbms.DatabaseManagementSystemSettings;
import org.neo4j.graphdb.GraphDatabaseService;

public class ClassDAO {
    private GraphDatabaseService graphDB = GraphDBFunc.getInstance().getGraphDb();
    public void create(Class a){

    }
    public Class read(){

        return null;
    }
    public void update(Class a){

    }
    public Class delete(Class a){

        return null;
    }

    //-----Attribut erzeugung
    private void createAttrib(Attribut a){

    }
    private Attribut deleteAttrib(Attribut a){

        return null;
    }
    private Attribut readAttrib(){

        return null;
    }
    //-----Method erzeugung
    private void createMethod(Method a){

    }
    private Method deleteMethod(Method a){

        return null;
    }
    private Method readMethod(){

        return null;
    }
    //-----Constructor erzeugung
    private void createConstructor(Constructor a){

    }
    private Constructor deleteConstructor(Constructor a){

        return null;
    }
    private Constructor readConstructor(){

        return null;
    }
}
