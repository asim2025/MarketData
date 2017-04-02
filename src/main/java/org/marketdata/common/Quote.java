package org.marketdata.common;

import java.io.Serializable;

/**
 * Created by asim2025 on 3/28/2017.
 */
public class Quote {
    private String symbol;
    private double price;
    private long timeStamp;
    private char[] symArr;

    public Quote(String symbol, double price, long timeStamp) {
        this.symbol = symbol;
        this.price = price;
        this.timeStamp = timeStamp;

        symArr = new char[symbol.length()];
        for (int i = 0; i < symArr.length; i++) {
            symArr[i] = symbol.charAt(i);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public char[] getSymbolArr() { return symArr; }

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
