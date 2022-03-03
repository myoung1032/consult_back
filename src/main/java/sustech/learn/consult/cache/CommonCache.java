package sustech.learn.consult.cache;

public interface CommonCache {
    Object put(String key, Object value);

    Object put(String key, Object value, long expiredSecond);

    Object get(String key);

    void remove(String key);
}
