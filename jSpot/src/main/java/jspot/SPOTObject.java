package jspot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Saša Milenković on 2016/12/14/
 */
public interface SPOTObject {
    Map<String, ?> object = new ConcurrentHashMap<>();
}
