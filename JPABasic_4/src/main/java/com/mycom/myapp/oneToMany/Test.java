package com.mycom.myapp.oneToMany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.oneToMany.entity.Comment;
import com.mycom.myapp.oneToMany.entity.Post;
import com.mycom.myapp.oneToMany.config.MyPersistenceUnitInfo;

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

			// 테이블 생성
			{
			//Hibernate: create table Comment (id integer not null auto_increment, content varchar(255), primary key (id)) engine=InnoDB
			// Hibernate: create table Post (id integer not null auto_increment, content varchar(255), title varchar(255), primary key (id)) engine=InnoDB
			// Hibernate: create table Post_Comment (Post_id integer not null, comments_id integer not null) engine=InnoDB
			// Hibernate: alter table Post_Comment drop index UK5nd2ucnctmvwq31ovab2vi00x
			// Hibernate: alter table Post_Comment add constraint UK5nd2ucnctmvwq31ovab2vi00x unique (comments_id)
			// Hibernate: alter table Post_Comment add constraint FK1yqm25y6mj9vmmxj4ev7aj2q1 foreign key (comments_id) references Comment (id)
			// Hibernate: alter table Post_Comment add constraint FKgtvmchqpjf5ysbhyg52cofvrn foreign key (Post_id) references Post (id)
			}

			// // #2
			// // Post Entity 1
			// {
			// 	Post p = new Post();
			// 	p.setTitle("첫번째 글");
			// 	p.setContent("내용1");
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			//
			// 	em.persist(p);
			// }



			// // #3
			// // comment Entity 2 , Post와의 연결이 필요
			// {
			// 	Comment c = new Comment();
			// 	c.setContent("댓글1");
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("댓글2");
			//
			// 	em.persist(c);
			// 	em.persist(c1);
			//
			// 	// Hibernate: insert into Comment (content) values (?)
			// 	// Hibernate: insert into Comment (content) values (?)
			//
			// }

			// // #4
			// // Post 1,Comment Entity 2개 persist
			// // Post - Comment 연결
			// {
			// 	Post p = new Post();
			// 	p.setTitle("첫번째 글");
			// 	p.setContent("내용1");
			//
			// 	Comment c = new Comment();
			// 	c.setContent("댓글1");
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("댓글2");
			//
			// 	p.setComments(List.of(c1,c));
			// 	em.persist(p);
			//
			// 	em.persist(c);
			// 	em.persist(c1);
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content) values (?)
			// 	// Hibernate: insert into Comment (content) values (?)
			// 	// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			// 	// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			//
			// }


			// // #5
			// // Post 1,Comment Entity 2개 persist
			// // Post - Comment 연결
			// // Post만 persist
			// {
			// 	Post p = new Post();
			// 	p.setTitle("첫번째 글");
			// 	p.setContent("내용1");
			//
			// 	Comment c = new Comment();
			// 	c.setContent("댓글1");
			//
			// 	Comment c1 = new Comment();
			// 	c1.setContent("댓글2");
			//
			// 	p.setComments(List.of(c1,c));
			// 	em.persist(p);
			//
			// 	//  save the transient instance before flushing: com.mycom.myapp.oneToMany.entity.Comment 오류 발생
			// }


			// #6
			// Post 1,Comment Entity 2개 persist
			// Post - Comment 연결
			// Post만 persist
			{
				Post p = new Post();
				p.setTitle("첫번째 글");
				p.setContent("내용1");

				Comment c = new Comment();
				c.setContent("댓글1");

				Comment c1 = new Comment();
				c1.setContent("댓글2");

				p.setComments(List.of(c1,c));
				em.persist(p);

				// Hibernate: insert into Post (content,title) values (?,?)
				// Hibernate: insert into Comment (content) values (?)
				// Hibernate: insert into Comment (content) values (?)
				// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
				// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			}

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
