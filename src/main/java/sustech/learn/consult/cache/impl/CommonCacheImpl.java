package sustech.learn.consult.cache.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import sustech.learn.consult.cache.CommonCache;

import java.util.Date;
import java.util.HashMap;

@Service
public class CommonCacheImpl implements CommonCache{
    private final static HashMap<String, DataItem> data = new HashMap<String, DataItem>();
    private final long defaultExpiredTime = 10 * 60; // 10 分钟
    CommonCache commonCache;
    @Override
    public Object put(String key, Object value) {
        return this.put(key, value, defaultExpiredTime);
    }

    @Override
    public Object put(String key, Object value, long expiredSecond) {
        if (null == value) return null;
        DataItem item = new DataItem();
        Date expireDate = new Date();
        expireDate.setTime(System.currentTimeMillis() + expiredSecond * 1000);
        item.setExpireDate(expireDate);
        item.setValue(value);
        data.put(key, item);
        return get(key);
    }

    @Override
    public Object get(String key) {
        DataItem item = data.get(key);
        if (null == item) return null;
        if (item.expireDate.compareTo(new Date()) > 0) {
            return item.getValue();
        }
        this.remove(key);
        return null;
    }

    @Override
    public void remove(String key) {
        data.remove(key);
    }

    @Data
    class DataItem {
        private Date expireDate;
        private Object value;
    }

}
