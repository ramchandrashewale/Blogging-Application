package com.blog.blog.payloads;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.Comment;
import com.blog.blog.entities.User;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer post_id;
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;
    private  UserDto user;

    private Set<CommentDto> comments=new HashSet<>();
}
