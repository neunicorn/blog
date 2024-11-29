package com.nasya.blog.controller;

import com.nasya.blog.model.request.category.CreateCategoryRequest;
import com.nasya.blog.model.request.category.GetCategoriesRequest;
import com.nasya.blog.model.request.category.UpdateCategoryRequest;
import com.nasya.blog.model.response.category.CreateCategoryResponse;
import com.nasya.blog.model.response.category.DeleteCategoryResponse;
import com.nasya.blog.model.response.category.GetCategoryResponse;
import com.nasya.blog.model.response.category.UpdateCategoryResponse;
import com.nasya.blog.services.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryAdminController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> getCategories(
            @RequestParam(required = false, defaultValue = "0") Integer pageNo,
            @RequestParam(required = false, defaultValue = "0") Integer limit,
            @RequestParam(required = true) boolean isDeleted
    ){
        GetCategoriesRequest req = GetCategoriesRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();

        return ResponseEntity.ok(categoryServices.getCategories(req, isDeleted));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryServices.getCategory(id));

    }

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> createCategory(
            @Valid @RequestBody CreateCategoryRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryServices.createCategory(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCategoryResponse> updateCategory(
            @Valid @RequestBody UpdateCategoryRequest request,
            @PathVariable Integer id
    ){
        request.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryServices.updateCategory(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCategoryResponse> deleteCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryServices.deleteCategory(id));
    }


}
