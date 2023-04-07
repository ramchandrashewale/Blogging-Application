package com.blog.blog.payloads;

import com.blog.blog.entities.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private int id;

    private String content;


}
