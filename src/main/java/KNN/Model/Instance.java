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
        for (int i = 0; i < split.length - 1; i++) {
            attributeValues[i] = Double.parseDouble(split[i]);
        }
        label = Double.parseDouble(split[split.length - 1]);
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
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.attributeValues.length);
        for (double attributeValue : attributeValues) {
            dataOutput.writeDouble(attributeValue);
        }
        dataOutput.writeDouble(label);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instance instance = (Instance) o;

        if (Double.compare(instance.label, label) != 0) return false;
        return Arrays.equals(attributeValues, instance.attributeValues);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = Arrays.hashCode(attributeValues);
        temp = Double.doubleToLongBits(label);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        int len = dataInput.readInt();
        this.attributeValues = new double[len];
        for (int i = 0; i < len; i++) {
            attributeValues[i] = dataInput.readDouble();
        }
        this.label = dataInput.readDouble();
    }

    @Override
    public int compareTo(Instance o) {
        if (!o.equals(this)) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        int length = this.getAttributeValues().length;
        for (double attributeValue : attributeValues) {
            out.append(String.valueOf(attributeValue));
            out.append("\t");
        }
        return out + String.valueOf(this.label);
    }
}
