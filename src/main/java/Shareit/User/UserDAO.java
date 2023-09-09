package Shareit.User;

import Shareit.Storage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDAO implements Storage<User> {
    
    private int id;
    private HashMap<Integer, User> users = new HashMap<>();

    @Override
    public User addObject(User user) {
        id++;
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public User getObjectById(int id) {
        return users.get(id);
    }

    @Override
    public HashMap<Integer, User> getAllObjects() {
        return users;
    }

    @Override
    public void deleteObject(int id) {
        users.remove(id);
    }
}
