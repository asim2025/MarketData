package org.marketdata.client;

import org.marketdata.common.LatencyTracker;
import org.marketdata.common.Quote;
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
        ByteBuffer byteBuffer = ByteBuffer.wrap(data); //ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
        //byteBuffer.flip();
        final int len = byteBuffer.getInt();
        final char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = byteBuffer.getChar();
        }
        final double price = byteBuffer.getDouble();
        final long timeStamp = byteBuffer.getLong();
        Quote quote = new Quote(new String(arr), price, timeStamp);
        //log.info("received: {}", quote);
        /*try(ByteArrayInputStream b = new ByteArrayInputStream(data)) {
            try (ObjectInputStream o = new ObjectInputStream(b)) {
                Quote quote = (Quote) o.readObject();
                //log.info("Quote: {}", quotePOJO);
            }
        }*/
        tracker.record();
    }
}
