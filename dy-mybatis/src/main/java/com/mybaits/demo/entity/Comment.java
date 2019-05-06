package com.mybaits.demo.entity;

/**
 * 评论
 */
public class Comment {
    private Integer commentId;

    private String content;

    private Integer bid;

    private String aaa ;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}