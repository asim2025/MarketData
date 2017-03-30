package org.marketdata.server;

import org.marketdata.common.LatencyTracker;
import org.marketdata.common.MarketDataListener;
import org.marketdata.common.Quote;
import org.marketdata.common.SymbolHistPrice;
import org.marketdata.provider.simple.SimpleMarketDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by asim2025 on 3/28/2017.
 */
public class MarketDataServer implements MarketDataListener {
    private final static Logger log = LoggerFactory.getLogger(MarketDataServer.class);
    private final int MAX_SECS = 23_400;        // Total # seconds in a trading day (between 09:30-16:00)
    private final int port;
    private final DatagramSocket datagramSocket;
    private final InetAddress address;
    private final SimpleMarketDataProvider provider;
    private final LatencyTracker tracker;

    public static void main(String[] args) throws Exception {
        int port = 8445;

        MarketDataServer server = new MarketDataServer(port);
        server.start();

    }

    public MarketDataServer(int port) throws Exception {
        this.port = port;
        this.datagramSocket = new DatagramSocket();
        this.address = InetAddress.getByName("localhost");
        this.provider = new SimpleMarketDataProvider(this);
        this.tracker = new LatencyTracker();
    }

    public void start() {
        SymbolHistPrice symbolHistPrice = new SymbolHistPrice("MSFT", "64.63", "65.10", "65.22", "64.35", 1_000_000);
        log.info(symbolHistPrice.toString());
        provider.generate(symbolHistPrice, 1);
        tracker.print();
    }

    @Override
    public void process(Quote quote) throws Exception {
        tracker.begin();
        byte[] buffer;
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try(ObjectOutputStream o = new ObjectOutputStream(b)) {
                o.writeObject(quote);
            }
            buffer = b.toByteArray();
        }
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        datagramSocket.send(packet);
        tracker.record();
    }
}
