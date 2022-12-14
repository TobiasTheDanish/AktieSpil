public class Range {

    public final float min;
    public final float max;

    public Range(float min, float max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return Colors.ANSI_RED + min + "%" + Colors.ANSI_RESET + " / " + Colors.ANSI_GREEN + max + "%" + Colors.ANSI_RESET;
    }
}
