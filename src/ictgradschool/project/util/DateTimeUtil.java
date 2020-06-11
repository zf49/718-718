package ictgradschool.project.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeUtil {
    public static long getEpoch(LocalDateTime time) {
        return time.atZone(ZoneOffset.UTC).toEpochSecond();
    }
}
