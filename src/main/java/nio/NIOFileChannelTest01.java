package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannelTest01
 * @Description TODO
 * @Author chasion
 * @Date 2022/10/31 19:16
 *
 * 将字符串写入文件中
 */
public class NIOFileChannelTest01 {
    public static void main(String[] args) throws IOException {
        String str = "hello,world!~";
        // 创建一个输出流，给channel
        File file = new File("src/main/resources/file01.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        // 通过输出流，获取对应的FileChannel
        FileChannel fileChannel = fos.getChannel();
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将字符串写入到缓冲区中
        byteBuffer.put(str.getBytes());
        // 对缓冲区进行 flip，重置position
        byteBuffer.flip();
        // 将buffer写入到channel中
        fileChannel.write(byteBuffer);
        fos.close();
    }
}
