package com.cxmax.junit_sample.junit;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/19.
 */

public class Calculator {

    private int value;

    public int sum(int a , int b) {
        return a + b;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
