package org.marketdata.common;

import org.HdrHistogram.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by asim2025 on 3/29/2017.
 */
public class LatencyTracker {
    private final static Logger log = LoggerFactory.getLogger(LatencyTracker.class);
    private final static Histogram histogram = new Histogram(TimeUnit.MINUTES.toNanos(30), 3);
    private long start;

    public LatencyTracker() {
    }


    public void begin() {
        start = System.nanoTime();
    }

    public void record() {
        histogram.recordValue(System.nanoTime() - start);
    }

    public void print() {
        histogram.outputPercentileDistribution(System.out, 1000d);
        /*log.info("Percentiles: MIN:{} 50:{} 75:{} 90:{}, 99:{}, 99.9:{}, 99.99:{}, MAX:{}",
                histogram.getMinNonZeroValue(),
                histogram.getValueAtPercentile(50d),
                histogram.getValueAtPercentile(75d),
                histogram.getValueAtPercentile(90d),
                histogram.getValueAtPercentile(99d),
                histogram.getValueAtPercentile(99.9d),
                histogram.getValueAtPercentile(99.99d),
                histogram.getMaxValue());*/
    }

}
