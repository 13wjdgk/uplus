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
public class Test3 {

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

			// 가
			// Post 조회 , fetchType EAGER로 설정
			{
				Post post = em.find(Post.class, 6L);
				Comment comment = new Comment();
				comment.setContent("댓글1");
				post.getComments().add(comment);

				em.persist(comment);


				// try{
				// 	Thread.sleep(5000);
				// } catch (Exception e) {
				// 	e.printStackTrace();
				// }
				// // Comments를 사용하는 시점에 다시 select 수행 X
				// System.out.println(post.getComments());
			}
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
