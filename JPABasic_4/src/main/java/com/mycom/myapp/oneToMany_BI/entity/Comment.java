package com.mycom.myapp.oneToMany_BI.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// owner entity
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private String content;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

// // //#1~8번 , 10번 , A~C
	// @ManyToOne
	// @JoinColumn(name = "post_id") //Comment 테이블에 post_id 컬럼이 생긴다.
	// private Post post;

	// // #9번
	// @ManyToOne(cascade = CascadeType.PERSIST)
	// @JoinColumn(name = "post_id") //Comment 테이블에 post_id 컬럼이 생긴다.
	// private Post post;

	// D번
	@ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
	@JoinColumn(name = "post_id") //Comment 테이블에 post_id 컬럼이 생긴다.
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


}
