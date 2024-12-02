package com.nasya.blog.services;

import com.nasya.blog.entity.Category;
import com.nasya.blog.entity.Post;
import com.nasya.blog.exception.ApiException;
import com.nasya.blog.mapper.PostMapper;
import com.nasya.blog.model.request.post.CreatePostRequest;
import com.nasya.blog.model.request.post.GetPostsRequest;
import com.nasya.blog.model.request.post.UpdatePostRequest;
import com.nasya.blog.model.response.post.*;
import com.nasya.blog.repository.CategoryRepository;
import com.nasya.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<GetPostResponse> getPosts(GetPostsRequest request){

        PageRequest pageRequest = PageRequest.of(request.getPageNo(), request.getLimit());
        List<Post> posts = postRepository.findAllByIsDeleted(false, pageRequest).getContent();
        return posts.stream().map(PostMapper.INSTANCE::mapGetResponse).toList();
    }

    public GetPostResponse getPostBySlug(String slug){
        Post post = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(()-> new ApiException("POST_NOT_FOUND", HttpStatus.NOT_FOUND));
        return PostMapper.INSTANCE.mapGetResponse(post);
    }

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ApiException("Category_not_fodund", HttpStatus.BAD_REQUEST));

        Post post = PostMapper.INSTANCE.mapCreatePostRequest(request);
        post.setCategory(category);
        post.setCreatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        post.setUpdatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        post.setCommentCount(0);
        postRepository.save(post);

        return PostMapper.INSTANCE.mapCreatePostResponse(post);
    }

    public UpdateBySlugPostResponse updatePostBySlug(String slug, UpdatePostRequest request){
        Post post = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(()-> new ApiException("POST_NOT_FOUND", HttpStatus.NOT_FOUND));

        post.setBody(request.getBody());
        post.setTitle(request.getTitle());
        post.setSlug(request.getSlug());
        post.setUpdatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        postRepository.save(post);

        return PostMapper.INSTANCE.mapUpdatePostResponse(post);
    }

    public DeletePostByIdResponse deletePostById(Integer id){
        Post postForDelete = postRepository.findById(id)
                .orElseThrow(()-> new ApiException("POST_NOT_FOUND", HttpStatus.NOT_FOUND));

        //implement soft delete
        postForDelete.setDeleted(true);
        postRepository.save(postForDelete);

        return DeletePostByIdResponse.builder().postId(id).build();
    }

    public PublishPostResponse publishPost(Integer id){
        Post searchPost = postRepository.findById(id)
                .orElseThrow(()-> new ApiException("POST_NOT_FOUND", HttpStatus.NOT_FOUND));

        searchPost.setPublishedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        searchPost.setPublished(true);
        postRepository.save(searchPost);

        return PublishPostResponse.builder().publishedAt(searchPost.getPublishedAt()).build();
    }
}
