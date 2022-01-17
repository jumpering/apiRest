package com.pestmonitors.pestmonitors.models;

import com.pestmonitors.pestmonitors.validators.DNI;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel("Usuario del sistema")
//@Entity(name = "ms_users")
public class UserDTO extends RepresentationModel<UserDTO> {

    @NonNull
    @ApiModelProperty(notes = "Identificador único para el user", example = "1", required = true)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@Id
    private Integer id;

    @NonNull
    private String name;

    //@NotNull
    //@NotBlank
    @Size(min = 2)
    private String lastName;

    private String firstName;

    //@NotNull
    @Positive
    @Min(18)
    @ToString.Exclude
    private Integer age;

    //@NotNull
    @Email
    private String email;

    @AssertTrue
    //ejemplo grupos validator
    //@Assertrue (message = "txt", groups = GroupValidatorOnCreate.class)
    //@Asserfalse (message = "txt", groups = GroupValidatorOnUpdate.class)
    private boolean active;

    @Past
    //@FutureOrPresent
    private LocalDate birthday;

    //@DNI
    //@NotNull
    private String DNI;

    private String body;

    private Integer posttId;
    
}
