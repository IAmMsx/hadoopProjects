package KNN.KNNUtils;

import KNN.Model.DistanceAndLabel;

import java.util.ArrayList;

public class Sort {
    // 获取距离最小的K个对象
    public static ArrayList<DistanceAndLabel> getNearst(ArrayList<DistanceAndLabel> list, int k) {
        ArrayList<DistanceAndLabel> result = new ArrayList<>();
        for (DistanceAndLabel distanceAndLabel : list) {
            if (result.size() < k) {
                result.add(distanceAndLabel);
            } else {
                int maxIndex = indexOfMax(result);
                if (distanceAndLabel.getDistance() < result.get(maxIndex).getDistance()) {
                    result.set(maxIndex, distanceAndLabel);
                }
            }
        }
        return result;
    }

    public static int indexOfMax(ArrayList<DistanceAndLabel> list) {
//        返回集合最大值的索引
        int maxIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDistance() > list.get(maxIndex).getDistance()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
