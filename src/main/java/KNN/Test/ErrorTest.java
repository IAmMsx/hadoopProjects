package KNN.Test;

//import org.apache.hadoop.fs.FileSystem;

import java.io.*;
import java.util.ArrayList;

public class ErrorTest {
    public static void main(String[] args) {
        ArrayList<Double> label = new ArrayList<>();
        BufferedReader bRun = null;
        try {
            File fRun = new File("D:\\javaWork\\hadoopProjects\\src\\main\\java\\KNN\\output1\\part-r-00000");
            bRun = new BufferedReader(new FileReader(fRun));
            String line = "";
            while ((line = bRun.readLine()) != null) {
                String[] split = line.split("\t");
                label.add(Double.parseDouble(split[split.length-1]));
            }
            for (Double aDouble : label) {
                System.out.println(aDouble);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bRun != null) {
                    bRun.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
