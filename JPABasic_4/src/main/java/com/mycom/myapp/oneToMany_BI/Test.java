package com.mycom.myapp.oneToMany_BI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.oneToMany_BI.config.MyPersistenceUnitInfo;
import com.mycom.myapp.oneToMany_BI.entity.Comment;
import com.mycom.myapp.oneToMany_BI.entity.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

// org.hibernate.UnknownEntityTypeException: Unable to locate entity descriptor: com.mycom.myapp.entity.Employee
// HashMap 에 몇 가지 설정
public class Test {

	public static void main(String[] args) {
		
		Map<String, String> props = new HashMap();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create"); // create (항상 새로 만든다), update ( 없으면 만들고, 있으면 변경되어야 하면 변경 )
		// hbm2ddl <= Hibernate Mapping to(2) DDL 
		EntityManagerFactory emf = 
				new HibernatePersistenceProvider().createContainerEntityManagerFactory(
						new MyPersistenceUnitInfo(), props
				);
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			// #1
			{

			}

			// #2
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			// 	em.persist(p);
			// }

			// #3 Comment 2개 생성
			// Insert는 되지만, Post는 null이 들어감
			//  Column 'post_id' cannot be null
			// DB에서  post_id NOTNULL 설정

			// {
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			// }

			// // #4 Post 1개, Comment 2개 생성
			// // Post 중심으로  Comment 연결
			// // 1건만 insert
			// //@OnToMany 실습에서는 오류가 발생했었음, 그 이유는 Post가 Owner Entity를 갖고 있었기 때문
			// // 현재는 Comment가 Owner Entity를 갖고 있음
			//
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			// 	em.persist(p);
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	//post 중심으로 연결
			// 	p.setComments(List.of(c1, c2));
			// 	//post만 persist
			// 	em.persist(p);
			//
			// }


			// #5 Post 1개, Comment 2개 생성
			// Post 중심으로  Comment 연결
			// 1건만 insert
			//@OnToMany 실습에서는 오류가 발생했었음, 그 이유는 Post가 Owner Entity를 갖고 있었기 때문
			// 현재는 Comment가 Owner Entity를 갖고 있음
			//
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			//
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	c1.setPost(p);
			// 	c2.setPost(p);
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			// 	// org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.mycom.myapp.oneToMany_BI.entity.Comment.post -> com.mycom.myapp.oneToMany_BI.entity.Post 에러 발생
			//
			// }


			// // #6 Post 1개, Comment 2개 생성
			// // Post  Comment  둘다 연결 , 둘다 persist
			// // 2개 생성
			//
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			//
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	c1.setPost(p);
			// 	c2.setPost(p);
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			// 	em.persist(p);

			// // 만약 , notnull이었으면, 오류가 발생했었을 것
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: update Comment set content=?,post_id=? where id=?
			// 	// Hibernate: update Comment set content=?,post_id=? where id=?
			//
			// }

			// // #7 Post 1개, Comment 2개 생성
			// // Post  Comment  둘다 연결 , 둘다 persist (post 먼저, comment 나중에 persist)
			// // 2개 생성
			//
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			//
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			//
			// 	p.setComments(List.of(c1, c2));
			// 	c1.setPost(p);
			// 	c2.setPost(p);
			//
			// 	em.persist(p);
			// 	em.persist(c1);
			// 	em.persist(c2);
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			//
			// }

			// // #8
			// // # 7의 코드 중 persist 관련 post 중심의 연결 코드는 필요가 없다.
			// // Po
			// // 2개 생성
			//
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			//
			// 	p.setContent("Post 1 Content");
			//
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	//불필요한 코드
			// 	//p.setComments(List.of(c1, c2));
			// 	c1.setPost(p);
			// 	c2.setPost(p);
			//
			// 	em.persist(p);
			// 	em.persist(c1);
			// 	em.persist(c2);
			//
			// 	// 	Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			//
			// }

			// #9
			// Comment의 cascade를 persist로 설정


			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			//
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	c1.setPost(p);
			// 	c2.setPost(p);
			//
			// 	em.persist(c1);
			// 	em.persist(c2);
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			//
			// }

			// // #10
			// // Post의 cascade를 persist로 설정
			// // Post, Comment 연결, post 중심
			// // post만 persist
			// // post가 Owner Entity가 되고, @OneToMany처럼 joinColumn이 아닌 join table을 따로 구성하게 된다.
			// {
			// 	Post p = new Post();
			// 	p.setTitle("Post 1");
			// 	p.setContent("Post 1 Content");
			//
			//
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("Comment 1");
			//
			// 	Comment c2 = new Comment();
			// 	c2.setContent("Comment 2");
			//
			// 	p.setComments(List.of(c1, c2));
			//
			// 	em.persist(p);
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Comment (content,post_id) values (?,?)
			// 	// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			// 	// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			// 	}


			// #9번 다시 실행 (원복하는 과정)


			{
				Post p = new Post();
				p.setTitle("Post 1");
				p.setContent("Post 1 Content");



				Comment c1 = new Comment();
				c1.setContent("Comment 1");

				Comment c2 = new Comment();
				c2.setContent("Comment 2");

				c1.setPost(p);
				c2.setPost(p);

				em.persist(c1);
				em.persist(c2);
				// Hibernate: insert into Post (content,title) values (?,?)
				// Hibernate: insert into Comment (content,post_id) values (?,?)
				// Hibernate: insert into Comment (content,post_id) values (?,?)

			}

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
