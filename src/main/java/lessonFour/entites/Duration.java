package lessonFour.entites;

import java.util.Arrays;

public enum Duration {
    MINUTES_60(60),
    MINUTES_80(80),
    MINUTES_120(120);

    private final int minutes;

    Duration(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    public static Duration getDurationOnValue(int minutes) {
        return Arrays.stream(Duration.values()).filter(d -> d.getMinutes() == minutes).findFirst().get();
    }
}
