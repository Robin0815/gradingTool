package Control.Strategy.ClassStrategy.Checker;

import Model.UMLComponent;
import java.util.List;

public class SyntaxChecker implements Checker{
    @Override
    public boolean checkUML(List<UMLComponent> comps) {

        return true;
    }
}
