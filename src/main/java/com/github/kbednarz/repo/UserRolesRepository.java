package com.github.kbednarz.repo;

import com.github.kbednarz.model.UserRoles;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {

    @Query("SELECT a.role FROM UserRoles a, UserEntity b WHERE b.username=?1 AND a.userId=b.id")
    public List<String> findRoleByUserName(String name);
}
