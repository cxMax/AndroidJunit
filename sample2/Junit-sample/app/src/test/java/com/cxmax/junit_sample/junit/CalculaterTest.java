package com.cxmax.junit_sample.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/19.
 */
public class CalculaterTest {

    Calculater calculater ;

    @Before
    public void setUp() {
        calculater = new Calculater();
    }

    @Test
    public void sum() throws Exception {
        int a = 1;
        int b = 3;
        int result = calculater.sum(a , b);
        Assert.assertTrue(result == 4);
    }

}