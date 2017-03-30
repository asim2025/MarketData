package org.marketdata.common;

import java.math.BigDecimal;

/**
 * Created by asim2025 on 3/28/2017.
 */
public class SymbolHistPrice {
    private String symbol;
    private int open;
    private int close;
    private int high;
    private int low;
    private int volume;
    private int scale = -1;
    private BigDecimal factor;

    public SymbolHistPrice(String symbol, String open, String close, String high, String low, int volume) {
        this.symbol = symbol;
        this.open = getInt(open);
        this.close = getInt(close);
        this.high = getInt(high);
        this.low = getInt(low);
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getOpen() {
        return open;
    }

    public int getClose() {
        return close;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public int getVolume() {
        return volume;
    }

    private int getInt(String str) {
        BigDecimal decimal = new BigDecimal(str);

        if (scale == -1) {
            this.scale = decimal.scale();
            double f=1d;
            for (int i = 0; i < scale; i++) {
                f *= 10;
            }
            factor = new BigDecimal(f);
        }

        return decimal.multiply(factor).intValue();
    }


    @Override
    public String toString() {
        return "SymbolHistPrice{" +
                "symbol='" + symbol + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", scale=" + scale +
                ", factor=" + factor +
                '}';
    }
}
