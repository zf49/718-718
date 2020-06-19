package ictgradschool.project.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random(1591848333);
    }

    @Test
    void testDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    //    @Test
    void generateRandomTimesForArticles() {
        LocalDateTime startTime = LocalDateTime.of(2020, 3, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 6, 1, 0, 0);

        List<LocalDateTime> times = getRandomSortedTimesBetween(startTime, endTime, 100);

        for (LocalDateTime time : times) {
            System.out.println(time.toString());
        }
    }

    //    @Test
    void generateRandomTimesForComments() {
        LocalDateTime startTime = LocalDateTime.of(2020, 6, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 6, 10, 0, 0);

        List<LocalDateTime> times = getRandomSortedTimesBetween(startTime, endTime, 500);

        for (LocalDateTime time : times) {
            System.out.println(time.toString());
        }
    }

    @Test
    void testGetEpoch() {
        long epoch = getEpoch(LocalDateTime.of(1970, 1, 1, 0, 0));
        assertEquals(0, epoch);
    }

    private List<LocalDateTime> getRandomSortedTimesBetween(LocalDateTime startTime, LocalDateTime endTime, int length) {
        List<LocalDateTime> times = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            times.add(randomTimeBetween(startTime, endTime));
        }

        Collections.sort(times);
        return times;
    }

    private LocalDateTime randomTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        long startEpoch = getEpoch(startTime);
        long endEpoch = getEpoch(endTime);
        long randomEpoch = getRandomLongBetween(startEpoch, endEpoch);
        return LocalDateTime.ofEpochSecond(randomEpoch, 0, ZoneOffset.UTC);
    }

    private long getRandomLongBetween(long lower, long upper) {
        return (long) (random.nextDouble() * (upper - lower) + lower);
    }

    private long getEpoch(LocalDateTime time) {
        return time.atZone(ZoneOffset.UTC).toEpochSecond();
    }
}
