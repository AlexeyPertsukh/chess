package test_util.three;

public class TripleString {
    public final String first;
    public final String second;
    public final String third;

    public TripleString(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static TripleString of(String first, String second, String third) {
        return new TripleString(first, second, third);
    }
}
