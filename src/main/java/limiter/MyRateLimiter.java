package limiter;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
/**
 * Created by xinz on 2016/7/11.
 */
public class MyRateLimiter{
    private int limit;
    private final Stopwatch stopwatch;
    private long nextTicketMicros;
    private volatile int existTicket;
    public MyRateLimiter(int limit){
        stopwatch = Stopwatch.createStarted();
        this.limit = limit;
        nextTicketMicros = stopwatch.elapsed(TimeUnit.MICROSECONDS) + 1000000;
        existTicket = limit;
    }
    public boolean tryAcquire(){
        synchronized (mutex()){
            long now = stopwatch.elapsed(TimeUnit.MICROSECONDS);
            if(now > nextTicketMicros){
                nextTicketMicros += 1000000;
                existTicket = limit;
            }
            if(existTicket <= 0){
                return false;
            }
            existTicket--;
            return true;
        }
    }

    private volatile Object mutexDoNotUseDirectly;
    private Object mutex() {
        Object mutex = mutexDoNotUseDirectly;
        if (mutex == null) {
            synchronized (this) {
                mutex = mutexDoNotUseDirectly;
                if (mutex == null) {
                    mutexDoNotUseDirectly = mutex = new Object();
                }
            }
        }
        return mutex;
    }
}
