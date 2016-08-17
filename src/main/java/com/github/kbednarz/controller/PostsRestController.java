package com.github.kbednarz.controller;

import com.github.kbednarz.model.PostEntity;
import com.github.kbednarz.repo.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.util.List;


@RestController
public class PostsRestController {
    @Autowired
    PostsRepository postsRepository;

    @RequestMapping("/rest/posts")
    public ResponseEntity<List<PostEntity>> showAllPosts(){
        List<PostEntity> postsEntities = (List) postsRepository.findAll();
        return new ResponseEntity<List<PostEntity>>(postsEntities, HttpStatus.OK);
    }

    @RequestMapping("/rest/posts/{id}")
    public ResponseEntity<PostEntity> showSpecificPost(@PathVariable Long id){
        PostEntity postsEntity = postsRepository.findOne(id);
        if (postsEntity == null) {
            return new ResponseEntity<PostEntity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PostEntity>(postsEntity,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rest/posts")
    public ResponseEntity<Void> createNewPost(@RequestBody PostEntity postsEntity, UriComponentsBuilder ucBuilder){
        postsEntity.setDate(new Date(System.currentTimeMillis()));
        postsRepository.save(postsEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rest/posts/{id}").buildAndExpand(postsEntity.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PostEntity> updatePost(@PathVariable("id") long id, @RequestBody PostEntity updatedPostEntity) {
        PostEntity currentPostEntity = postsRepository.findOne(id);
        if (currentPostEntity == null) {
            return new ResponseEntity<PostEntity>(HttpStatus.NOT_FOUND);
        }

        currentPostEntity.setAuthor(updatedPostEntity.getAuthor());
        currentPostEntity.setTitle(updatedPostEntity.getTitle());
        currentPostEntity.setContent(updatedPostEntity.getContent());

        postsRepository.save(currentPostEntity);

        return new ResponseEntity<PostEntity>(currentPostEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PostEntity> deletePost(@PathVariable("id") long id) {
        PostEntity postEntity = postsRepository.findOne(id);
        if (postEntity == null) {
            return new ResponseEntity<PostEntity>(HttpStatus.NOT_FOUND);
        }

        postsRepository.delete(id);

        return new ResponseEntity<PostEntity>(HttpStatus.NO_CONTENT);
    }


}
