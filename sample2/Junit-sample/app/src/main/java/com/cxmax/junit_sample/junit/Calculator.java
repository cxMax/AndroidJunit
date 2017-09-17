package com.cxmax.junit_sample.junit;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/19.
 */

public class Calculator {

    int sum(int a, int b) {
        if (!Utils.checkParamsNotNull()) {
            return 0;
        }
        return a + b;
    }

    static class Utils{
        static boolean checkParamsNotNull(){
            return true;
        }
    }
}
