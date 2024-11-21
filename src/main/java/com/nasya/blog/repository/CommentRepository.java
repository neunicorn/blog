package com.nasya.blog.repository;

import com.nasya.blog.entity.Comment;
import com.nasya.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByPostId(Integer postId, Pageable pageable);
}
