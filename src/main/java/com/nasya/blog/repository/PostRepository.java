package com.nasya.blog.repository;

import com.nasya.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findBySlug(String slug);
    Optional<Post> findBySlugAndDeleted(String slug, boolean isDeleted);
}
