package Shareit.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    public UserServiceImpl(@Autowired UserDAO userDao) {
        this.userDao = userDao;
    }

    @NotDuplicateEmail
    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = UserMapper.mapDtoToUser(userDto);
        userDao.addUser(user);
        return UserMapper.mapUserToDto(user);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userDao.getUserById(id);
        UserDTO userDTO;
        if (user != null) {
            userDTO = UserMapper.mapUserToDto(user);
        } else {
            throw new UserValidateException("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return userDTO;
    }

    @NotDuplicateEmail
    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User updateUser = userDao.getUserById(id);
        if (updateUser != null) {
            if (userDTO.getEmail() != null) {
                updateUser.setEmail(userDTO.getEmail());
            }
            if (userDTO.getName() != null) {
                updateUser.setName(userDTO.getName());
            }
        } else {
            throw new UserValidateException("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        userDao.userDelete(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        if (!userDao.getAllUsers().isEmpty()) {
            for (User user : userDao.getAllUsers().values()) {
                users.add(UserMapper.mapUserToDto(user));
            }
        }
        return users;
    }


}
