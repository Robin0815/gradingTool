package Control.Strategy.ClassStrategy.Control;


public class FeedbackGenerator {
    private FeedbackGenerator() {

    }

    private static FeedbackGenerator feedbackGenerator;

    public static FeedbackGenerator getInstance() {
        if (feedbackGenerator == null)
            synchronized (FeedbackGenerator.class) {
                if (feedbackGenerator == null) {
                    feedbackGenerator = new FeedbackGenerator();
                }
            }
        return feedbackGenerator;
    }


    private String res = "Bewertung UMLKlassendiagramm:\n--------------------------------------------------\n";

    public String getRes() {
        return res;
    }

    public void addRes(String a) {
        res += a;
    }

    public void reset() {
        res = "Bewertung UMLKlassendiagramm:\n--------------------------------------------------\n";
    }
}
