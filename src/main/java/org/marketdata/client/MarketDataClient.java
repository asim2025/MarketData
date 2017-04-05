package org.marketdata.client;

import org.marketdata.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by asim2025 on 3/29/2017.
 */
public class MarketDataClient {
    private final static Logger log = LoggerFactory.getLogger(MarketDataClient.class);
    private final int port;
    private final LatencyTracker tracker;
    private final int serializeType;
    private final int socketBufferSize;

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("usage: port, serializeType, socketBufferSize");
        }

        String[] arr = args[0].split("_");
        int port = Integer.parseInt(arr[0]);
        int serializeType = Integer.parseInt(arr[1]);
        int socketBufferSize = Integer.parseInt(arr[2]);

        log.info("Port: {},  SerializeType: {}, socketBufferSize: {}", port, serializeType, socketBufferSize);
        MarketDataClient client = new MarketDataClient(port, serializeType, socketBufferSize);
        client.receive();
    }

    public MarketDataClient(int port, int serializeType, int socketBufferSize) {
        this.port = port;
        this.serializeType = serializeType;
        this.socketBufferSize = socketBufferSize;
        this.tracker = new LatencyTracker();
    }

    public void receive() throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        datagramSocket.setSoTimeout(10000);
        datagramSocket.setReceiveBufferSize(socketBufferSize);
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

        Quote quote = null;

        switch (serializeType) {
            case 1: quote = JavaSerialization.deserialize(data); break;
            case 2: quote = ByteBufferSerialization.deserialize(data); break;
            case 3: quote = UnsafeSerialization.deserialize(data); break;
        }

        quote.hashCode();
        //log.info("received: {}", quote);
        tracker.record();
    }
}
