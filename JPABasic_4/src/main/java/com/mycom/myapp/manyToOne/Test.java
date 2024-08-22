package com.mycom.myapp.manyToOne;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.manyToOne.entity.Comment;
import com.mycom.myapp.manyToOne.entity.Post;
import com.mycom.myapp.manyToOne.config.MyPersistenceUnitInfo;
import com.mycom.myapp.oneToOne.entity.Passport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

// org.hibernate.UnknownEntityTypeException: Unable to locate entity descriptor: com.mycom.myapp.entity.Employee
// HashMap 에 몇 가지 설정
public class Test {

	public static void main(String[] args) {
		
		Map<String, String> props = new HashMap();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update"); // create (항상 새로 만든다), update ( 없으면 만들고, 있으면 변경되어야 하면 변경 )
		// hbm2ddl <= Hibernate Mapping to(2) DDL 
		EntityManagerFactory emf = 
				new HibernatePersistenceProvider().createContainerEntityManagerFactory(
						new MyPersistenceUnitInfo(), props
				);
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();

			// #1
			// table 생성
			// 2개의 테이블 Post,Comment 만들어진다. Comment 테이블에 Post_id FK 컬럼이 만들어 진다.
			// {
			//
			// }

			// // #2
			// {
			// 	Post post = new Post();
			// 	post.setTitle("Post");
			// 	post.setContent("Post 1 Content");
			// 	em.persist(post);
			// }

			// // #3
			// {
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1 Content");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2 Content");
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			//
			// }

			// //#4
			// // Post 1, Comment 2 생성
			// // Post, Comment 모두 연결
			// // Post 1개만 persist , Post 1건 insert 정상
			// {
			//
			// 	Post post = new Post();
			// 	post.setTitle("Post");
			// 	post.setContent("Post 1 Content");
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1 Content");
			// 	c1.setPost(post);
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2 Content");
			// 	c2.setPost(post);
			//
			// 	em.persist(post);
			//
			// }

			//#5
			// Post 1, Comment 2 생성
			// Post, Comment 모두 연결
			// Comment 2건만 persist , 에러 발생
			// {
			//
			// 	Post post = new Post();
			// 	post.setTitle("Post");
			// 	post.setContent("Post 1 Content");
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1 Content");
			// 	c1.setPost(post);
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2 Content");
			// 	c2.setPost(post);
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			//
			// }

			// //#6
			// // Post 1, Comment 2 생성
			// // Post, Comment 모두 연결
			// // Comment,Post persist
			// // Comment 2건, Post 1건 insert 정상
			// {
			//
			// 	Post post = new Post();
			// 	post.setTitle("Post");
			// 	post.setContent("Post 1 Content");
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1 Content");
			// 	c1.setPost(post);
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2 Content");
			// 	c2.setPost(post);
			//
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: update Comment set content=?,post_id=? where id=?
			// 	// Hibernate: update Comment set content=?,post_id=? where id=?
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			// 	em.persist(post);
			//
			// }


			// //#7
			// // Post 1, Comment 2 생성
			// // Post, Comment 모두 연결
			// // Comment,Post persist , 포스트를 먼
			// // Comment 2건, Post 1건 insert 정상
			// {
			//
			// 	Post post = new Post();
			// 	post.setTitle("Post");
			// 	post.setContent("Post 1 Content");
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1 Content");
			// 	c1.setPost(post);
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2 Content");
			// 	c2.setPost(post);
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			//
			// 	em.persist(post);
			// 	em.persist(c1);
			// 	em.persist(c2);
			//
			//
			// }

			// //#8
			// // Post 1, Comment 2 생성
			// // Post, Comment 모두 연결
			// // Comment casacade persist
			// // Comment 2건, Post 1건 insert 정상
			// {
			//
			// 	Post post = new Post();
			// 	post.setTitle("Post");
			// 	post.setContent("Post 1 Content");
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1 Content");
			// 	c1.setPost(post);
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2 Content");
			// 	c2.setPost(post);
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			//
			// 	// em.persist(post);
			// 	em.persist(c1);
			// 	em.persist(c2);
			//
			//
			// }


			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
