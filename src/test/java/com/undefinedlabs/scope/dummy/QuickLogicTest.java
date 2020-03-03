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
public class QuickLogicTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        final Long[][] waitTimes = new Long[800][];
        for(int i = 0; i < 800; i++) {
            waitTimes[i] = new Long[]{ ThreadLocalRandom.current().nextLong(1000) };
        }

        return Arrays.asList(waitTimes);
    }

    private Long waitTime;

    public QuickLogicTest(final Long waitTime) {
        this.waitTime = waitTime;
    }

    @Test
    public void should_execute_quick_logic() throws InterruptedException {
        Thread.sleep(this.waitTime);
        assertThat(true).isTrue();
    }

}
