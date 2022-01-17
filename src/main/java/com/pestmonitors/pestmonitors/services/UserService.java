package com.pestmonitors.pestmonitors.services;

import com.pestmonitors.pestmonitors.models.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<UserDTO> getUserById(Integer id);

    public List<UserDTO> getAllUsers();

    public Optional<UserDTO>  createUser(UserDTO userDTO);
    
    public void deleteById(Integer id);
}