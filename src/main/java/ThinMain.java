import Control.Parser;

public class ThinMain {

    public static void main(String[] args) {
        //System.out.println("Start");

        String file = "C:\\Users\\Robin\\Google Drive\\Uni\\Bachelor\\Adapter-UML.uxf";
        Parser a = new Parser();
        a.parseFile(file);
    }
}