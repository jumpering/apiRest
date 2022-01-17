package com.pestmonitors.pestmonitors.client;

import com.pestmonitors.pestmonitors.models.UserDTO;

public interface UserClient {

    public UserDTO getUserById(Integer id);
}
