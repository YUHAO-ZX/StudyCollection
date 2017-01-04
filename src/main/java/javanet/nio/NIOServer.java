package javanet.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xinz on 16/9/9.
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        FileChannel outputChannel = null;
        FileChannel inputChannel = null;
        try {
            File file = new File("/Users/niceday/1.txt");
            fos = new FileOutputStream(file);
            fis = new FileInputStream(file);
            inputChannel = fis.getChannel();
            outputChannel = fos.getChannel();
            ByteBuffer bbf = ByteBuffer.allocate(1024);
//            bbf.put("asdfasdfasfdaasdfasdfsf".getBytes());
            bbf.flip();
            inputChannel.read(bbf);
//            bbf.flip();
//            outputChannel.write(bbf);
            bbf.toString();
            System.out.println(bbf.toString());

            System.out.println();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if(null != outputChannel){
                outputChannel.close();
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
