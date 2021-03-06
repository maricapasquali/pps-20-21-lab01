import lab01.tdd.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;

    private SelectStrategy strategy;

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

    @Test
    void testNextSelectStrategy() {
        circularList.add(200);
        circularList.add(2);
        circularList.add(5);
        circularList.add(33);

        strategy = new EvenStrategy();
        Assertions.assertEquals(Optional.of(200), circularList.next(strategy));

        strategy = new MultipleOfStrategy(3);
        circularList.next();
        circularList.next();
        Assertions.assertEquals(Optional.of(33), circularList.next(strategy));

        strategy = new EqualsStrategy(2);
        circularList.next();
        Assertions.assertEquals(Optional.of(2), circularList.next(strategy));

        circularList.reset();

        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));
        Assertions.assertEquals(Optional.of(2), circularList.next(strategy));
        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));
        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));
        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));
    }

    @Test
    void testNoSatisfyNextSelectStrategy() {
        circularList.add(200);
        circularList.add(2);
        circularList.add(5);
        circularList.add(33);

        strategy = new EvenStrategy();
        circularList.next();
        circularList.next();
        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));

        strategy = new MultipleOfStrategy(3);
        circularList.previous();
        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));

        strategy = new EqualsStrategy(2);
        Assertions.assertEquals(Optional.empty(), circularList.next(strategy));

    }
}
