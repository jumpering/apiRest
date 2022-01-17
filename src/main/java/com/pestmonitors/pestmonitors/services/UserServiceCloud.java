package com.pestmonitors.pestmonitors.services;

import com.pestmonitors.pestmonitors.client.UserClient;
import com.pestmonitors.pestmonitors.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("CLOUD")
//@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Professional")
public class UserServiceCloud implements UserService{

    @Autowired
    private UserClient userClient;

    public Optional<UserDTO> getUserById(Integer id){

        //desde cliente para recurso externo
        UserDTO userDTO = userClient.getUserById(id); //EJEMPLO CLIENTE PARA RECURSO EXTERNO (otro API Rest por ejemplo)
        return Optional.ofNullable(userDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserDTO> createUser(UserDTO userDTO) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }
}
