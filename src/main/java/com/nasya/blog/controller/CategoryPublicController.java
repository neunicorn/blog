package com.nasya.blog.controller;

import com.nasya.blog.model.request.category.GetCategoriesRequest;
import com.nasya.blog.model.response.category.GetCategoryResponse;
import com.nasya.blog.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryPublicController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> getCategories(
            @RequestParam(required = false, defaultValue = "0") Integer pageNo,
            @RequestParam(required = false, defaultValue = "0") Integer limit
    ){
        GetCategoriesRequest req = GetCategoriesRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();

        return ResponseEntity.ok(categoryServices.getCategories(req, false));
    }
}
