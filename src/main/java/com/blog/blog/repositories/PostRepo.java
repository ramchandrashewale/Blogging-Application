package com.blog.blog.repositories;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post ,Integer> {

    List<Post> findByUser(User user);
    Page<Post> findByCategory(Category category, Pageable pageable);

    List<Post> findByTitleContaining(String title);
}
