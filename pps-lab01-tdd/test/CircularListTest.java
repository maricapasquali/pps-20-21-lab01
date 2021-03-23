import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int NO_ELEMENT = 0;
    private static final int ONE_ELEMENT = 1;

    private static final int NUMBER_1 = 20;
    private static final int NUMBER_2 = 40;
    private static final int NUMBER_3 = 2;

    private static final int STRATEGY_NUMBER_1 = 200;
    private static final int STRATEGY_NUMBER_2 = NUMBER_3;
    private static final int STRATEGY_NUMBER_3 = 5;
    private static final int STRATEGY_NUMBER_4 = 33;

    private static final int EQUALS_STRATEGY_NUMBER = STRATEGY_NUMBER_2;
    private static final int MULTIPLE_STRATEGY_NUMBER = 3;

    private CircularList circularList;

    private FactorySelectStrategy factorySelectStrategy;

    @BeforeEach
    void setUp() {
        circularList = new CircularListImpl();
        factorySelectStrategy = new FactorySelectStrategyImpl();
    }

    @Test
    void testSize() {
        assertEquals(NO_ELEMENT, circularList.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    void testAdd() {
        circularList.add(NUMBER_1);
        assertEquals(ONE_ELEMENT, circularList.size());
    }

    @Test
    void testNext() {
        addSomeElement();

        assertEquals(Optional.of(NUMBER_1), circularList.next());
        assertEquals(Optional.of(NUMBER_2), circularList.next());
        assertEquals(Optional.of(NUMBER_3), circularList.next());
    }

    @Test
    void testNextIfEmpty() {
        assertEquals(Optional.empty(), circularList.next());
    }

    @Test
    void testNextOnLast() {
        addSomeElement();

        assertEquals(Optional.of(NUMBER_1), circularList.next());
        assertEquals(Optional.of(NUMBER_2), circularList.next());
        assertEquals(Optional.of(NUMBER_3), circularList.next());

        assertEquals(Optional.of(NUMBER_1), circularList.next());
        assertEquals(Optional.of(NUMBER_2), circularList.next());
    }

    @Test
    void testPrevious() {
        addSomeElement();

        assertEquals(Optional.of(NUMBER_1), circularList.next());
        assertEquals(Optional.of(NUMBER_2), circularList.next());

        assertEquals(Optional.of(NUMBER_2), circularList.previous());
        assertEquals(Optional.of(NUMBER_1), circularList.previous());
    }

    @Test
    void testPreviousIfEmpty() {
        assertEquals(Optional.empty(), circularList.previous());
    }

    @Test
    void testPreviousOnFirst() {
        addSomeElement();

        assertEquals(Optional.of(NUMBER_3), circularList.previous());
        assertEquals(Optional.of(NUMBER_2), circularList.previous());
        assertEquals(Optional.of(NUMBER_1), circularList.previous());

        assertEquals(Optional.of(NUMBER_3), circularList.previous());
    }

    @Test
    void testReset() {
        addSomeElement();

        assertEquals(Optional.of(NUMBER_1), circularList.next());
        assertEquals(Optional.of(NUMBER_2), circularList.next());
        circularList.reset();
        assertEquals(Optional.of(NUMBER_1), circularList.next());
    }

    @Test
    void testNextEvenStrategy() {
        addSomeElementWithStrategy();

        var strategy = factorySelectStrategy.newEvenStrategy();
        assertEquals(Optional.of(STRATEGY_NUMBER_1), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_2), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_1), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_2), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_3), circularList.next());
        assertEquals(Optional.of(STRATEGY_NUMBER_1), circularList.next(strategy));

    }

    @Test
    void testNextMultipleOfStrategy() {
        addSomeElementWithStrategy();

        var strategy = factorySelectStrategy.newMultipleOfStrategy(MULTIPLE_STRATEGY_NUMBER);
        assertEquals(Optional.of(STRATEGY_NUMBER_4), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_4), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_4), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_1), circularList.next());
        assertEquals(Optional.of(STRATEGY_NUMBER_4), circularList.next(strategy));

    }

    @Test
    void testNextEqualsStrategy() {
        addSomeElementWithStrategy();

        var strategy =  factorySelectStrategy.newEqualsStrategy(EQUALS_STRATEGY_NUMBER);
        assertEquals(Optional.of(STRATEGY_NUMBER_2), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_2), circularList.next(strategy));
        assertEquals(Optional.of(STRATEGY_NUMBER_3), circularList.next());
        assertEquals(Optional.of(STRATEGY_NUMBER_2), circularList.next(strategy));

    }

    @Test
    void testNoSatisfyNextSelectStrategy() {
        circularList.add(STRATEGY_NUMBER_3);
        circularList.add(STRATEGY_NUMBER_3);
        circularList.add(STRATEGY_NUMBER_4);

        var strategy = factorySelectStrategy.newEvenStrategy();
        assertEquals(Optional.empty(), circularList.next(strategy));

        strategy = factorySelectStrategy.newMultipleOfStrategy(NUMBER_2);
        assertEquals(Optional.empty(), circularList.next(strategy));

        strategy = factorySelectStrategy.newEqualsStrategy(EQUALS_STRATEGY_NUMBER);
        assertEquals(Optional.empty(), circularList.next(strategy));

    }

    private void addSomeElement() {
        circularList.add(NUMBER_1);
        circularList.add(NUMBER_2);
        circularList.add(NUMBER_3);
    }

    private void addSomeElementWithStrategy() {
        circularList.add(STRATEGY_NUMBER_1);
        circularList.add(STRATEGY_NUMBER_2);
        circularList.add(STRATEGY_NUMBER_3);
        circularList.add(STRATEGY_NUMBER_4);
    }
}
