package Control.Strategy.UseCaseStrategy;

public class ErrorWrapper {

    private Integer sucesses = 0;
    private Integer fails = 0;

    public Integer getFails() {
        return fails;
    }

    public void setFails(Integer fails) {
        this.fails = fails;
    }

    public Integer getSucesses() {
        return sucesses;
    }

    public void setSucesses(Integer sucesses) {
        this.sucesses = sucesses;
    }

    public double getPercentage(){
        return Math.round((double) this.fails/(this.fails+this.sucesses)*100);
    }

    @Override
    public String toString() {
        return "ErrorWrapper{" +
                "sucesses=" + sucesses +
                ", fails=" + fails +
                '}';
    }
}
