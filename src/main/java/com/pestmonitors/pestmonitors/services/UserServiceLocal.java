package com.pestmonitors.pestmonitors.services;

import com.pestmonitors.pestmonitors.client.UserClient;
import com.pestmonitors.pestmonitors.dao.entities.UserEntity;
import com.pestmonitors.pestmonitors.dao.repositories.UserRepository;
import com.pestmonitors.pestmonitors.models.UserDTO;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("LOCAL")
//@Primary   ...implementación prioritaria
//@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Community")
public class UserServiceLocal implements UserService{

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<UserDTO> getUserById(Integer id) {

        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(id);
        UserEntity userEntity = optionalUserEntity.get();
        //modo rudo
        //UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getName());
        //modo modelMapper
        UserDTO userDTO = this.modelMapper.map(userEntity, UserDTO.class);
        return Optional.ofNullable(userDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntityList = this.userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
//        for (UserEntity userEntity : userEntityList){
//            userDTOList.add(this.modelMapper.map(userEntity, UserDTO.class));
//        }
        userEntityList.forEach(user -> userDTOList.add(this.modelMapper.map(user, UserDTO.class)));

        return userDTOList;
    }

    @Override
    public Optional<UserDTO> createUser(UserDTO userDTO) {
        UserEntity userEntity = this.modelMapper.map(userDTO, UserEntity.class);
        userEntity = this.userRepository.save(userEntity);
        userDTO = this.modelMapper.map(userEntity, UserDTO.class);
        return Optional.ofNullable(userDTO);
    }

    @Override
    public void deleteById(Integer id) {
        this.userRepository.deleteById(id);
    }
}
