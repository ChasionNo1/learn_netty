package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOFileChannelTest03
 * @Description TODO
 * @Author chasion
 * @Date 2022/10/31 20:48
 *
 * 文件拷贝，使用同一个buffer
 */
public class NIOFileChannelTest03 {
    public static void main(String[] args) {
        File srcFile = new File("src/main/resources/1.txt");
        File desFile = new File("src/main/resources/2.txt");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // 站在流的角度，输入到流中
            // 将文件中内容输入到通道
            fis = new FileInputStream(srcFile);
            FileChannel fileChannel01 = fis.getChannel();
            // 流中的内容输出出去
            // 将通道中的内容输入到文件中
            fos = new FileOutputStream(desFile);
            FileChannel fileChannel02 = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            // 循环读取
            // 三者之间的连接关系是：
            // 文件  --- >  channel ---> buffer
            // 先将通道的内容read到缓冲区，然后将将缓冲区write到fos
            while (fileChannel01.read(byteBuffer) != -1){
                // 重置position
                byteBuffer.flip();
                fileChannel02.write(byteBuffer);
                // 这里需要复位操作
                // 因为写入操作也会移动buffer的指针，此时指针也在最后
                // 再次flip即可
                // 至于为什么当position==limit时，此时再read会等于0？
                byteBuffer.flip();

            }

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
