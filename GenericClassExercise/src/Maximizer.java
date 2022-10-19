public class Maximizer<T extends Comparable> {
    private T myVal;

    public void updateValue(T val) {
        if (myVal == null || myVal.compareTo(val) < 0) { myVal = val; }
    }

    public T getValue() {
        return myVal;
    }
}
