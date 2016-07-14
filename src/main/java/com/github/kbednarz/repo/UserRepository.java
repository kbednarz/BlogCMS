package com.github.kbednarz.repo;

import com.github.kbednarz.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kamil on 2016-06-29.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
}

