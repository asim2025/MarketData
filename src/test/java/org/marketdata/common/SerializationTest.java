package org.marketdata.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by asim2025 on 4/2/2017.
 */
public class SerializationTest {

    @Test
    public void javaSerializaationTest() throws  Exception {
        Quote q1 = new Quote("abc", 12.32, 1245678L);

        byte[] buffer = JavaSerialization.serialize(q1);
        assertNotNull(buffer);

        Quote q2 = JavaSerialization.deserialize(buffer);
        assertNotNull(q2);
        assertEquals(q1.getSymbol(),q2.getSymbol());
        assertEquals(q1.getPrice(), q2.getPrice(), 0.0001);
        assertEquals(q1.getTimeStamp(), q2.getTimeStamp());
    }


    @Test
    public void byteBufferSerializaationTest() throws  Exception {
        Quote q1 = new Quote("abc", 12.32, 1245678L);

        byte[] buffer = ByteBufferSerialization.serialize(q1);
        assertNotNull(buffer);

        Quote q2 = ByteBufferSerialization.deserialize(buffer);
        assertNotNull(q2);
        assertEquals(q1.getSymbol(),q2.getSymbol());
        assertEquals(q1.getPrice(), q2.getPrice(), 0.0001);
        assertEquals(q1.getTimeStamp(), q2.getTimeStamp());
    }

    @Test
    public void unsafeSerializaationTest() throws  Exception {
        Quote q1 = new Quote("abc", 12.32, 1245678L);

        byte[] buffer = UnsafeSerialization.serialize(q1);
        assertNotNull(buffer);

        Quote q2 = UnsafeSerialization.deserialize(buffer);
        assertNotNull(q2);
        assertEquals(q1.getSymbol(),q2.getSymbol());
        assertEquals(q1.getPrice(), q2.getPrice(), 0.0001);
        assertEquals(q1.getTimeStamp(), q2.getTimeStamp());
    }
}
