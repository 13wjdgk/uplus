package com.mycom.myapp.oneToMany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.oneToMany.config.MyPersistenceUnitInfo;
import com.mycom.myapp.oneToMany.entity.Comment;
import com.mycom.myapp.oneToMany.entity.Post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

// org.hibernate.UnknownEntityTypeException: Unable to locate entity descriptor: com.mycom.myapp.entity.Employee
// HashMap 에 몇 가지 설정
public class Test2 {

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

			// // A
			// // 조인하지 않고, Post만 조회 , fetchType이 기본적으로 lazy로 설정되어 있기 때문
			// {
			// 	Post post = em.find(Post.class, 2L);
			// 	System.out.println(post);
			//
			// 	// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
			// 	// Post{id=2, title='첫번째 글', content='내용1'}
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	} catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	//Hibernate: select c1_0.Post_id,c1_1.id,c1_1.content from Post_Comment c1_0 join Comment c1_1 on c1_1.id=c1_0.comments_id where c1_0.Post_id=?
			// 	System.out.println(post.getComments());
			// }


			// B
			// // 자기 자신만 조회
			// {
			// 	Comment c = em.find(Comment.class, 1L);
			// 	System.out.println(c);
			// }



			// // C
			// // Post 조회 , fetchType EAGER로 설정
			// {
			// 	Post post = em.find(Post.class, 2L);
			// 	System.out.println(post);
			//
			// 	// Hibernate: select p1_0.id,p1_0.content,p1_0.title,c1_0.Post_id,c1_1.id,c1_1.content from Post p1_0 left join Post_Comment c1_0 on p1_0.id=c1_0.Post_id left join Comment c1_1 on c1_1.id=c1_0.comments_id where p1_0.id=?
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	} catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	// Comments를 사용하는 시점에 다시 select 수행 X
			// 	System.out.println(post.getComments());
			// }


			// C
			// Post 조회 , fetchType EAGER로 설정
			{
				Post post = em.find(Post.class, 2L);
				System.out.println(post);

				// Hibernate: select p1_0.id,p1_0.content,p1_0.title,c1_0.Post_id,c1_1.id,c1_1.content from Post p1_0 left join Post_Comment c1_0 on p1_0.id=c1_0.Post_id left join Comment c1_1 on c1_1.id=c1_0.comments_id where p1_0.id=?

				try{
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Comments를 사용하는 시점에 다시 select 수행 X
				System.out.println(post.getComments());
			}
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
