package com.github.kbednarz.repo;

import com.github.kbednarz.model.PostEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kamil on 2016-06-28.
 */
public interface PostsRepository extends CrudRepository<PostEntity, Long> {
    //List<PostsEntity> findByTitle(String title);
}
