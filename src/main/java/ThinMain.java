import Control.Parser;

public class ThinMain {

    public static void main(String[] args) {
        //System.out.println("Start");

        String file = "Adapter-UML.uxf";
        Parser a = new Parser();
        a.parseFile(file);
    }
}