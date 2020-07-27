/*
@author mkowol2s
 */

package Control.Strategy.UseCaseStrategy.Control;

import Control.Strategy.UseCaseStrategy.DTO.ReducedRelation;
import Control.Strategy.UseCaseStrategy.DTO.Solution;
import Model.Elements;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.inference.TTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ComparingEngine {

    private final TTest tester = new TTest();
    private int passedSubmissions = 0;
    private int undecidedSubmissions = 0;
    private int failedSubmissions = 0;

    public void compareSolutions(Solution tutorsolution, Solution studentsolution, double alpha, double delta){
        double konfElements = getSimilarityNumberOfElements(tutorsolution.getNumberOfElements(),studentsolution.getNumberOfElements());
        double konfRelations = getSimilarityRelations(tutorsolution.getReducedRelationIntegerMap(),studentsolution.getReducedRelationIntegerMap());
        createFeedback(konfElements,konfRelations,alpha,delta);
    }

    private double getSimilarityNumberOfElements(HashMap<Elements, Integer> map1, HashMap<Elements, Integer> map2){
        ArrayList<Double> sampleList1 = new ArrayList<>();
        ArrayList<Double> sampleList2 = new ArrayList<>();

        for(Entry<Elements, Integer> entry: map1.entrySet()) {
            sampleList1.add((double) entry.getValue());
            if (map2.get(entry.getKey()) == null){
                sampleList2.add(0.0);
            }else {
                sampleList2.add((double) map2.get(entry.getKey()));
            }
        }
        for(Entry<Elements, Integer> entry: map2.entrySet()) {
            if( !map1.containsKey(entry.getKey())) {
                sampleList2.add((double) entry.getValue());
                sampleList1.add(0.0);
            }
        }
        double[] sample1 = ArrayUtils.toPrimitive(sampleList1.toArray(new Double[0]));
        double[] sample2 = ArrayUtils.toPrimitive(sampleList2.toArray(new Double[0]));
        return tester.tTest(sample1, sample2);
    }

    private double getSimilarityRelations(Map<ReducedRelation, Integer> map1, Map<ReducedRelation, Integer> map2){
        ArrayList<Double> sampleList1 = new ArrayList<>();
        ArrayList<Double> sampleList2 = new ArrayList<>();

        for(Entry<ReducedRelation, Integer> entry: map1.entrySet()) {
            sampleList1.add((double) entry.getValue());
            if (map2.get(entry.getKey()) == null) {
                sampleList2.add(0.0);
            }else {
                sampleList2.add((double) map2.get(entry.getKey()));
            }
        }
        for(Entry<ReducedRelation, Integer> entry: map2.entrySet()) {
            if(!map1.containsKey(entry.getKey())) {
                sampleList2.add((double) entry.getValue());
                sampleList1.add(0.0);
            }
        }
        double[] sample1 = ArrayUtils.toPrimitive(sampleList1.toArray(new Double[0]));
        double[] sample2 = ArrayUtils.toPrimitive(sampleList2.toArray(new Double[0]));
        return tester.tTest(sample1, sample2);

    }

    private void createFeedback(double konfElements, double konfRelations, double alpha, double delta){
        if (konfElements < alpha || konfRelations < alpha) {
            //return failed
            failedSubmissions++;
            System.out.println("FAILED");
        } else if (konfElements < delta || konfRelations < delta) {
            //return undecided
            undecidedSubmissions++;
            System.out.println("UNDECIDED");
        } else {
            //return passed
            passedSubmissions++;
            System.out.println("PASSED");
        }
    }


    public ArrayList<Long> returnResults(){
        ArrayList<Long> results = new ArrayList<>();
        results.add(Math.round((double) failedSubmissions/(failedSubmissions+undecidedSubmissions+passedSubmissions)*100));
        results.add(Math.round((double) undecidedSubmissions/(failedSubmissions+undecidedSubmissions+passedSubmissions)*100));
        results.add(Math.round((double) passedSubmissions/(failedSubmissions+undecidedSubmissions+passedSubmissions)*100));
        return results;
    }
}
