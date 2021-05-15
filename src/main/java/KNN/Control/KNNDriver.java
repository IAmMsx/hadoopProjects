package KNN.Control;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class KNNDriver {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJobName("KNN");

        job.addCacheFile(new URI(args[2]));
    }
}
