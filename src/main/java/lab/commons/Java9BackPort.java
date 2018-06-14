package lab.commons;

import lombok.val;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Java9BackPort {
    static <T> Map<T, T> mapOf(T... ts) {
        assert ts.length % 2 == 0;
        val result = new HashMap<T, T>(ts.length / 2);
        for (int i = 0; i < ts.length;)
            result.put(ts[i++], ts[i++]);
        return Collections.unmodifiableMap(result);
    }

    static <K, V> Map<K, V> mapOf(K k1, V v1) {
        val result = new HashMap<K, V>(1);
        result.put(k1, v1);
        return Collections.unmodifiableMap(result);
    }

    static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2) {
        val result = new HashMap<K, V>(2);
        result.put(k1, v1);
        result.put(k2, v2);
        return Collections.unmodifiableMap(result);
    }

    static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
        val result = new HashMap<K, V>(3);
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        return Collections.unmodifiableMap(result);
    }

    static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        val result = new HashMap<K, V>(4);
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        result.put(k4, v4);
        return Collections.unmodifiableMap(result);
    }

    static <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        val result = new HashMap<K, V>(5);
        result.put(k1, v1);
        result.put(k2, v2);
        result.put(k3, v3);
        result.put(k4, v4);
        result.put(k5, v5);
        return Collections.unmodifiableMap(result);
    }
}
