package test_util.three;

public class Triple {
    public final Object first;
    public final Object second;
    public final Object third;

    public Triple(Object first, Object second, Object third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static Triple of(Object first, Object second, Object third) {
        return new Triple(first, second, third);
    }
}
