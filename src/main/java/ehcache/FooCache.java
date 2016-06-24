package ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.BeansException;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by xinz on 2016/6/24.
 */
public class FooCache implements ApplicationContextAware {
    private Cache productCache = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CacheManager cacheManager = applicationContext.getBean(EhCacheCacheManager.class).getCacheManager();
        this.productCache = cacheManager.getCache("productCache");
    }
}
