package com.soreak.service;

import com.soreak.pojo.Blog;
import com.soreak.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);


    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(String query,Pageable pageable);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    List<Blog> listRecommendBlogTop(Integer size);


}
