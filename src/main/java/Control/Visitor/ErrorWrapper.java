package Control.Visitor;

public class ErrorWrapper {

    private Integer sucesses = 0;
    private Integer fails = 0;

    public Integer getFails() {
        return fails;
    }

    public void setFails(Integer fails) {
        this.fails = fails;
    }

    public void incrementFails() {
        this.fails++;
    }

    public Integer getSucesses() {
        return sucesses;
    }

    public void setSucesses(Integer sucesses) {
        this.sucesses = sucesses;
    }

    public void incrementSucesses() {
        this.sucesses++;
    }

}
