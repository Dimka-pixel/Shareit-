package Shareit.User;

import Shareit.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;

@Repository
public class UserDAO implements Storage<User> {
    
    private int id;

    private HashSet<String> emails = new HashSet<>();

    private HashMap<Integer, User> users = new HashMap<>();

    @Override
    public User addObject(User user) {
            id++;
            emails.add(user.getEmail());
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
