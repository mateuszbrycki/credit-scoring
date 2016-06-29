package com.neural.data.statistics;

/**
 * Created by Mateusz on 29.06.2016.
 */
public class Entry<T> {

    private T min;
    private T part;
    private T partAmounts;
    private T max;

    public Entry(T min, T part, T partAmounts, T max) {
        this.min = min;
        this.part = part;
        this.partAmounts = partAmounts;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getPart() {
        return part;
    }

    public void setPart(T part) {
        this.part = part;
    }

    public T getPartAmounts() {
        return partAmounts;
    }

    public void setPartAmounts(T partAmounts) {
        this.partAmounts = partAmounts;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "min=" + min +
                ", part=" + part +
                ", partAmounts=" + partAmounts +
                ", max=" + max +
                '}';
    }
}
