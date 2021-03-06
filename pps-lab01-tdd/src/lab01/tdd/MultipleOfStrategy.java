package lab01.tdd;

/**
 * Represent a select Strategy to get the next multiple of a given number.
 */
public class MultipleOfStrategy implements SelectStrategy {

    private final int value;

    public MultipleOfStrategy(int value) {
        this.value = value;
    }

    @Override
    public boolean apply(int element) {
        return element % value == 0;
    }
}
