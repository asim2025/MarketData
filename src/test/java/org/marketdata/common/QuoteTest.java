package org.marketdata.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by asim2025 on 3/30/2017.
 */
public class QuoteTest {

    @Test
    public void testQuotePOJO() {
        Quote q = new Quote("abc", 109.12, 1234);
        assertEquals(q.getSymbol(), "abc");
        assertEquals(q.getPrice(), 109.12, 0.0001);
        assertEquals(q.getTimeStamp(), 1234);
    }

}
