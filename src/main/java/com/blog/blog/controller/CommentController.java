package com.blog.blog.controller;

import com.blog.blog.entities.Comment;
import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.CommentDto;
import com.blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto commentDto1=commentService.crateComment(commentDto,postId);

        return new ResponseEntity<>(commentDto1,HttpStatus.CREATED);
    }

    @DeleteMapping({"/postID"})
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully ", true),HttpStatus.OK);
    }
}
