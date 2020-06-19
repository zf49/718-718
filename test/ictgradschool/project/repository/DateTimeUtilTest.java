package ictgradschool.project.repository;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static ictgradschool.project.util.DateTimeUtil.getEpoch;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeUtilTest {

    @Test
    void testEpoch() {
        long epoch = getEpoch(LocalDateTime.of(1970, 1, 1, 0, 0));
        assertEquals(0, epoch);
    }

    //    @Test
    void testNow() {
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedNow = now.atZone(ZoneId.systemDefault());
        System.out.println(now);
        System.out.println(zonedNow);
    }
}