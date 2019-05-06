package com.mybaits.demo.entity;

public class Blog {
    private Integer bid;

    private String name;

    private Integer authorId;

    private Author author;

    private Comment comment;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }


    public Comment getComment() {
        //保存到数据库的
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}