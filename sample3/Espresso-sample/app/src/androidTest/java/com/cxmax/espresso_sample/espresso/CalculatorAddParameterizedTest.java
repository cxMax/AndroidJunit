/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cxmax.espresso_sample.espresso;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import android.support.test.filters.SmallTest;

import com.cxmax.espresso_sample.calculator.Calculator;

import java.lang.Iterable;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
@SmallTest
public class CalculatorAddParameterizedTest {

    /**
     * @return {@link Iterable} that contains the values that should be passed to the constructor.
     * In this example we are going to use three parameters: operand one, operand two and the
     * expected result.
     */
    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0, 0},
                {0, -1, -1},
                {2, 2, 4},
                {8, 8, 16},
                {16, 16, 32},
                {32, 0, 32},
                {64, 64, 128}});
    }

    private final double mOperandOne;
    private final double mOperandTwo;
    private final double mExpectedResult;

    private Calculator mCalculator;

    public CalculatorAddParameterizedTest(double operandOne, double operandTwo,
            double expectedResult) {
        mOperandOne = operandOne;
        mOperandTwo = operandTwo;
        mExpectedResult = expectedResult;
    }

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    @Test
    public void testAdd_TwoNumbers() {
        double resultAdd = mCalculator.add(mOperandOne, mOperandTwo);
        assertThat(resultAdd, is(equalTo(mExpectedResult)));
    }

}