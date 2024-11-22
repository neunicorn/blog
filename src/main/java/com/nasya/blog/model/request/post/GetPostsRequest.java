package com.nasya.blog.model.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GetPostsRequest {

    private Integer pageNo;
    private Integer limit;
}
