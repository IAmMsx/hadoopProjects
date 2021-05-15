package KNN.Control;

import KNN.KNNUtils.Distance;
import KNN.Model.DistanceAndLabel;
import KNN.Model.Instance;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class KNNMapper extends Mapper<LongWritable, Text, Instance, DistanceAndLabel> {
    private ArrayList<Instance> testSet = new ArrayList<>();

    private DistanceAndLabel outV;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 将测试集封装为Instance对象，放入testSet中
        URI[] cacheFiles = context.getCacheFiles();
        FileSystem fs = FileSystem.get(context.getConfiguration());
        FSDataInputStream fis = fs.open(new Path(cacheFiles[0]));

        // 从流中读对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));

        String line;
        while (StringUtils.isNotEmpty(line = reader.readLine())) {
            Instance testInstance = new Instance(line);
            testSet.add(testInstance);
        }

        IOUtils.closeStream(fis);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Instance trainInstance = new Instance(value.toString());
        double trainLabel = trainInstance.getLabel();
        for (Instance testInstance : testSet) {
            double distance = 0.0;
            try {
                distance = Distance.EuclideanDistance(testInstance.getAttributeValues(), trainInstance.getAttributeValues());
            } catch (Exception e) {
                e.printStackTrace();
            }
            outV = new DistanceAndLabel(distance, trainLabel);
            context.write(testInstance, outV);
        }
    }
}
