package com.nasya.blog.mapper;

import com.nasya.blog.entity.Comment;
import com.nasya.blog.model.request.comment.CreateCommentRequest;
import com.nasya.blog.model.response.comment.CreateCommentResponse;
import com.nasya.blog.model.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment mapCreateComment(CreateCommentRequest request);

    CreateCommentResponse mapCreateCommentResponse(Comment comment);

    GetCommentResponse mapGetCommentResponse(Comment comment);
}
