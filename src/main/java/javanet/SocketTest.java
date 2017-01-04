package javanet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xinz on 16/8/15.
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);
            while (true){
                System.out.println("start.......");
                Socket socket = ss.accept();
                System.out.println(socket.getLocalAddress().getHostAddress());
                System.out.println(socket.getRemoteSocketAddress());
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                Thread thread = new Thread(new SendClientTask(osw));
                thread.start();
//                BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String temp;
                char[] buf = new char[10];
                while (true){
                    int rs = isr.read(buf);
                    if(rs == -1){
                        System.out.println("finish");
                        break;
                    }
                    System.out.println(String.valueOf(buf));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class SendClientTask implements Runnable{
        OutputStreamWriter osw;
        public SendClientTask(OutputStreamWriter osw){
            this.osw = osw;
        }
        public void run() {
            for(int i=0;i<10;i++){
                try {
                    osw.write("t"+i);
                    osw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
