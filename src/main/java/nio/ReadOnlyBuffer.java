package nio;

import java.nio.ByteBuffer;

/**
 * @ClassName ReadOnlyBuffer
 * @Description TODO
 * @Author chasion
 * @Date 2022/11/1 9:32
 *
 * 可以将一个普通Buffer 转成只读Buffer [举例说
 * 写入会抛出readOnlyBufferException
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        // 得到一个只读的buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
    }
}
