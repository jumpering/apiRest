package com.pestmonitors.pestmonitors.dao.entities;

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
@Entity(name = "ms_users")
//@Table(name = "ms_users")
public class UserEntity extends RepresentationModel<UserEntity> {

    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;

    @NonNull
    private String name;

    //@NotNull
    //@NotBlank
    private String lastName;

    private String firstName;

    //@NotNull
    private Integer age;

    //@NotNull
    private String email;

    //ejemplo grupos validator
    //@Assertrue (message = "txt", groups = GroupValidatorOnCreate.class)
    //@Asserfalse (message = "txt", groups = GroupValidatorOnUpdate.class)
    private boolean active;

    //@FutureOrPresent
    private LocalDate birthday;

    //@DNI
    //@NotNull
    private String DNI;

    private String body;

    private Integer posttId;
    
}
