package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName MappedByteBufferTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/11/1 9:51
 *
 *  mappedByteBuffer 可以让文件直接在内存（堆外内存）修改，操作系统不需要拷贝一份
 */
public class MappedByteBufferTest {
    public static void main(String[] args) {
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile("src/main/resources/1.txt", "rw");
            // 获取对应的通道
            FileChannel channel = rw.getChannel();
            /**
             * 参数1：FileChannel.MapMode.READ_WRITE  使用的读写模式
             * 参数2：可以直接修改的起始位置
             * 参数3：映射到内存的大小，多个字节映射到内存中
             *
             * MappedByteBuffer抽象类的具体实现类，DirectByteBuffer
             * */
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            map.put(0, (byte) 'H');
            map.put(3, (byte) '9');


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
