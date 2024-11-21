package com.nasya.blog.services;

import com.nasya.blog.entity.Comment;
import com.nasya.blog.entity.Post;
import com.nasya.blog.repository.CommentRepository;
import com.nasya.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private PostRepository postRepository;

    @Transactional
    public Comment createPost(Comment request, Integer post_id){
        if(Objects.isNull(request)){
            return null;
        }
        Post post = postRepository.findById(post_id).orElse(null);
        if(post == null){
            return null;
        }


        request.setCreated_at(BigInteger.valueOf(Instant.now().getEpochSecond()));
        request.setPost(post);

        request = commentRepository.save(request);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return request;
    };

    public List<Comment> getComments(String postSlug, Integer pageNo, Integer limit){
        if(Objects.isNull(postSlug)) return null;

        Post post = postRepository.findBySlugAndIsDeleted(postSlug, false).orElse(null);
        if(Objects.isNull(post)) return null;
        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        return commentRepository.findByPostId(post.getId(), pageRequest).getContent();
    };

    public Comment getComment(Integer comment_id){
        if(Objects.isNull(comment_id)) return null;
        return commentRepository.findById(comment_id).orElse(null);
    }
}
