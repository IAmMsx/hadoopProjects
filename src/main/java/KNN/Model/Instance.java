package KNN.Model;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class Instance implements WritableComparable<Instance> {
    private double[] attributeValues;//属性
    private double label;//类别

    public Instance(String line) {
        String[] split = line.split("\t");
        attributeValues = new double[split.length - 1];

        for (int i = 0; i < attributeValues.length; i++) {
            attributeValues[i] = Double.parseDouble(split[i]);
        }
        label = Double.parseDouble(split[split.length - 1]);

        // mnist 标签在第一列
//        label = Double.parseDouble(split[0]);
//        for (int i = 1; i < split.length; i++) {
//            attributeValues[i - 1] = Double.parseDouble(split[i]);
//        }
    }

    public Instance(double[] attributeValues, double label) {
        this.attributeValues = attributeValues;
        this.label = label;
    }

    public Instance() {
    }

    public double[] getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(double[] attributeValues) {
        this.attributeValues = attributeValues;
    }

    public double getLabel() {
        return label;
    }

    public void setLabel(double label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instance instance = (Instance) o;

        return Arrays.equals(attributeValues, instance.attributeValues);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(attributeValues);
    }

    @Override
    public int compareTo(Instance o) {
        if (o.equals(this))
            return 0;
        double thisSum = 0.0, oSum = 0.0;
        for (double attributeValue : this.attributeValues) {
            thisSum += attributeValue;
        }
        for (double attributeValue : o.attributeValues) {
            oSum += attributeValue;
        }
        if (thisSum > oSum) return 1;
        else return -1;
    }


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.attributeValues.length);
        for (double attributeValue : this.attributeValues) {
            dataOutput.writeDouble(attributeValue);
        }
        dataOutput.writeDouble(label);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        int len = dataInput.readInt();
        this.attributeValues = new double[len];
        for (int i = 0; i < len; i++) {
            this.attributeValues[i] = dataInput.readDouble();
        }
        this.label = dataInput.readDouble();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (double attributeValue : attributeValues) {
            out.append(attributeValue);
            out.append("\t");
        }
        return out + String.valueOf(this.label);
    }
}
