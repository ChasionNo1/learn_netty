package nio;

import java.nio.IntBuffer;

/**
 * @ClassName BasicBuffer
 * @Description TODO
 * @Author chasion
 * @Date 2022/10/31 10:54
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // Buffer的使用
        // 创建一个buffer，大小为5，存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 向buffer中存放数据
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);
        // 读取数据
        // buffer 读写功能切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
