package Shareit.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = UserMapper.mapDtoToUser(userDto);
        HashSet<String> emails = new HashSet<>();
        for(UserDTO userDtoCheckEmail : getAllUsers()){
            emails.add(userDtoCheckEmail.getEmail());
        }
        if(!emails.contains(userDto.getEmail())) {
            userDao.addObject(user);
        } else {
            throw new UserValidateException("This email address already used", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDTO userDTO = UserMapper.mapUserToDto(user);
        log.info("return {}", userDTO);
        return userDTO;

    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userDao.getObjectById(id);
        UserDTO userDTO;
        if (user != null) {
            userDTO = UserMapper.mapUserToDto(user);
        } else {
            log.warn("User with id = {} not found", id);
            throw new UserValidateException("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        log.info("return {}", userDTO);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        User updateUser = Optional.ofNullable(userDao.getObjectById(id)).orElseThrow();
        if (userDTO.getEmail() != null) {
            if (!userDTO.getEmail().isBlank()) {
                for(UserDTO userDtoCheckEmail : getAllUsers()){
                    if(userDtoCheckEmail.getEmail().equals(userDTO.getEmail())&&userDtoCheckEmail.getId()!=id){
                        log.warn("The field \"Email\" is exists");
                        throw new UserValidateException("This email already used", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
                updateUser.setEmail(userDTO.getEmail());
            } else {
                log.warn("The field \"Email\" is blank");
                throw new UserValidateException("The field \"Email\" should not be empty", HttpStatus.BAD_REQUEST);
            }
        }
        if (userDTO.getName() != null) {
            if (!userDTO.getName().isBlank()) {
                updateUser.setName(userDTO.getName());
            } else {
                log.warn("The field \"name\" is blank");
                throw new UserValidateException("The field \"name\" should not be empty", HttpStatus.BAD_REQUEST);
            }

        }
        return getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        log.info("Delete user with id = {}", id);
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
        log.info("return {}", users);
        return users;
    }


}
