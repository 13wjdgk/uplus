package com.mycom.myapp.oneToMany_BI;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.oneToMany_BI.config.MyPersistenceUnitInfo;
import com.mycom.myapp.oneToMany_BI.entity.Comment;
import com.mycom.myapp.oneToMany_BI.entity.Post;

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
			// // post find()
			// //@OneToMany의  FetchType.Lazy 설정으로 자기 자신만 가지고 온다.
			// {
			// 	Post p = em.find(Post.class, 1L);
			// 	System.out.println(p);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// // 필요할 때 가지고옴
			// 	// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
			// 	// Post{id=1, title='Post 1', content='Post 1 Content'}
			// 	// Hibernate: select c1_0.post_id,c1_0.id,c1_0.content from Comment c1_0 where c1_0.post_id=?
			// 	// [com.mycom.myapp.oneToMany_BI.entity.Comment@22d477c2, com.mycom.myapp.oneToMany_BI.entity.Comment@41def031]
			// 	System.out.println(p.getComments());
			// }

			// // B
			// // Comment find()
			// // @ManyToOne의 FetchType EAGER
			// // join 수행, Comment와 Post를 join
			// {
			// 	Comment c1 = em.find(Comment.class, 1L);
			// 	System.out.println(c1);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	// 다시 조회하지 않음
			// 	// Hibernate: select c1_0.id,c1_0.content,p1_0.id,p1_0.content,p1_0.title from Comment c1_0 left join Post p1_0 on p1_0.id=c1_0.post_id where c1_0.id=?
			// 	// com.mycom.myapp.oneToMany_BI.entity.Comment@4b957db0
			// 	// Post{id=1, title='Post 1', content='Post 1 Content'}
			// 	System.out.println(c1.getPost());
			// }

			// // C
			// // post find()
			// // post fetchType EAGER로 변경
			// // join 수행, Comment와 Post를 join
			//
			// {
			// 	Post p = em.find(Post.class, 1L);
			// 	System.out.println(p);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	// 처음부터 가지고 온다
			// 	// Hibernate: select p1_0.id,p1_0.content,p1_0.title,c1_0.post_id,c1_0.id,c1_0.content from Post p1_0 left join Comment c1_0 on p1_0.id=c1_0.post_id where p1_0.id=?
			// 	// Post{id=1, title='Post 1', content='Post 1 Content'}
			// 	// [com.mycom.myapp.oneToMany_BI.entity.Comment@6f85ee02, com.mycom.myapp.oneToMany_BI.entity.Comment@551be9f6]
			// 	System.out.println(p.getComments());
			// }

			// D
			// Comment find()
			// @ManyToOne의 FetchType Lazy로 변경
			// join 수행하지 않음, Comment만 가져온 후, 필요할 때 post 가지고 온다.
			{
				Comment c1 = em.find(Comment.class, 1L);
				System.out.println(c1);

				try{
					Thread.sleep(5000);
				}catch (Exception e) {
					e.printStackTrace();
				}
				// 필요할 때 조회
				// Hibernate: select c1_0.id,c1_0.content,c1_0.post_id from Comment c1_0 where c1_0.id=?
				// com.mycom.myapp.oneToMany_BI.entity.Comment@1e30db85
				// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
				// Post{id=1, title='Post 1', content='Post 1 Content'}
				System.out.println(c1.getPost());
			}

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
