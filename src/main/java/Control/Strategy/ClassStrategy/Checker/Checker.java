package Control.Strategy.ClassStrategy.Checker;

import Model.UMLComponent;

import java.util.List;

public interface Checker {
    boolean checkUML(List<UMLComponent> comps);
}
