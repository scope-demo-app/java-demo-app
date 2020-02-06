package com.undefinedlabs.scope.dummy;

import com.googlecode.junittoolbox.ParallelParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(ParallelParameterized.class)
public class SlowLogicTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        final Long[][] waitTimes = new Long[20][];
        for(int i = 0; i < 20; i++) {
            waitTimes[i] = new Long[]{ ThreadLocalRandom.current().nextLong(1000, 30000) };
        }

        return Arrays.asList(waitTimes);
    }

    private Long waitTime;

    public SlowLogicTest(final Long waitTime) {
        this.waitTime = waitTime;
    }

    @Test
    public void should_execute_slow_logic() throws InterruptedException {
        Thread.sleep(this.waitTime);
        assertThat(true).isTrue();
    }
}
