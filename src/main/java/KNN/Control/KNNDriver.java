package KNN.Control;

import KNN.Model.DistanceAndLabel;
import KNN.Model.Instance;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * * //1. 获取job
 * * //2. 设置jar包lujing
 * * //3. 关联mapper、reducer
 * * //4. 设置map输出的kv类型
 * * //5.设置最终输出的kv类型
 * * //6.设置输入路径和输出路径
 * * //7.提交job
 **/
public class KNNDriver {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        conf.setInt("K", 4);
        Job job = Job.getInstance(conf);
        job.setJobName("KNN");

//        conf.setInt("K", Integer.parseInt(args[4]));
//        job.addCacheFile(new URI(args[2]));//测试集路径
//        job.addCacheFile(new URI("src/main/java/KNN/testDataSet.txt"));
        job.addCacheFile(new URI("D:\\dataSet\\mnist_dataset_csv\\mnist_test.csv"));

        job.setJarByClass(KNNDriver.class);//2.

        job.setMapperClass(KNNMapper.class);
        job.setCombinerClass(KNNCombiner.class);
        job.setReducerClass(KNNReducer.class);

//        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Instance.class);
        job.setMapOutputValueClass(DistanceAndLabel.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

//        FileInputFormat.setInputPaths(job, new Path(args[1]));//训练集路径
//        FileOutputFormat.setOutputPath(job, new Path(args[3]));//输出路径
//        FileInputFormat.setInputPaths(job, new Path("src/main/java/KNN/trainDataSet.txt"));
//        FileOutputFormat.setOutputPath(job, new Path("src/main/java/KNN/output1"));
        FileInputFormat.setInputPaths(job, new Path("D:\\dataSet\\mnist_dataset_csv\\mnist_train.csv"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/java/KNN/output"));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);


    }
}
