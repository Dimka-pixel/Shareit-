package Shareit.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id;
    @NotBlank(groups = ExceptPatchMappingValidated.class)
    private String name;
    @Email(groups = AllMappingValidated.class)
    @NotNull(groups = ExceptPatchMappingValidated.class)
    private String email;

}
