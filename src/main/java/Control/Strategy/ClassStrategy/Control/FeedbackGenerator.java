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


    private String res = "Automatisierte Bewertung von UMLKlassendiagrammen:\n ------------------------------------------------";

    public String getRes() {
        return res;
    }
}
