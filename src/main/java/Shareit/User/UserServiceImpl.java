package Shareit.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @NotDuplicateEmail
    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = UserMapper.mapDtoToUser(userDto);
        userDao.addObject(user);
        UserDTO userDTO = UserMapper.mapUserToDto(user);
        logger.info("return {}", userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userDao.getObjectById(id);
        UserDTO userDTO;
        if (user != null) {
            userDTO = UserMapper.mapUserToDto(user);
        } else {
            logger.warn("User with id = {} not found", id);
            throw new UserValidateException("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        logger.info("return" + userDTO);
        return userDTO;
    }

    @NotDuplicateEmail
    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User updateUser = Optional.ofNullable(userDao.getObjectById(id)).orElseThrow();
        if (userDTO.getEmail() != null) {
            if (!userDTO.getEmail().isBlank()) {
                updateUser.setEmail(userDTO.getEmail());
            } else {
                logger.warn("The field \"Email\" is blank");
                throw new UserValidateException("The field \"Email\" should not be empty", HttpStatus.BAD_REQUEST);
            }
        }
        if (userDTO.getName() != null) {
            if (!userDTO.getName().isBlank()) {
                updateUser.setName(userDTO.getName());
            } else {
                logger.warn("The field \"name\" is blank");
                throw new UserValidateException("The field \"name\" should not be empty", HttpStatus.BAD_REQUEST);
            }

        }
        return getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        logger.info("Delete user with id = {}", id);
        userDao.deleteObject(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        if (!userDao.getAllObjects().isEmpty()) {
            for (User user : userDao.getAllObjects().values()) {
                users.add(UserMapper.mapUserToDto(user));
            }
        }
        logger.info("return" + users);
        return users;
    }


}
