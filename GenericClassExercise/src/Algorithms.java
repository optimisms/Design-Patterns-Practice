public class Algorithms {
    public static <T extends Comparable> Stats<T> calcStats(T[] values) {
        Stats<T> stats = new Stats<>();
        stats.min = getMin(values);
        stats.max = getMax(values);
        return stats;
    }

    private static <T extends Comparable> T getMin(T[] values) {
        T min = values[0];
        for (T val : values) {
            if (min.compareTo(val) > 0) { min = val; }
        }
        return min;
    }

    private static <T extends Comparable> T getMax(T[] values) {
        T max = values[0];
        for (T val : values) {
            if (max.compareTo(val) < 0) { max = val; }
        }
        return max;
    }
}

