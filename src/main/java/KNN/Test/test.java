package KNN.Test;

import KNN.KNNUtils.Distance;
import KNN.KNNUtils.Sort;
import KNN.KNNUtils.ValueOfMostFrequent;
import KNN.Model.DistanceAndLabel;
import KNN.Model.Instance;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class test {

    private ArrayList<DistanceAndLabel> createList() {
        ArrayList<DistanceAndLabel> testArr = new ArrayList<>();
        testArr.add(new DistanceAndLabel(5.0,1.0));
        testArr.add(new DistanceAndLabel(2.0,1.0));
        testArr.add(new DistanceAndLabel(3.0,1.0));
        testArr.add(new DistanceAndLabel(4.0,1.0));
        testArr.add(new DistanceAndLabel(4.0,1.0));
        testArr.add(new DistanceAndLabel(10.0,5.0));
        testArr.add(new DistanceAndLabel(1.0,5.0));
        testArr.add(new DistanceAndLabel(99.0,5.0));
        testArr.add(new DistanceAndLabel(16.0,5.0));
        return testArr;
    }

    @Test
    public void distanceTest(){
        double[] a1 = {3.0,4.0};
        double[] a2 = {0.0,0.0};
        try {
            double v = Distance.EuclideanDistance(a1, a2);
            System.out.println(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SortTest(){
        ArrayList<DistanceAndLabel> testArr = createList();
        System.out.println(Sort.getNearst(testArr,3));
        System.out.println(Sort.indexOfMax(testArr));

    }

    @Test
    public void ValueOfMostTest(){
        ArrayList<DistanceAndLabel> testArr = createList();
        try {
            System.out.println(ValueOfMostFrequent.valueOfMostFrequent(testArr));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void InstanceToStringTest() {
        double[] tempDouble = {1.0, 2.0, 3.0, 4.0, 5.0};
        Instance instance = new Instance(tempDouble, 1.0);
        System.out.println(instance);
    }
}
