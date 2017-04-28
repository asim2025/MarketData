package org.marketdata.server;

import org.marketdata.common.*;
import org.marketdata.provider.simple.SimpleMarketDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by asim2025 on 3/28/2017.
 */
public
class MarketDataServer implements MarketDataListener {
    private final static Logger log = LoggerFactory.getLogger(MarketDataServer.class);
    private final int MAX_SECS = 23_400;        // Total # seconds in a trading day (between 09:30-16:00)
    private final int port;
    private final DatagramSocket datagramSocket;
    private final InetAddress address;
    private final SimpleMarketDataProvider provider;
    private final LatencyTracker tracker;
    private final int serializeType;
    private final DatagramPacket packet;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("usage: port serializeeType");
        }
        String[] arr = args[0].split("_");
        int port = Integer.parseInt(arr[0]);
        int serializeType = Integer.parseInt(arr[1]);

        log.info("port: {},  serializeType: {}", port, serializeType);
        MarketDataServer server = new MarketDataServer(port, serializeType);
        server.start();

    }

    public MarketDataServer(int port, int serializeType) throws Exception {
        this.port = port;
        this.serializeType = serializeType;
        this.datagramSocket = new DatagramSocket();
        this.address = InetAddress.getByName("localhost");
        this.packet = new DatagramPacket(new byte[256], 256, this.address, port);
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
        byte[] buffer = null;

        switch (serializeType) {
            case 1: buffer = JavaSerialization.serialize(quote); break;
            case 2: buffer = ByteBufferSerialization.serialize(quote); break;
            case 3: buffer = UnsafeSerialization.serialize(quote); break;
        }

        packet.setData(buffer, 0, buffer.length);
        datagramSocket.send(packet);
        tracker.record();
    }
}
