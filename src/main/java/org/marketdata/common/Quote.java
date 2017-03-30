package org.marketdata.common;

import java.io.Serializable;

/**
 * Created by asim2025 on 3/28/2017.
 */
public class Quote implements Serializable {
    private String symbol;
    private double price;
    private long timeStamp;

    public Quote(String symbol, double price, long timeStamp) {
        this.symbol = symbol;
        this.price = price;
        this.timeStamp = timeStamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
