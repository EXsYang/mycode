import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangda
 * @description:
 * @create 2023-05-21-14:53
 */
public class MyTomcat {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        int i = 0;
        while (!serverSocket.isClosed()){ // 如果serverSocket 没有关闭
            System.out.println("f服务器等待连接、、、" + ++i);
            // 等待浏览器/客户端连接，得到socket
            // 该socket用于通信
            Socket socket = serverSocket.accept();

            // 拿到 和socket相关的输出流
            OutputStream outputStream = socket.getOutputStream();
            FileReader fileReader = new FileReader("src/hello.html");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String buf = "";
            while ((buf = bufferedReader.readLine()) != null){
                outputStream.write(buf.getBytes());
            }
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

//            outputStream.write("hello i am server".getBytes());
            System.out.println("信息以打入管道" );

            outputStream.close();
            socket.close();
        }
        serverSocket.close();


    }
}
