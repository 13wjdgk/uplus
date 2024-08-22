package com.mycom.myapp.manyToOne.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// no owner entity
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;

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
