package com.soreak.service;

import com.soreak.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
