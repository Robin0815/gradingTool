package Control.Strategy.UseCaseStrategy;

import Model.Elements;
import Model.UMLComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<Elements, Integer> numberOfElements = new HashMap<>();
    private List<UMLComponent> relationlist = new ArrayList<>();

    public Map<Elements, Integer> getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Map<Elements, Integer> numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<UMLComponent> getRelationlist() {
        return relationlist;
    }

    public void setRelationlist(List<UMLComponent> relationlist) {
        this.relationlist = relationlist;
    }
}
