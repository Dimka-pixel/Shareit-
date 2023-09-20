package Shareit;

import java.util.HashMap;

public interface Storage<T> {

    T addObject(T t);

    T getObjectById(int id);

    HashMap<Integer, T> getAllObjects();

    void deleteObject(int id);

}
