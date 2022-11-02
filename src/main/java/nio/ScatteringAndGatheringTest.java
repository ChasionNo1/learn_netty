package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @ClassName ScatteringAndGatheringTest
 * @Description TODO
 * @Author chasion
 * @Date 2022/11/1 10:18
 *
 * NIO 还支持 通过多个
 * Buffer (即 Buffer 数组) 完成读写操作，即 Scattering 和 Gathering 【举例说明】
 * Scattering:将数据写入到buffer中，可以采用buffer数组，依次写入
 * Gathering：从buffer中读取数据时，可以采用buffer数组，依次读取
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) {
        // 使用ServerSocketChannel 和SocketChannel
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
            // 绑定端口到socket，并启动
            serverSocketChannel.socket().bind(inetSocketAddress);
            // 创建buffer数组
            ByteBuffer[] byteBuffers = new ByteBuffer[2];
            byteBuffers[0] = ByteBuffer.allocate(5);
            byteBuffers[1] = ByteBuffer.allocate(3);

            // 等待客户端连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            int messageLength = 8;
            while (true){
                int byteRead = 0;
                while (byteRead < messageLength){
                    long count = socketChannel.read(byteBuffers);
                    // 累计读取的字节数
                    byteRead += count;
                    System.out.println("byteRead = " + byteRead);
                    Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position = " + byteBuffer.position() + ", limit = " +
                            byteBuffer.limit()).forEach(System.out::println);
                }
                // 将所有buffer进行flip
                Arrays.asList(byteBuffers).forEach(Buffer::flip);
                // 将数据读取显示到客户端
                long byteWrite = 0;
                while (byteWrite < messageLength){
                    long count = socketChannel.write(byteBuffers);
                    byteWrite += count;
                }
                Arrays.asList(byteBuffers).forEach(Buffer::clear);
                System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWrite);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
