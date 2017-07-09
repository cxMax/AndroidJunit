
package com.cxmax.espresso_sample.espresso;

import android.support.test.filters.LargeTest;
import android.test.ActivityInstrumentationTestCase2;

import com.cxmax.espresso_sample.CalculatorActivity;
import com.cxmax.espresso_sample.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.cxmax.espresso_sample.espresso.HintMatcher.withHint;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@LargeTest
public class OperationHintLegacyInstrumentationTest
        extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    private CalculatorActivity mActivity;

    public OperationHintLegacyInstrumentationTest() {
        super(CalculatorActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
    }

    public void testPreconditions() {
        assertThat(mActivity, notNullValue());
    }

    public void testEditText_OperandOneHint() {
        String operandOneHint = mActivity.getString(R.string.type_operand_one_hint);
        onView(withId(R.id.operand_one_edit_text)).check(matches(HintMatcher.withHint(operandOneHint)));
    }

    public void testEditText_OperandTwoHint() {
        String operandTwoHint = mActivity.getString(R.string.type_operant_two_hint);
        onView(withId(R.id.operand_two_edit_text)).check(matches(HintMatcher.withHint(operandTwoHint)));
    }

}
