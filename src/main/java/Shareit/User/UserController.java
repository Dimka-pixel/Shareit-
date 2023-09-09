package Shareit.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable int id) {
        logger.info("response GET /users/{id} id = " + id);
        return userService.getUserById(id);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        logger.info("response GET /users");
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addUser(@Validated({AllMappingValidated.class, ExceptPatchMappingValidated.class})
                           @RequestBody UserDTO userDto) {
        logger.info("response POST /users");
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable int id) {
        logger.info("response Delete /users/{id} id = {}", id);
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@Validated(AllMappingValidated.class) @RequestBody UserDTO userDTO,
                              @PathVariable int id) {
        logger.info("response Patch /users/{id} id = {}", id);
        return userService.updateUser(userDTO, id);
    }


}
