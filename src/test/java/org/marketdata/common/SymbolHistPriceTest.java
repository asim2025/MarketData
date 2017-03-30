package org.marketdata.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by asim2025 on 3/28/2017.
 */
public class SymbolHistPriceTest {
    private Logger log = LoggerFactory.getLogger(SymbolHistPriceTest.class);

    @Test
    public void testConversion() {
        SymbolHistPrice symbolHistPrice = new SymbolHistPrice("MSFT", "64.63", "65.10", "65.22", "64.35", 1_000_000);
        log.info(symbolHistPrice.toString());
        assertEquals(symbolHistPrice.getOpen(), 6463);
        assertEquals(symbolHistPrice.getClose(), 6510);
        assertEquals(symbolHistPrice.getHigh(), 6522);
        assertEquals(symbolHistPrice.getLow(), 6435);
        assertEquals(symbolHistPrice.getVolume(), 1000000);
    }

    @Test
    public void testBigDecimalConversion() {
        BigDecimal bigDecimal = new BigDecimal("65.10");
        BigDecimal factor = new BigDecimal(100);
        BigDecimal x = bigDecimal.multiply(factor);
        log.info("{}", x.intValue());
    }
}
