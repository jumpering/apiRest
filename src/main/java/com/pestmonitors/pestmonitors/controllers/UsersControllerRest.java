package com.pestmonitors.pestmonitors.controllers;

import com.pestmonitors.pestmonitors.models.UserDTO;
import com.pestmonitors.pestmonitors.services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UsersControllerRest {

    @Autowired
    @Qualifier("LOCAL") //se puede obviar esto si usamos ConditionalOnProperty en las implementaciones del servicio
    UserService userService;

    //alta
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        //parametro: para grupos @Validated(GroupValidatorOnCreate.class) )
        //@NotNull(GroupValidatorOnCreate.class)
        this.userService.createUser(userDTO);

        //... aquí lo crearía en el service...
        //para responseEntity URL en el Head de la respuesta + código 201 de created
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //baja
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        ///aquí borro...
        this.userService.deleteById(id);
        return ResponseEntity.ok(null);
    }


    //modificación (2)
    @PutMapping("/users")   //con Put modifico usuario entero (todos los cambios)
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        ///aquí updateo...
        return userDTO;
    }

    @PatchMapping("/users/{id}")//con PatchMapping actualizo parcialmente el usuario (por ejemplo un atributo solo)
    public UserDTO updateUserAge(Map<String, Object> atributes, @PathVariable String id){
        UserDTO userDTO = new UserDTO(1,"xavi");
        //logica
        return userDTO;
    }


    //consulta (2s)
    @GetMapping("/users/{id}")
    @ApiOperation(notes = "Retrieve one user system by id", value = "Get user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Response ok if the operation was successful"),
            @ApiResponse(code = 404, message = "Response not found if the resource could not be found")
    })
    public ResponseEntity<UserDTO> getUserById(
            @ApiParam(example = "1", value = "Identifier for User", allowableValues = "1,2,3,4", required = true)
            @PathVariable Integer id){
        Optional<UserDTO> optUserDTO = userService.getUserById(id);

        //Recupero objeto del optional con un if, modo tradicional
//        if (!optUserDTO.isEmpty()){
//            UserDTO userDTO = optUserDTO.get();
//        }

        //Opción a redirigir a un método si presente
        //optUserDTO.ifPresent(u -> algunMetodo(u));

        //recupero objeto del optional con una Lambda, y lanzo excepción si no existe...
        try{
            UserDTO userDTO = optUserDTO.orElseThrow(NoSuchElementException::new);

            userDTO.setAge(43);
            userDTO.setLastName("Canals");
            Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
            //Link withAllRel = linkTo(methodOn(UsersControllerRest.class).getAllUsers()).withRel("Users");
            userDTO.add(withSelfRel);
            //userDTO.add(withAllRel);

            //ejemplo por si el service no lo encuentra NOT FOUND (o encuentra un null), devuelvo código error 404
    //        if (userDTO == null){
    //            return ResponseEntity.notFound().build();
    //        }
            return ResponseEntity.ok(userDTO);
         } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }





    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @RequestParam(required = false) String name
    ) {
        //...aquí lo buscaría, ejemplo mockeado TOT AIXÓ A LA CAPA DE NEGOCI Services
//        List<UserDTO> listUsers = List.of(new UserDTO(1, "Xavi"),
//                                          new UserDTO(2, "Marteta"));
        List<UserDTO> listUsers = this.userService.getAllUsers();

//        for (UserDTO userDTO : listUsers) {
//            Link withSelfRel = linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel();
//            userDTO.add(withSelfRel);
//        }
        listUsers.forEach(userDTO -> userDTO.add(linkTo(methodOn(UsersControllerRest.class).getUserById(userDTO.getId())).withSelfRel()));


        if (name != null) {
            listUsers = listUsers.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());
        }
        return ResponseEntity.ok(listUsers);
    }

}
