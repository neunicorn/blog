package com.nasya.blog.repository;

import com.nasya.blog.entity.Category;
import com.nasya.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


    Page<Post> findAllByIsDeleted(boolean isDeleted, Pageable pageable);

    Optional<Post> findFirstBySlugAndIsDeleted(String slug, boolean isDeleted);

    Integer countPostByCategory(Category category);
}
