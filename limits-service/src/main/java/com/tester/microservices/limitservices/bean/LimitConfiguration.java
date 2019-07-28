package com.tester.microservices.limitservices.bean;

public class LimitConfiguration {
    private int minimum;
    private int maximum;

    public LimitConfiguration(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public LimitConfiguration() {
    }

    @Override
    public String toString() {
        return "LimitConfiguration{" +
                "minimum=" + minimum +
                ", maximum=" + maximum +
                '}';
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
