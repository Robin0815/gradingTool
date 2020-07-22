/*
@author mkowol2s
 */

package Model;

public class Note implements UMLComponent{
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String toString() {
        return "Note{" +
                "text='" + text + '\'' +
                '}';
    }
}
