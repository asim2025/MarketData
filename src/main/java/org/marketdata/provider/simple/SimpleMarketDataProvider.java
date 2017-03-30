package org.marketdata.provider.simple;

import org.marketdata.common.MarketDataListener;
import org.marketdata.common.Quote;
import org.marketdata.common.SymbolHistPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by asim2025 on 3/28/2017.
 */
public class SimpleMarketDataProvider {
    private final static Logger log = LoggerFactory.getLogger(SimpleMarketDataProvider.class);
    private final Random random;
    private final MarketDataListener listener;

    public SimpleMarketDataProvider(MarketDataListener listener) {
        random = new Random();
        this.listener = listener;
    }


    /**
     *  Randomly generate symbol market data.
     *  The data (quotes) are generated within symbol's historical high/low range.
     *  The first quote equals symbol's open.
     *  The last quote equals symbol's close.
     *  Total number of quotes depend on symbols total volume for that day.
     */
    public void generate(SymbolHistPrice summary, int timeInSecs) {
        long qps = summary.getVolume() / timeInSecs;

        int num = random.nextInt(summary.getHigh() - 1 - summary.getLow() + 1) + summary.getLow();
        DecimalFormat df = new DecimalFormat("###.##");
        long quotenum = 0;

        do {
            long now = System.currentTimeMillis();
            for (int i = 0; i < qps; i++, quotenum++) {
                Quote quote;
                if (quotenum == 0) {
                    quote = new Quote(summary.getSymbol(), summary.getOpen(), now);
                } else if (quotenum == summary.getVolume()) {
                    quote = new Quote(summary.getSymbol(), summary.getClose(), now);
                } else {
                    quote = getQuote(summary.getSymbol(), num, df, now);
                }
                try {
                    listener.process(quote);
                } catch (Exception e) {
                    log.warn("exception {}", e);
                }
            }
            log.info("published quote running total: {}", quotenum);
            timeInSecs--;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (timeInSecs > 0);
    }

    private Quote getQuote(String symbol, long num, DecimalFormat df, long now) {
        double price = Double.parseDouble(df.format((double) num + random.nextDouble()));
        Quote quote = new Quote(symbol, price, now);
        return quote;
    }


}
