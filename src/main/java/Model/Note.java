/*
@author mkowol2s
 */

package Model;

public class Note extends ConnectableComponent{
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

    @Override
    public Elements id() {
        return Elements.NOTE;
    }
}
