package com.nasya.blog.mapper;

import com.nasya.blog.entity.Post;
import com.nasya.blog.model.request.post.CreatePostRequest;
import com.nasya.blog.model.response.post.CreatePostResponse;
import com.nasya.blog.model.response.post.GetPostResponse;
import com.nasya.blog.model.response.post.UpdateBySlugPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post mapCreatePostRequest(CreatePostRequest request);

    UpdateBySlugPostResponse mapUpdatePostResponse(Post response);

    CreatePostResponse mapCreatePostResponse(Post post);

    GetPostResponse mapGetResponse(Post post);
}
