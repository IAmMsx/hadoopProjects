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

import java.io.*;
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
    @Test
    public void test() throws IOException {
        ArrayList<Instance> testSet = new ArrayList<>();
        ArrayList<Instance> testSet2 = new ArrayList<>();


        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/KNN/test500_Each.txt"));
        String line ;
        while (StringUtils.isNotEmpty(line = bufferedReader.readLine())){
            Instance testInstance = new Instance(line);
            testSet.add(testInstance);
            testSet2.add(testInstance);
        }
        for (int i = 0; i < testSet.size(); i++) {
            System.out.print(testSet.get(i)+"\t");
            System.out.println(testSet.get(i).compareTo(testSet2.get(i)));
        }

        IOUtils.closeStream(bufferedReader);
    }
}
