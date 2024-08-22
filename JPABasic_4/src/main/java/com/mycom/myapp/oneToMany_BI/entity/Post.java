package com.mycom.myapp.oneToMany_BI.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Non - owner entity
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private String title;
	private String content;

	// # 1번부터 9번까지 , A~D
	@OneToMany(mappedBy = "post") // 양방향, 오너쉽이 없음을 알려줌 , 양방향으로 연결되어야 하는 자기 자신(필드명)을 적어준다.
	private List<Comment> comments;

	// // 10번
	// @OneToMany(cascade = CascadeType.PERSIST) //Post에 mappedBy 빼면 Post가 owner entity가 된다.
	// private List<Comment> comments;

	// C
	// @OneToMany(mappedBy = "post",fetch = FetchType.EAGER) // 양방향, 오너쉽이 없음을 알려줌 , 양방향으로 연결되어야 하는 자기 자신(필드명)을 적어준다.
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
