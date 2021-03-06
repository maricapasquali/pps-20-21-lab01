package lab01.tdd;

/**
 * Represent a select Strategy to get the next multiple of a given number.
 */
public class MultipleOfStrategy extends EqualsStrategy {

    private final int multipleOf;

    public MultipleOfStrategy(int multipleOf) {
        super(0);
        this.multipleOf = multipleOf;
    }

    @Override
    public boolean apply(int element) {
        return super.apply(element % this.multipleOf);
    }
}
