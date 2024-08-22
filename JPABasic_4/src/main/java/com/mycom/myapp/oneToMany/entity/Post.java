package com.mycom.myapp.oneToMany.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// owner entity
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;

	// #1 ~ 5번까지
	// #가
	@OneToMany
	private List<Comment> comments;

	// #6번까지
	// @OneToMany(cascade = CascadeType.PERSIST)
	// private List<Comment> comments;

	// #C번까지
	// @OneToMany(fetch = FetchType.EAGER)
	// private List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Post{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			'}';
	}
}
