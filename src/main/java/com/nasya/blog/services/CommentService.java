package com.nasya.blog.services;

import com.nasya.blog.entity.Comment;
import com.nasya.blog.entity.Post;
import com.nasya.blog.repository.CommentRepository;
import com.nasya.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public Comment createPost(Comment request, Integer post_id){
        if(Objects.isNull(request)){
            return null;
        }

        Post post = postRepository.findById(post_id).orElse(null);

        Comment commentDummy= new Comment();
        commentDummy.setPost(post);
        commentDummy.setCreated_at(BigInteger.valueOf(Instant.now().getEpochSecond()));

        return commentRepository.save(commentDummy);
    };

    public List<Comment> getComments(String postSlug, Integer pageNo, Integer limit){
        if(Objects.isNull(postSlug)) return null;

        Post post = postRepository.findBySlug(postSlug).orElse(null);
        return commentRepository.findCommentByPost(post);
    };

    public Comment getComment(Integer comment_id){
        if(Objects.isNull(comment_id)) return null;
        return commentRepository.findById(comment_id).orElse(null);
    }
}
