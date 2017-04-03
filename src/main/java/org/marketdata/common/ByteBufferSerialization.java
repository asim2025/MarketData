package org.marketdata.common;

import java.nio.ByteBuffer;

/**
 * Created by asim2025 on 4/2/2017.
 */
public class ByteBufferSerialization {
    public static byte[] serialize(Quote quote) throws Exception {
        byte[] buffer =  new byte[1024];
        ByteBuffer byteBuffer =  ByteBuffer.wrap(buffer); //ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
        //byteBuffer.clear();
        byteBuffer.putInt(quote.getSymbolArr().length);
        byteBuffer.asCharBuffer().put(quote.getSymbolArr());
        byteBuffer.position(byteBuffer.position() + quote.getSymbolArr().length*2);
        byteBuffer.putDouble(quote.getPrice());
        byteBuffer.putLong(quote.getTimeStamp());
        return buffer;
    }

    public static Quote deserialize(byte[] buffer) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer); //ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
        //byteBuffer.flip();
        final int len = byteBuffer.getInt();
        final char[] arr = new char[len];
        for (int i = 0; i < len; i++) {
            arr[i] = byteBuffer.getChar();
        }
        final double price = byteBuffer.getDouble();
        final long timeStamp = byteBuffer.getLong();
        Quote quote = new Quote(new String(arr), price, timeStamp);
        return quote;
    }

}
