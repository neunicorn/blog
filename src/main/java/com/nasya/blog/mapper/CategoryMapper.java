package com.nasya.blog.mapper;

import com.nasya.blog.entity.Category;
import com.nasya.blog.entity.Comment;
import com.nasya.blog.model.request.category.CreateCategoryRequest;
import com.nasya.blog.model.request.comment.CreateCommentRequest;
import com.nasya.blog.model.response.category.CreateCategoryResponse;
import com.nasya.blog.model.response.category.GetCategoryResponse;
import com.nasya.blog.model.response.comment.CreateCommentResponse;
import com.nasya.blog.model.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category mapCreateCategory(CreateCategoryRequest request);

    CreateCategoryResponse mapCreateCategoryResponse(Category category);

    GetCategoryResponse mapGetCategoryResponse(Category category);
}
