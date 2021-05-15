package KNN.Control;

import KNN.KNNUtils.Sort;
import KNN.Model.DistanceAndLabel;
import KNN.Model.Instance;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class KNNCombiner extends Reducer<Instance, DistanceAndLabel, Instance, DistanceAndLabel> {
    private int k;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        k = context.getConfiguration().getInt("K", 1);
    }

    @Override
    protected void reduce(Instance key, Iterable<DistanceAndLabel> values, Context context) throws IOException, InterruptedException {
        // 取出K个最近邻
        ArrayList<DistanceAndLabel> lists = new ArrayList<>();
        for (DistanceAndLabel value : values) {
            DistanceAndLabel tempDL = new DistanceAndLabel(value.getDistance(), value.getLabel());
            lists.add(tempDL);
        }
        lists = Sort.getNearst(lists, k);
        for (DistanceAndLabel list : lists) {
            context.write(key, list);
        }
    }
}
