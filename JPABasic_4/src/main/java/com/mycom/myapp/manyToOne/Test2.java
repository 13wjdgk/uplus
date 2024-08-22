package com.mycom.myapp.manyToOne;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.manyToOne.config.MyPersistenceUnitInfo;
import com.mycom.myapp.manyToOne.entity.Comment;
import com.mycom.myapp.manyToOne.entity.Post;

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
			// {
			// 	Post p = em.find(Post.class, 1);
			// 	System.out.println(p);
			// }

			// // B
			// // join으로 연관된 Post를 가져온다.
			// //@ManyToOne(fetch = FetchType.EAGER)이기 때문
			// {
			// 	Comment c1 = em.find( Comment.class, 6);
			// 	System.out.println(c1);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			//
			// 	// Comment와 연관된 Post를 가져올 때 다시 Sql 수행하지 않는다.
			// 	System.out.println(c1.getPost());
			// }


			// C
			// Comment 1건 find()
			//@ManyToOne(fetch = FetchType.Lazy)로 변경
			// Comment만 가져온 후에, 필요할 때 where 절을 이용해서 post를 가져온다.
			{
				Comment c1 = em.find( Comment.class, 6);
				System.out.println(c1);

				try{
					Thread.sleep(5000);
				}catch (Exception e) {
					e.printStackTrace();
				}

				// 필요할 때 where 절을 이용해서 post를 가져온다.
				// Hibernate: select c1_0.id,c1_0.content,c1_0.post_id from Comment c1_0 where c1_0.id=?
				// com.mycom.myapp.manyToOne.entity.Comment@16b3c905
				// Hibernate: select p1_0.id,p1_0.content,p1_0.title from Post p1_0 where p1_0.id=?
				// Post{id=4, title='Post', content='Post 1 Content'}
				System.out.println(c1.getPost());
			}

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
