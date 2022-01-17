package com.pestmonitors.pestmonitors.dao.repositories;

import com.pestmonitors.pestmonitors.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public UserEntity getByNameContains(String contains);
}
