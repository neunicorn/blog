package com.nasya.blog.services;

import com.nasya.blog.entity.Category;
import com.nasya.blog.exception.ApiException;
import com.nasya.blog.mapper.CategoryMapper;
import com.nasya.blog.model.request.category.CreateCategoryRequest;
import com.nasya.blog.model.request.category.GetCategoriesRequest;
import com.nasya.blog.model.request.category.UpdateCategoryRequest;
import com.nasya.blog.model.response.category.CreateCategoryResponse;
import com.nasya.blog.model.response.category.DeleteCategoryResponse;
import com.nasya.blog.model.response.category.GetCategoryResponse;
import com.nasya.blog.model.response.category.UpdateCategoryResponse;
import com.nasya.blog.repository.CategoryRepository;
import com.nasya.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    public List<GetCategoryResponse> getCategories(GetCategoriesRequest req, boolean isDeleted){

        PageRequest page = PageRequest.of(req.getPageNo(), req.getLimit());
        List<Category> categories = categoryRepository.findByIsDeleted(isDeleted, page).getContent();

        if(categories.isEmpty()){
            throw new ApiException("CATEGORIES_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        return categories.stream().map(CategoryMapper.INSTANCE::mapGetCategoryResponse).toList();
    }

    public GetCategoryResponse getCategory(Integer id) {
         Category res = categoryRepository.findById(id)
                 .orElseThrow(()-> new ApiException("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND) );
        return CategoryMapper.INSTANCE.mapGetCategoryResponse(res);

    }

    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.mapCreateCategory(request);

        BigInteger date = BigInteger.valueOf(Instant.now().getEpochSecond());
        category.setCreatedAt(date);
        category.setUpdatedAt(date);
        categoryRepository.save(category);
        return CategoryMapper.INSTANCE.mapCreateCategoryResponse(category);
    }

    public UpdateCategoryResponse updateCategory(UpdateCategoryRequest request) {
        Category category = categoryRepository.findFirstById(request.getId())
                .orElseThrow(()-> new ApiException("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND));

        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setUpdatedAt(BigInteger.valueOf(Instant.now().getEpochSecond()));
        categoryRepository.save(category);

        return UpdateCategoryResponse.builder()
                .name(category.getName())
                .slug(category.getSlug())
                .build();
    }

    public DeleteCategoryResponse deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new ApiException("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND)
        );
        Integer countPostByCategory = postRepository.countPostByCategory(category);
        if(countPostByCategory != 0){
            throw new ApiException("CATEGORY_CANNOT_BE_DELETED", HttpStatus.BAD_REQUEST);
        }
        category.setDeleted(true);
        categoryRepository.save(category);
    return DeleteCategoryResponse.builder()
            .id(category.getId())
            .build();
    }
}
