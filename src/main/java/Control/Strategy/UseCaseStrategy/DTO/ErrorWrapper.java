/*
@author mkowol2s
 */

package Control.Strategy.UseCaseStrategy.DTO;

public class ErrorWrapper {

    private int sucesses = 0;
    private int fails = 0;

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public int getSucesses() {
        return sucesses;
    }

    public void setSucesses(int sucesses) {
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
