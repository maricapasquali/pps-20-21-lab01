package lab01.tdd;

/**
 * Represent a select Strategy to get the next equal element of a given one.
 */
class EqualsStrategy implements SelectStrategy {

    private final int value;

    public EqualsStrategy(int value) {
        this.value = value;
    }

    @Override
    public boolean apply(int element) {
        return this.value == element;
    }
}
