package Shareit.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class DuplicateEmailValidator implements ConstraintValidator<NotDuplicateEmail, Object[]> {
    //commit

   /* @Autowired
    private UserService userService;*/

    @Override
    public void initialize(NotDuplicateEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

   /* @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        if(objects.length==1){
            UserDTO user = (UserDTO)objects[0];
            for (UserDTO userDTO : userService.getAllUsers()){
                if(user.getEmail().equals(userDTO.getEmail())){
                    if(userDTO.getId()!= user.getId()) {
                        return false;
                    }
                }

            }
        }else {
            int userId = (Integer) objects[1];
            UserDTO user = (UserDTO) objects[0];
            for (UserDTO userDTO : userService.getAllUsers()) {
                if (user.getEmail().equals(userDTO.getEmail())) {
                    if (userDTO.getId() != userId) {
                        return false;
                    }
                }

            }
        }
        return true;
    }*/
}
