package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xinz on 2016/7/20.
 */
public class ReentrantLockTest {
    public static volatile boolean m = true;
    public static void main(String[] args) throws InterruptedException {
        //condition.await() 会让线程丢锁，其他线程可获取锁，condition.signal()  唤起一个正在wait的线程，该线程重新获取锁
//        testCondition();

        //
        uninterruptibly();

    }

    public static void uninterruptibly() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("64949");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                while (m){
//                    try {
//                        condition.await();
//                    } catch (InterruptedException e) {
//                        System.out.println("test");
//                        e.printStackTrace();
//                    }
//                }
                System.out.println("execute thread 1");
                lock.unlock();
            }
        };
        Thread t = new Thread(r);
        t.start();

        lock.lock();
        Thread.sleep(1000);
//        t.interrupt();
        System.out.println("interrupt");
        condition.signal();
        Thread.sleep(1000);
        m = false;
        lock.unlock();
        System.out.println("signal");
    }
    public static void testCondition() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (m){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        System.out.println("test");
                        e.printStackTrace();
                    }
                }

                System.out.println("execute thread 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        };
       Thread thread1 = new Thread(r);
       Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

//        Thread.sleep(1000);
//        thread2.interrupt();
        lock.lock();
        System.out.println("execute thread 2");
        m = false;
        condition.signalAll();
        lock.unlock();
    }

}
