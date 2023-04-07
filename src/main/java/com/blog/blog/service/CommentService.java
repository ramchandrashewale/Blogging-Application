package com.blog.blog.service;

import com.blog.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto crateComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
