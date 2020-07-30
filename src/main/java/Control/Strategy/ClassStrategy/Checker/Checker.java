package Control.Strategy.ClassStrategy.Checker;

import Model.UMLComponent;

import java.util.List;

public interface Checker {
    String checkUML(List<UMLComponent> comps);
}
