package com.cxmax.junit_sample.mockito;

import com.cxmax.junit_sample.junit.Calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.Timeout;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

/**
 * @describe : some test methods are picked from below
 *             https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#7
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/19.
 */
public class MockitoDemoTest {

    List<String> mockedList;

    @Before
    public void setUp() throws Exception {
        mockedList = mock(List.class);
    }

    @Test
    public void checkResult1() {
        when(mockedList.get(0)).thenReturn("test");
        String result = mockedList.get(0);
        verify(mockedList).get(0);
        Assert.assertEquals("test" , result);
    }

    @Test
    public void checkResult2() {
        when(mockedList.get(anyInt())).thenReturn("hello","world");
        String result = mockedList.get(0)+mockedList.get(1);
        verify(mockedList , times(2)).get(anyInt());
        Assert.assertEquals("helloworld", result);
    }

    @Test
    public void checkResult3() {
        Map<Integer,String> map = mock(Map.class);
        when(map.put(anyInt(), anyString())).thenReturn("hello");
        map.put(1, "world");
        verify(map).put(1, "world");
//        Mockito.verify(map).put(Mockito.eq(1), Mockito.eq("world"));
    }

    @Test
    public void checkResult4() {
        List<String> mockedList = mock(List.class);
        //using mock
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        verify(mockedList).add("once");
        when(mockedList.get(anyInt())).thenReturn("once");
        String result = mockedList.get(0);
        Assert.assertEquals("once" , result);
    }

    @Test
    public void checkSpy() {
        List list = new LinkedList();
        List spy = spy(list);
        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");
        Assert.assertEquals("one" , spy.get(0));
    }

    @Test
    public void checkSpy1() {
        List list = new LinkedList();
        List spy = spy(list);

        when(spy.get(0)).thenReturn("foo");
        Mockito.doReturn("foo").when(spy).get(0);
    }

    @Test
    public void testStub() {
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void testArgumentMatcher() {
        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());
    }

    @Test
    public void testExactNumOfInvocation() {
        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");
    }

    @Test
    public void testDoThrow() {
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();
    }

