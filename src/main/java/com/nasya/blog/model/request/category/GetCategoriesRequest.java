package com.nasya.blog.model.request.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCategoriesRequest {

    private Integer pageNo;
    private Integer limit;
}
