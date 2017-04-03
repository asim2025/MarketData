package org.marketdata.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by asim2025 on 4/2/2017.
 */
public class JavaSerialization {

    public static byte[] serialize(Quote quote) throws Exception {
        byte[] buffer;
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try (ObjectOutputStream o = new ObjectOutputStream(b)) {
                o.writeObject(quote);
            }
            buffer = b.toByteArray();
        }
        return buffer;
    }

    public static Quote deserialize(byte[] buffer) throws Exception {
        try (ByteArrayInputStream b = new ByteArrayInputStream(buffer)) {
            try (ObjectInputStream o = new ObjectInputStream(b)) {
                Quote quote = (Quote) o.readObject();
                return quote;
            }
        }
    }
}
