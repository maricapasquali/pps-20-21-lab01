package lab01.tdd;

/**
 * Represent a select Strategy to get the next even element.
 */
public class EvenStrategy implements SelectStrategy {

    @Override
    public boolean apply(int element) {
        return element % 2 == 0;
    }
}
