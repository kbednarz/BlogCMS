package com.github.kbednarz.repo;

import com.github.kbednarz.model.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<PostEntity, Long> {
    //List<PostsEntity> findByTitle(String title);
}
