package com.cxmax.junit_sample.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/19.
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {
        int a = 1;
        int b = 3;
        Mockito.when(Calculator.Utils.checkParamsNotNull()).thenReturn(true);
        int result = calculator.sum(a , b);
        Assert.assertTrue(result == 4);
    }

}