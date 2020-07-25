package Control.Strategy.UseCaseStrategy;
import Model.Elements;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.inference.TTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class ComparingEngine {

    private static TTest tester = new TTest();

    public static void compareSolutions(Solution tutorsolution,Solution studentsolution, double alpha){
        createSamplesFromMapElements(tutorsolution.getNumberOfElements(),studentsolution.getNumberOfElements(),alpha);
    }

    private static void createSamplesFromMapElements(HashMap<Elements, Integer> map1, HashMap<Elements, Integer> map2, double alpha){
        ArrayList<Double> sampleList1 = new ArrayList<>();
        ArrayList<Double> sampleList2 = new ArrayList<>();

        for(Entry<Elements, Integer> entry: map1.entrySet()) {
            if(entry.getKey() != Elements.TOTALELEMTS){
                sampleList1.add((double) entry.getValue());
                if (map2.get(entry.getKey()) == null){
                    sampleList2.add(0.0);
                }else {
                    sampleList2.add((double) map2.get(entry.getKey()));
                }
            }
        }
        for(Entry<Elements, Integer> entry: map2.entrySet()) {
            if(entry.getKey() != Elements.TOTALELEMTS && !map1.containsKey(entry.getKey())){
                sampleList2.add((double) entry.getValue());
                sampleList1.add(0.0);
            }
        }
        System.out.println(Arrays.toString(sampleList1.toArray()));
        System.out.println(Arrays.toString(sampleList2.toArray()));
        double[] sample1 = ArrayUtils.toPrimitive(sampleList1.toArray(new Double[sampleList1.size()]));
        double[] sample2 = ArrayUtils.toPrimitive(sampleList2.toArray(new Double[sampleList2.size()]));
        System.out.println(tester.tTest(sample1, sample2));
        System.out.println("ALPHA: " + alpha);
        System.out.println("1-ALPHA: "+(1-alpha));
        System.out.println(tester.tTest(sample1, sample2, alpha));
    }
}
