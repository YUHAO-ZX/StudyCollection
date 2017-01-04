package javanet.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created by xinz on 16/9/12.
 */
public class BufferCharView {//buffer 子视图
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(7).order(ByteOrder.BIG_ENDIAN);
        CharBuffer charBuffer = buffer.asCharBuffer();
        buffer.put (0, (byte)0);
        buffer.put (1, (byte)'H');
        buffer.put (2, (byte)0);
        buffer.put (3, (byte)'I');
        buffer.put (4, (byte)0);
        buffer.put (5, (byte)'!');
        buffer.put (6, (byte)0);
        print(buffer);
        print(charBuffer);
    }
    private static void print(Buffer buffer){
        System.out.println ("pos=" + buffer.position( )
                + ", limit=" + buffer.limit( )
                + ", capacity=" + buffer.capacity( )
                + ": '" + buffer.toString( ) + "'");
    }
}
