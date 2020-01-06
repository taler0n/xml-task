public class IntRange {
    private int minValue;
    private int maxValue;

    public IntRange(int minimum, int maximum) {
        minValue = minimum;
        maxValue = maximum;
    }

    public boolean contains(int value) {
        return (value >= minValue && value <= maxValue);
    }
}
