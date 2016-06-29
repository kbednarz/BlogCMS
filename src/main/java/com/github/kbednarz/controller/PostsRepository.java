package com.github.kbednarz.controller;

import com.github.kbednarz.model.PostsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kamil on 2016-06-28.
 */
public interface PostsRepository extends CrudRepository<PostsEntity, Long> {
}
