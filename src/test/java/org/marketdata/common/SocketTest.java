package org.marketdata.common;

import org.junit.Test;

import java.net.DatagramSocket;

/**
 * Created by asim2025 on 4/4/2017.
 */
public class SocketTest {

    @Test
    public void testSocketBufferSize() throws Exception {
        DatagramSocket socket = new DatagramSocket(9999);
        System.out.println("socketBufferSize: " + socket.getReceiveBufferSize());
    }
}
