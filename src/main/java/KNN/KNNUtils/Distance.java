package KNN.KNNUtils;

/**
 * @Author msx
 * @Description 计算距离
 **/
public class Distance {
    public static double EuclideanDistance(double[] a, double[] b) throws Exception {
        // 判断维数是否相同
        if (a.length != b.length) {
            throw new Exception("两样例维数不同！");
        }
        // 计算距离
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }

        return sum;
    }
}
