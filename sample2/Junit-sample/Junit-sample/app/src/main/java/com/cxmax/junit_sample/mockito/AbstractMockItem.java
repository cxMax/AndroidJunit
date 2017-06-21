package com.cxmax.junit_sample.mockito;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-6-20.
 */

public abstract class AbstractMockItem {

    private String param;

    public AbstractMockItem(String param) {
        this.param = param;
    }

    abstract void mock();
}
