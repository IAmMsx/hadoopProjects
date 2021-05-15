package KNN.Model;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DistanceAndLabel implements WritableComparable<DistanceAndLabel> {
    private double distance;//距离
    private double label;//类别

    public DistanceAndLabel(double distance, double label) {
        this.distance = distance;
        this.label = label;
    }

    public DistanceAndLabel() {
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLabel() {
        return label;
    }

    public void setLabel(double label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "DistanceAndLabel{" +
                "distance=" + distance +
                ", label=" + label +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistanceAndLabel that = (DistanceAndLabel) o;

        if (Double.compare(that.distance, distance) != 0) return false;
        return Double.compare(that.label, label) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(distance);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(label);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(DistanceAndLabel o) {
        if (o.equals(this)) return 0;
        return Double.compare(o.distance, this.distance);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(distance);
        dataOutput.writeDouble(label);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        distance = dataInput.readDouble();
        label = dataInput.readDouble();

    }
}
