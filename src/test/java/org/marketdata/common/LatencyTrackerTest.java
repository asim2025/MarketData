package org.marketdata.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by asim2025 on 3/29/2017.
 */
public class LatencyTrackerTest {
    private final static Logger log = LoggerFactory.getLogger(LatencyTrackerTest.class);

    @Test
    public void testLatencyTracker() throws Exception {
        LatencyTracker tracker = new LatencyTracker();
        tracker.begin();
        Thread.sleep(10);
        tracker.record();
        tracker.print();
    }
}
