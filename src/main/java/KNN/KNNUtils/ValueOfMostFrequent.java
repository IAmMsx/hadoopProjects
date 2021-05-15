package KNN.KNNUtils;

import KNN.Model.DistanceAndLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValueOfMostFrequent {
    // 找到出现次数最多的样例
    public static Double valueOfMostFrequent(ArrayList<DistanceAndLabel> lists) throws Exception {
        if (lists.isEmpty()) {
            throw new Exception("List is empty!");
        } else {
            HashMap<Double, Integer> tmp = new HashMap<>();
            for (DistanceAndLabel list : lists) {
                double label = list.getLabel();
                if (tmp.containsKey(label)) {
                    Integer oldValue = tmp.get(label);
                    tmp.replace(label, oldValue, oldValue + 1);
                } else {
                    tmp.put(label, 1);
                }
            }
            Double value = 0.0;
            Integer frequence = 0;
            for (Map.Entry<Double, Integer> entry : tmp.entrySet()) {
                if (entry.getValue() > frequence) {
                    frequence = entry.getValue();
                    value = entry.getKey();
                }
            }
            return value;
        }

    }
}
