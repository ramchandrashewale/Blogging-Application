package com.blog.blog.service.impl;

import com.blog.blog.entities.Comment;
import com.blog.blog.entities.Post;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.CommentDto;
import com.blog.blog.repositories.CommentRepo;
import com.blog.blog.repositories.PostRepo;
import com.blog.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto crateComment(CommentDto commentDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postID",postId));

        Comment comment=modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment=commentRepo.save(comment);


        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        commentRepo.delete(comment);

    }
}
