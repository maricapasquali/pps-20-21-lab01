package lab01.tdd;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

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
        if (this.iterator.hasNext()) {
            return Optional.of(this.iterator.next());
        } else if (this.size() > 0) {
            this.iterator = circularList.listIterator();
            return Optional.of(this.iterator.next());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> previous() {
        if (this.iterator.hasPrevious()) {
            return Optional.of(this.iterator.previous());
        } else if (this.size() > 0) {
            var lastIndex = this.size() - 1;
            this.iterator = circularList.listIterator(lastIndex);
            return Optional.of(this.circularList.get(lastIndex));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void reset() {
        this.iterator = circularList.listIterator();
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        var element = this.next();
        return element.isPresent() && strategy.apply(element.get()) ? element : Optional.empty();
    }
}
