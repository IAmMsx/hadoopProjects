package KNN.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class splitDataSet {
    public static void main(String[] args){
        double ProportionOfTest =0.1;
        //
        BufferedReader bufferedReader = null;
        BufferedWriter bTestout = null;
        BufferedWriter bTrainout = null;
        try {
            File fin = new File("D:\\dataSet\\aitifical\\两类二维高斯分布\\500_each.txt");
            File fTestout = new File("src/main/java/KNN/test500_Each.txt");
            bufferedReader = new BufferedReader(new FileReader(fin));
            bTestout = new BufferedWriter(new FileWriter(fTestout));
            bTrainout = new BufferedWriter(new FileWriter("src/main/java/KNN/train500_Each.txt"));

            //
            String line;
            ArrayList<String> tempArr = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                tempArr.add(line+"\n");
            }
            Collections.shuffle(tempArr);
            for (int i = 0; i < tempArr.size(); i++) {
                if (i < tempArr.size() * ProportionOfTest) {
                    bTestout.write(tempArr.get(i));
                } else {
                    bTrainout.write(tempArr.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bTestout != null) {
                    bTestout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bTrainout != null) {
                    bTrainout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //
    }
}
