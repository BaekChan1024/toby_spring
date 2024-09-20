package toby_spring.hellospring.clock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class ClockTest {

    @Test
    public void clock() {
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt1).isBefore(dt2);
    }

    @Test
    public void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(dt1).isEqualTo(dt2);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }
}
