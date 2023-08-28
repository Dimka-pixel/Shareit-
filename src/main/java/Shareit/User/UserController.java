package Shareit.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    //commit
    @Autowired
    private final UserServiceImpl userService;

    public UserController(@Autowired @Validated UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return userService.getUserById(id);

    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserDTO addUser(@Validated({AllMappingValidated.class, ExceptPatchMappingValidated.class})
                           @RequestBody UserDTO userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/users/{id}")
    public UserDTO updateUser(@Validated(AllMappingValidated.class) @RequestBody UserDTO userDTO,
                              @PathVariable int id) {
        return userService.updateUser(userDTO, id);
    }


}
