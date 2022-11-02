package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannelTest04
 * @Description TODO
 * @Author chasion
 * @Date 2022/11/1 9:17
 *
 * 文件拷贝
 */
public class NIOFileChannelTest04 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 创建输入输出流
           fis = new FileInputStream("src/main/resources/a.jpeg");
           fos = new FileOutputStream("src/main/resources/b.jpeg");
           // 获取对应的channel
            FileChannel fisChannel = fis.getChannel();
            FileChannel fosChannel = fos.getChannel();
            // 通道复制
            fosChannel.transferFrom(fisChannel, 0, fisChannel.size());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
