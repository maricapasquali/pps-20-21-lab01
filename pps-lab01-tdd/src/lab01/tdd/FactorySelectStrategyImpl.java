package lab01.tdd;

public class FactorySelectStrategyImpl implements FactorySelectStrategy {

    @Override
    public SelectStrategy newEqualsStrategy(int value) {
        return new EqualsStrategy(value);
    }

    @Override
    public SelectStrategy newMultipleOfStrategy(int multipleOf) {
        return new MultipleOfStrategy(multipleOf);
    }

    @Override
    public SelectStrategy newEvenStrategy() {
        return new EvenStrategy();
    }
}
