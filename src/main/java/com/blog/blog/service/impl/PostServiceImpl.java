package com.blog.blog.service.impl;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.PostDto;
import com.blog.blog.payloads.PostResponse;
import com.blog.blog.repositories.CategoryRepository;
import com.blog.blog.repositories.PostRepo;
import com.blog.blog.repositories.UserRepo;
import com.blog.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post","postID",postId));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());

        Post newPost=postRepo.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {


        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryID",categoryId));

        Pageable p=PageRequest.of(pageNumber,pageSize);

        Page<Post> page=postRepo.findByCategory(category,p);

        List<Post> posts= page.getContent();
        List<PostDto> postDtos=posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


       PostResponse postResponse=new PostResponse();
       postResponse.setContent(postDtos);
       postResponse.setPageNumber(page.getNumber());
       postResponse.setPageSize(page.getSize());
       postResponse.setTotalElement(page.getTotalElements());
       postResponse.setTotalPage(page.getTotalPages());
       postResponse.setLastPage(page.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> posts=postRepo.findByUser(user);
        List<PostDto> postDtosList=posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtosList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtosList=posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtosList;
    }
}
