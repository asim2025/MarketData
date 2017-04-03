package org.marketdata.client;

import org.marketdata.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

/**
 * Created by asim2025 on 3/29/2017.
 */
public class MarketDataClient {
    private final static Logger log = LoggerFactory.getLogger(MarketDataClient.class);
    private final int port;
    private final LatencyTracker tracker;

    public static void main(String[] args) throws Exception {
        int port = 8445;
        MarketDataClient client = new MarketDataClient(port);
        client.receive();
    }

    public MarketDataClient(int port) {
        this.port = port;
        this.tracker = new LatencyTracker();
    }

    public void receive() throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        datagramSocket.setSoTimeout(10000);
        log.info("connected to port {} ...", port);
        int nquote = 0;

        try {
            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(packet);
                ++nquote;
                process(packet.getData());
            }
        } finally {
            log.info("processed quote: {}", nquote);
            tracker.print();
        }
    }

    private void process(byte[] data) throws Exception {
        tracker.begin();
        //Quote quote = JavaSerialization.deserialize(data);
        //Quote quote = ByteBufferSerialization.deserialize(data);
        Quote quote = UnsafeSerialization.deserialize(data);
        //log.info("received: {}", quote);
        tracker.record();
    }
}
