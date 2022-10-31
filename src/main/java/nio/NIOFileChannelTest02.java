package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannelTest02
 * @Description TODO
 * @Author chasion
 * @Date 2022/10/31 20:26
 *
 * 从文件中读取字符串
 */
public class NIOFileChannelTest02 {
    public static void main(String[] args) {
        File file = new File("src/main/resources/file01.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileChannel fileChannel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            // 将文件中输入，在channel中，读取到buffer中
            int read = fileChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
