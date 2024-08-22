package com.mycom.myapp.manyToOne.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// owner entity
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private String content;

	//# 1~7번까지
	// @ManyToOne
	// private Post post;

	//# 8번
	// @ManyToOne(cascade = CascadeType.PERSIST)
	// private Post post;

	// #C
	@ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
	private Post post;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
