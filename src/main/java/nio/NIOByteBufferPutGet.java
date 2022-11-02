package nio;

import java.nio.ByteBuffer;

/**
 * @ClassName NIOByteBufferPutGet
 * @Description TODO
 * @Author chasion
 * @Date 2022/11/1 9:28
 *
 * ByteBuffer 支持类型化的put 和 get, put 放入的是什么数据类型，get就应该使用
 * 相应的数据类型来取出，否则可能有 BufferUnderflowException 异常。[举例说明]
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        // 类型化方式放入数据
        buffer.putInt(10);
        buffer.putChar('a');
        buffer.putLong(200L);
        buffer.putShort((short) 2);

        buffer.flip();
        System.out.println("----------------------");
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getShort());
    }
}
