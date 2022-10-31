package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BIOServer
 * @Description TODO
 * @Author chasion
 * @Date 2022/10/28 15:22
 *
 * Java BIO 应用实例
 * 实例说明：
 * 1) 使用BIO模型编写一个服务器端，监听6666端口，当有客户端连接时，就启动一个线程与之通讯。
 * 2) 要求使用线程池机制改善，可以连接多个客户端.
 * 3) 服务器端可以接收客户端发送的数据(telnet 方式
 *
 *
 * Java BIO 问题分析
 * 1) 每个请求都需要创建独立的线程，与对应的客户端进行数据Read，业务处理，数据 Write 。
 * 2) 当并发数较大时，需要创建大量线程来处理连接，系统资源占用较大。
 * 3) 连接建立后，如果当前线程暂时没有数据可读，则线程就阻塞在 Read 操作上，造成线程资源浪费
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 线程池机制
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        // 创建server socket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");

        // 监听客户端连接
        while (true){
            System.out.println("线程信息 id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
            System.out.println("等待连接");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到了一个客户端");
            // 创建一个线程进行通信
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // 与客户端进行通信
                    handler(socket);

                }
            });
        }

    }

    // 编写一个handler方法，与客户端进行通信
    public static void handler(Socket socket){

        try {
            System.out.println("线程信息 id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream is = socket.getInputStream();
            // 读取客户端数据
            while (true){
                System.out.println("read.....");
                System.out.println("线程信息 id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
                int read = is.read(bytes);
                if (read != -1){
                    // 输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                }else {
                    // 读取完毕
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("关闭与客户端的连接");
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                System.out.println("socket 断开连接");
            }
        }
    }
}
