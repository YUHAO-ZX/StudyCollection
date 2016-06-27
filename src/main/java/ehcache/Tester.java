package ehcache;

import com.alibaba.fastjson.JSON;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinz on 2016/6/24.
 */
public class Tester {
    private static Cache productCache = null;
    public static void main(String[] args) throws InterruptedException {
        if(null == productCache){
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:cache/cache.xml");
            CacheManager cacheManager = applicationContext.getBean(EhCacheCacheManager.class).getCacheManager();
            productCache = cacheManager.getCache("foo");
        }

        for(int i=0;i<4;i++){
            Element element = productCache.get("zhangxin");
            if(element != null && element.getObjectValue() != null){
                System.out.println("in local cache");
                Map<String,String> local = (Map<String,String>)element.getObjectValue();
                if(null != local && !local.isEmpty()){
                    System.out.println(local.get("name"));
                }
            }else{
                System.out.println("not in local cache");
                Map<String,String> value = new HashMap<String, String>();
                value.put("name","zhangxin");
                value.put("age","21");
                productCache.put(new Element("zhangxin",value));
            }
            Thread.sleep(2000);

        }


    }
}
