package com.undefinedlabs.scope.dummy.benchmark;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class BenchmarkTest {

    @Test
    public void benchmarkTestMD5() throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTestMD5.class.getSimpleName())
                .mode(Mode.SingleShotTime)
                .forks(1)
                .build();

        final Collection<RunResult> runResults = new Runner(opt).run();
        assertThat(runResults).isNotEmpty();
    }

    public static class BenchmarkTestMD5 {
        @Benchmark
        public void digestMD5(){
            DigestUtils.md5Hex("ILoveJava");
        }
    }

    @Test
    public void benchmarkTestSHA1() throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTestSHA1.class.getSimpleName())
                .mode(Mode.SingleShotTime)
                .forks(1)
                .build();

        final Collection<RunResult> runResults = new Runner(opt).run();
        assertThat(runResults).isNotEmpty();
    }

    public static class BenchmarkTestSHA1 {
        @Benchmark
        public void digestSHA1(){
            DigestUtils.sha1Hex("ILoveJava");
        }
    }

    @Test
    public void benchmarkTestSHA384() throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTestSHA384.class.getSimpleName())
                .mode(Mode.SingleShotTime)
                .forks(1)
                .build();

        final Collection<RunResult> runResults = new Runner(opt).run();
        assertThat(runResults).isNotEmpty();
    }

    public static class BenchmarkTestSHA384 {
        @Benchmark
        public void digestSHA384(){
            DigestUtils.sha384("ILoveJava");
        }
    }

    @Test
    public void benchmarkTestSHA512() throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTestSHA512.class.getSimpleName())
                .mode(Mode.SingleShotTime)
                .forks(1)
                .build();

        final Collection<RunResult> runResults = new Runner(opt).run();
        assertThat(runResults).isNotEmpty();
    }

    public static class BenchmarkTestSHA512 {
        @Benchmark
        public void digestSHA512(){
            DigestUtils.sha512("ILoveJava");
        }
    }

    @Test
    public void benchmarkTestMD2() throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTestMD2.class.getSimpleName())
                .mode(Mode.SingleShotTime)
                .forks(1)
                .build();

        final Collection<RunResult> runResults = new Runner(opt).run();
        assertThat(runResults).isNotEmpty();
    }

    public static class BenchmarkTestMD2 {
        @Benchmark
        public void digestMD2(){
            DigestUtils.md2Hex("ILoveJava");
        }
    }
}
