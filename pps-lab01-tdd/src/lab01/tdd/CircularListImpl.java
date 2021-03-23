package lab01.tdd;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Supplier;

public class CircularListImpl implements CircularList {

    private ListIterator<Integer> iterator;
    private final List<Integer> circularList;

    public CircularListImpl() {
        this.circularList = new LinkedList<>();
        this.iterator = circularList.listIterator();
    }

    @Override
    public void add(int element) {
        this.circularList.add(element);
        this.iterator = circularList.listIterator();
    }

    @Override
    public int size() {
        return this.circularList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.circularList.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        return move(()-> this.iterator.hasNext(),
                    ()-> this.iterator.next(),
                    ()-> {
                        this.iterator = circularList.listIterator();
                        return this.iterator.next();
                    });
    }

    @Override
    public Optional<Integer> previous() {
        return move(()-> this.iterator.hasPrevious(),
                    ()-> this.iterator.previous(),
                    ()-> {
                        var lastIndex = this.size() - 1;
                        this.iterator = circularList.listIterator(lastIndex);
                        return this.circularList.get(lastIndex);
                    });
    }

    @Override
    public void reset() {
        this.iterator = circularList.listIterator();
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        var element = this.next();
        return element.isPresent() && strategy.apply(element.get()) ? element :
               (circularList.stream().anyMatch(strategy::apply) ? next(strategy) : Optional.empty());
    }

    private Optional<Integer> move(final Supplier<Boolean> hasMove, final Supplier<Integer> move, final Supplier<Integer> circularMove){
        if (hasMove.get()) {
            return Optional.of(move.get());
        } else if (this.size() > 0) {
            return Optional.of(circularMove.get());
        } else {
            return Optional.empty();
        }
    }
}
