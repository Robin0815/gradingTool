package Control.Strategy;

import Model.UMLComponent;

import java.util.List;

public interface Strategy {
    public void analyzeUML(List<UMLComponent> comps);
}
