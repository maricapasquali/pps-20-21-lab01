package lab01.tdd;

public interface FactorySelectStrategy {

    SelectStrategy newEqualsStrategy(final int value);

    SelectStrategy newMultipleOfStrategy(final int multipleOf);

    SelectStrategy newEvenStrategy();
}
