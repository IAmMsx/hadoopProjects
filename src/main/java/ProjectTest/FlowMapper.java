package ProjectTest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {


    private FlowBean outV = new FlowBean();
    private Text outK = new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 读入一行
        String line = value.toString();
        // 切割
        String[] split = line.split("\t");

        outK.set(split[1]);
        outV.setUpFlow(Long.parseLong(split[split.length - 3]));
        outV.setDownFlow(Long.parseLong(split[split.length - 2]));
        outV.setSumFlow();

        context.write(outK, outV);

    }
}
