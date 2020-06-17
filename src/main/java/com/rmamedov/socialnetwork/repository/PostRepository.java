package com.rmamedov.socialnetwork.repository;

import com.rmamedov.socialnetwork.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {
}