    @Test
    public void testInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");
        //create an inOrder verifier for a single mock
        InOrder order = inOrder(singleMock);
        //following will make sure that add is first called with "was added first, then with "was added second"
        order.verify(singleMock).add("was added first");
        order.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");
        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");
    }

    @Test
    public void testVerifyZeroInteractions() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);
        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void testRedundantInvocation() {
        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void testAnnotations() {
        MockitoAnnotations.initMocks(Calculator.class);
    }

    @Test
    public void testMockIterators() {
        when(mockedList.get(anyInt()))
                .thenThrow(new RuntimeException())
                .thenReturn("foo");

        //First call: throws runtime exception:
        mockedList.get(anyInt());

        //Second call: prints "foo"
        System.out.println(mockedList.get(anyInt()));

        //Any consecutive call: prints "foo" as well (last stubbing wins).
        System.out.println(mockedList.get(anyInt()));

        //notice : All mock.someMethod("some arg") calls will return "two"
        when(mockedList.get(anyInt()))
                .thenReturn("one");
        when(mockedList.get(anyInt()))
                .thenReturn("two");
    }

    @Test
    public void testMockWithCallBack() {
        when(mockedList.get(anyInt())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + args;
            }
        });

        //the following prints "called with arguments: anyInt()"
        System.out.println(mockedList.get(anyInt()));
    }

    @Test
    public void testMockDoReturn() {
        //doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod() family of methods
        doReturn("sao ni ma !").when(mockedList.get(anyInt()));

        System.out.println(mockedList.get(anyInt()));
    }

    @Test
    public void testMockSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    @Test
    public void testMockSpyGotcha() {
        // when you spy on real objects + you try to stub a final method = trouble.
        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
    }

    @Test
    public void testMockSmartNull() {
        List list = mock(LinkedList.class , Mockito.RETURNS_SMART_NULLS);
        // it will not throw NullPointException !
        System.out.println(list.get(0));
        System.out.println(list.toArray().length);
    }

    @Test
    public void testMockArgumentCaptor() {
        // ArgumentCaptor most like spy() , it returns a real object.
        ArgumentCaptor<Calculator> argument = ArgumentCaptor.forClass(Calculator.class);
        verify(argument.capture().getValue() , times(1));
        junit.framework.Assert.assertEquals(1, argument.getValue().getValue());
    }

    @Test
    public void testPartialMock() {
        //you can create partial mock with spy() method:
        List list = spy(new LinkedList());

        //you can enable partial mock capabilities selectively on mocks:
        Calculator mock = mock(Calculator.class);
        //Be sure the real implementation is 'safe'.
        //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
        when(mock.getValue()).thenCallRealMethod();
    }

    @Test
    public void testMockReset() {
        //avoid doing the below things, it means you don't write a simple or small test
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);

        reset(mock);
        //at this point the mock forgot any interactions & stubbing
    }

    @Test
    public void testMockSerializable() {
        List serializableMock = mock(List.class, withSettings().serializable());


        List<Object> list = new ArrayList<Object>();
        List<Object> spy = mock(ArrayList.class, withSettings()
                .spiedInstance(list)
                .defaultAnswer(CALLS_REAL_METHODS)
                .serializable());

    }


    @Test
    public void testMockVerificationWithTimeOut() {
        //passes when someMethod() is called within given time span
        verify(mockedList.get(0), timeout(100));
        //above is an alias to:
        verify(mockedList.get(0), timeout(100).times(1));

        //passes when someMethod() is called *exactly* 2 times within given time span
        verify(mockedList.get(0), timeout(100).times(2));

        //passes when someMethod() is called *at least* 2 times within given time span
        verify(mockedList.get(0), timeout(100).atLeast(2));

        //verifies someMethod() within given time span using given verification mode
        //useful only if you have your own custom verification modes.
        verify(mockedList.get(0), new Timeout(100, new VerificationMode() {
            @Override
            public void verify(VerificationData data) {
                mockedList.get(anyInt());
            }
        }));
    }

    //instead:
    @Spy Calculator calculator = new Calculator();
    //you can write:
    @Spy Calculator calculator1;

    //same applies to @InjectMocks annotation:
    @InjectMocks Calculator calculator2;
    @Test
    public void testAutomaticInstantiateWithAnnotation() {


    }

    @Test
    public void testMockOneLineStub() {
        List list = when(mock(ArrayList.class , Mockito.RETURNS_SMART_NULLS)).thenThrow(new NullPointerException()).getMock();
    }

    @Test
    public void testMockIgnoreWithStub() {
        List list = mock(List.class,  Mockito.RETURNS_SMART_NULLS);
        //creates InOrder that will ignore stubbed
        InOrder inOrder = inOrder(ignoreStubs(mockedList, list));
        inOrder.verify(mockedList);
        inOrder.verify(list);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testMockDetail() {
        //To identify whether a particular object is a mock or a spy:
        mockingDetails(mockedList).isMock();
        mockingDetails(mockedList).isSpy();

        //Getting details like type to mock or default answer:
        MockingDetails details = mockingDetails(mockedList);
        details.getMockCreationSettings().getTypeToMock();
        details.getMockCreationSettings().getDefaultAnswer();

        //Getting interactions and stubbings of the mock:
        MockingDetails details1 = mockingDetails(mockedList);
        details.getInteractions();
        details.getStubbings();

        //Printing all interactions (including stubbing, unused stubs)
        System.out.println(mockingDetails(mockedList).printInvocations());
    }

    @Test
    public void testMockCustomVerificationFailureMessage() {
        // will print a custom message on verification failure
        verify(mockedList.get(anyInt()), description("This will print on failure")).someMethod();

        // will work with any verification mode
        verify(mockedList.get(anyInt()), times(2).description("someMethod should be called twice")).someMethod();
    }
}