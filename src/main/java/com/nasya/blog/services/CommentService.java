package com.nasya.blog.services;

import com.nasya.blog.entity.Comment;
import com.nasya.blog.entity.Post;
import com.nasya.blog.exception.ApiException;
import com.nasya.blog.mapper.CommentMapper;
import com.nasya.blog.model.request.comment.CreateCommentRequest;
import com.nasya.blog.model.response.comment.CreateCommentResponse;
import com.nasya.blog.model.response.comment.GetCommentResponse;
import com.nasya.blog.repository.CommentRepository;
import com.nasya.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request){
        if(Objects.isNull(request)){
            throw new ApiException("BAD_REQUEST", HttpStatus.BAD_REQUEST);
        }
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPost().getSlug(), false)
                .orElseThrow(()-> new ApiException("POST_NOT_FOUND", HttpStatus.NOT_FOUND));

        Comment comment= CommentMapper.INSTANCE.mapCreateComment(request);
                comment.setCreatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
                comment.setPost(post);

        commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapCreateCommentResponse(comment);
    };

    public List<GetCommentResponse> getComments(String postSlug, Integer pageNo, Integer limit){

        Post post = postRepository.findFirstBySlugAndIsDeleted(postSlug, false)
                .orElseThrow(()-> new ApiException("POST_NOT_FOUND", HttpStatus.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(pageNo, limit);
        List<Comment> comments = commentRepository.findByPostId(post.getId(), pageRequest).getContent();

        return comments.stream().map(CommentMapper.INSTANCE::mapGetCommentResponse)
                .collect(Collectors.toList());
    };

    public GetCommentResponse getComment(Integer comment_id){
        if(Objects.isNull(comment_id)) return null;
        Comment comment = commentRepository.findById(comment_id)
                .orElseThrow(()-> new ApiException("COMMENT_NOT_FOUND", HttpStatus.NOT_FOUND));
        return CommentMapper.INSTANCE.mapGetCommentResponse(comment);
    }
}
