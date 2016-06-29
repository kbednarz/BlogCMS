package com.github.kbednarz.repo;

import com.github.kbednarz.model.PostsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Kamil on 2016-06-28.
 */
public interface PostsRepository extends CrudRepository<PostsEntity, Long> {
    //List<PostsEntity> findByTitle(String title);
}
