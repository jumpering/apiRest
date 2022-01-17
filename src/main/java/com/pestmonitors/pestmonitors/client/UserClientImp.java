package com.pestmonitors.pestmonitors.client;

import com.pestmonitors.pestmonitors.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//para recuperar de un recurso externo, recoje un JSON y lo convierte a una clase
@Service
public class UserClientImp implements UserClient{

    //mejor así que no hacer el new en la implementación, por si quiero seteaar configuración global al restTemplate (por ejemplo un timeuot para todas las llamadas)
//    @Autowired
//    private RestTemplate restTemplate;

    @Override
    public UserDTO getUserById(Integer id) {

        RestTemplate restTemplate = new RestTemplate();
        String sourceResourceUrl = "https://jsonplaceholder.typicode.com/comments/1";
        UserDTO userDTO = restTemplate.getForObject(sourceResourceUrl, UserDTO.class);
        return userDTO;
    }
}
