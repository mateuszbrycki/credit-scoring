package com.neural.data.statistics;

/**
 * Created by Mateusz on 29.06.2016.
 */
public class Entry<T> {

    private T min;
    private T firstBound;
    private T secondBound;
    private T max;


    public Entry(T min, T firstBound, T secondBound, T max) {
        this.min = min;
        this.firstBound = firstBound;
        this.secondBound = secondBound;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getFirstBound() {
        return firstBound;
    }

    public void setFirstBound(T firstBound) {
        this.firstBound = firstBound;
    }

    public T getSecondBound() {
        return secondBound;
    }

    public void setSecondBound(T secondBound) {
        this.secondBound = secondBound;
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
                ", firstBound=" + firstBound +
                ", secondBound=" + secondBound +
                ", max=" + max +
                '}';
    }
}
