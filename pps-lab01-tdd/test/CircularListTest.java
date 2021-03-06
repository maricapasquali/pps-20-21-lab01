import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;

    @BeforeEach
    void setUp() {
        circularList = new CircularListImpl();
    }

    @Test
    void testAdd() {
        circularList.add(20);
        Assertions.assertEquals(Optional.of(20), circularList.next());
    }

    @Test
    void testSize() {
        Assertions.assertEquals(0, circularList.size());
    }

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(circularList.isEmpty());
    }

    @Test
    void testNextIfEmpty() {
        Assertions.assertEquals(Optional.empty(), circularList.next());
    }

    @Test
    void testNext() {
        circularList.add(20);
        circularList.add(40);
        circularList.add(2);
        Assertions.assertEquals(Optional.of(20), circularList.next());
        Assertions.assertEquals(Optional.of(40), circularList.next());
        Assertions.assertEquals(Optional.of(2), circularList.next());
    }

    @Test
    void testNextOnLast() {
        circularList.add(20);
        circularList.add(40);
        circularList.add(2);

        Assertions.assertEquals(Optional.of(20), circularList.next());
        Assertions.assertEquals(Optional.of(40), circularList.next());
        Assertions.assertEquals(Optional.of(2), circularList.next());

        Assertions.assertEquals(Optional.of(20), circularList.next());
        Assertions.assertEquals(Optional.of(40), circularList.next());
    }

    @Test
    void testPreviousIfEmpty() {
        Assertions.assertEquals(Optional.empty(), circularList.previous());
    }

    @Test
    void testPrevious() {
        circularList.add(20);
        circularList.add(40);
        circularList.add(2);
        Assertions.assertEquals(Optional.of(20), circularList.next());
        Assertions.assertEquals(Optional.of(40), circularList.next());

        Assertions.assertEquals(Optional.of(40), circularList.previous());
        Assertions.assertEquals(Optional.of(20), circularList.previous());
    }

    @Test
    void testPreviousOnFirst() {
        circularList.add(20);
        circularList.add(40);
        circularList.add(2);
        Assertions.assertEquals(Optional.of(2), circularList.previous());
        Assertions.assertEquals(Optional.of(40), circularList.previous());
        Assertions.assertEquals(Optional.of(20), circularList.previous());

        Assertions.assertEquals(Optional.of(2), circularList.previous());
    }

    @Test
    void testReset() {
        circularList.add(20);
        circularList.add(40);
        circularList.add(2);

        Assertions.assertEquals(Optional.of(20), circularList.next());
        Assertions.assertEquals(Optional.of(40), circularList.next());
        circularList.reset();
        Assertions.assertEquals(Optional.of(20), circularList.next());
    }
}
