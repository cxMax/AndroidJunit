package com.cxmax.junit_sample.mockito;

import com.cxmax.junit_sample.junit.Calculater;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @describe : some test methods are picked from https://static.javadoc.io/org.mockito/mockito-core/2.8.9/org/mockito/Mockito.html#7
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
        List spy = Mockito.spy(list);
        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");
        Assert.assertEquals("one" , spy.get(0));
    }

    @Test
    public void checkSpy1() {
        List list = new LinkedList();
        List spy = Mockito.spy(list);

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
        MockitoAnnotations.initMocks(Calculater.class);
    }

    @Test
    public void testMockIterators() {
        when(mock.someMethod("some arg"))
                .thenThrow(new RuntimeException())
                .thenReturn("foo");

        //First call: throws runtime exception:
        mock.someMethod("some arg");

        //Second call: prints "foo"
        System.out.println(mock.someMethod("some arg"));

        //Any consecutive call: prints "foo" as well (last stubbing wins).
        System.out.println(mock.someMethod("some arg"));
    }

}