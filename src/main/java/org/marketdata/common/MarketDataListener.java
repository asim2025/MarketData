package org.marketdata.common;

/**
 * Created by asim2025 on 3/29/2017.
 */
public interface MarketDataListener {
    void process(Quote quote) throws Exception;
}
