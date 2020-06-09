package ictgradschool.project.controller;

import ictgradschool.project.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentListController {

    public List<Comment> getCommentListByArticleId(int articleId){
        List<Comment> commentList = new ArrayList<>();

        Comment comment = new Comment();
        comment.articleId = 1;
        comment.content = "Good article!";
        comment.date = 1591686802;
        comment.userName = "commenter";
        comment.id = 1;
        commentList.add(comment);

        return commentList;
    }
}
