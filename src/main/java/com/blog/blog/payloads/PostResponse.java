package com.blog.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private List<PostDto> content;

    private int pageNumber;

    private int pageSize;

    private Long totalElement;

    private int totalPage;
    private boolean lastPage;
}
