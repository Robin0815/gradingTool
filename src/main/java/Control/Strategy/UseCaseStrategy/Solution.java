package Control.Strategy.UseCaseStrategy;

import Model.Elements;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private HashMap<Elements, Integer> numberOfElements = new HashMap<>();
    private Map<ReducedRelation, Integer> reducedRelationIntegerMap = new HashMap<>();

    public Map<ReducedRelation, Integer> getReducedRelationIntegerMap() {
        return reducedRelationIntegerMap;
    }

    public void setReducedRelationIntegerMap(Map<ReducedRelation, Integer> reducedRelationIntegerMap) {
        this.reducedRelationIntegerMap = reducedRelationIntegerMap;
    }

    public HashMap<Elements, Integer> getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(HashMap<Elements, Integer> numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "numberOfElements=" + numberOfElements +
                ", reducedRelationIntegerMap=" + reducedRelationIntegerMap +
                '}';
    }
}
