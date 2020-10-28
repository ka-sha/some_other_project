public class Pair<T, S extends Comparable<S>> implements Comparable<Pair<T,S>> {
    private T element1;
    private S element2;

    public Pair(T element1, S element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public T getElement1() {
        return element1;
    }

    public S getElement2() {
        return element2;
    }

    public void setElement2(S element2) {
        this.element2 = element2;
    }

    @Override
    public String toString() {
        return element1 + " | " + element2;
    }

    @Override
    public int compareTo(Pair<T, S> p) {
        return element2.compareTo(p.element2);
    }
}