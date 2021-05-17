package KNN.Control;

import KNN.KNNUtils.Sort;
import KNN.KNNUtils.ValueOfMostFrequent;
import KNN.Model.DistanceAndLabel;
import KNN.Model.Instance;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class KNNReducer extends Reducer<Instance, DistanceAndLabel, Text, NullWritable> {
    private int k;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        k = context.getConfiguration().getInt("K", 1);
    }

    @Override
    protected void reduce(Instance key, Iterable<DistanceAndLabel> values, Context context) throws IOException, InterruptedException {
        ArrayList<DistanceAndLabel> lists = new ArrayList<>();
        for (DistanceAndLabel value : values) {
            DistanceAndLabel tmpDL = new DistanceAndLabel(value.getDistance(), value.getLabel());
            lists.add(tmpDL);
        }

        //
//        lists = Sort.getNearst(lists,k);
        try {
            Double label = ValueOfMostFrequent.valueOfMostFrequent(lists);
            Instance outK = new Instance(key.getAttributeValues(), label);
            context.write(new Text(outK.toString()), NullWritable.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
