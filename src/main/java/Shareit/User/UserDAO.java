package Shareit.User;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDAO {
    //commit
    private int id;
    private HashMap<Integer, User> users = new HashMap<>();

    public User addUser(User user) {
        id++;
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public void userDelete(int id) {
        users.remove(id);
    }

    public HashMap<Integer, User> getAllUsers() {
        return users;
    }

}
