package javanet;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by xinz on 16/8/15.
 */
public class URLTester {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out);
            System.out.println(socket.getLocalAddress().getHostAddress());
            System.out.println(socket.getRemoteSocketAddress());
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            Thread thread = new Thread(new ServerBackTask(isr));
            thread.start();
            int i=0;
            while (true){
                System.out.println("send....");
                osw.write("test:"+i++);
                osw.flush();
                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ServerBackTask implements Runnable{
        InputStreamReader isr;
        public ServerBackTask(InputStreamReader isr){
            this.isr = isr;
        }
        public void run() {
            System.out.println("server back start.......");
            while (true){
                try {
                    int rs = isr.read();
                    if(-1 == rs){
                        break;
                    }
                    char rse = (char)rs;
                    System.out.print(rse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println("server back end.......");
        }
    }
}
