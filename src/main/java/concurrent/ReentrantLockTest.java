package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xinz on 2016/7/20.
 */
public class ReentrantLockTest {
    public static volatile int m = 0;
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        final ReentrantLock lock = new ReentrantLock();
        for(int i=0;i<100;i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    if(m == 0){
                        m = 1;
                    }else{
                        m = 0;
                    }
                    System.out.println(m);
                    lock.unlock();
                }
            });

        }

    }
}
