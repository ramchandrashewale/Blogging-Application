package com.blog.blog.controller;

import com.blog.blog.config.AppConstants;
import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.PostDto;
import com.blog.blog.payloads.PostResponse;
import com.blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> cratePost(@RequestBody PostDto postDto, @PathVariable Integer userId ,@PathVariable Integer categoryId){
        PostDto cratePost=postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostDto>(cratePost, HttpStatus.CREATED);

    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }
    @GetMapping("/category/{categoryId}/post")
    public  ResponseEntity<PostResponse> getByCategory(@PathVariable Integer categoryId ,@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize){
        PostResponse postDtoList=postService.getPostsByCategory(categoryId,pageNumber,pageSize);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/allPost")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                   @RequestParam(value ="sortDir" ,defaultValue=AppConstants.SORT_DIR,required = false )String sortDir
                                                    ){

        PostResponse postDtos=postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("getSingle/{postId}")
    public ResponseEntity<PostDto> getByPostID(@PathVariable Integer postId){
        PostDto postDto=postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post is deleted successfully",true),HttpStatus.OK);
    }
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
     PostDto postDto1=   postService.updatePost(postDto,postId);
     return  new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable  String keyword){
        List<PostDto> postDtos=postService.searchPosts(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

}
