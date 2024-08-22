package com.mycom.myapp.ManyToMany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.ManyToMany.config.MyPersistenceUnitInfo;
import com.mycom.myapp.ManyToMany.entity.Team;
import com.mycom.myapp.ManyToMany.entity.User;

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
			// #1
			{

			}

			// // #A
			// // Team find()
			// // @ManyToMany fetchType = FetchType.LAZY이다.
			// {
			// 	Team t1 = em.find(Team.class, 1L);
			// 	System.out.println(t1);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}
			// 	catch(Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	// 필요할 때 가지고온다.
			// 	// Hibernate: select t1_0.id,t1_0.name from teams t1_0 where t1_0.id=?
			// 	// Team{id=1, name='Team 1'}
			// 	// Hibernate: select u1_0.team_id,u1_1.id,u1_1.name from teams_users u1_0 join users u1_1 on u1_1.id=u1_0.user_id where u1_0.team_id=?
			// 	// [User{id=1, name='User 1'}, User{id=2, name='User 2'}]
			// 	System.out.println(t1.getUsers());
			// }

			// #B
			// Team find()
			// @ManyToMany fetchType = FetchType.EAGER이다.
			// {
			// 	Team t1 = em.find(Team.class, 1L);
			// 	System.out.println(t1);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}
			// 	catch(Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	// 필요할 때 가지고 오지 않는다.
			// 	// Hibernate: select t1_0.id,t1_0.name,u1_0.team_id,u1_1.id,u1_1.name from teams t1_0 left join teams_users u1_0 on t1_0.id=u1_0.team_id left join users u1_1 on u1_1.id=u1_0.user_id where t1_0.id=?
			// 	// Team{id=1, name='Team 1'}
			// 	// [User{id=1, name='User 1'}, User{id=2, name='User 2'}]
			// 	System.out.println(t1.getUsers());
			// }


			// // #C
			// // User find()
			// // @ManyToMany fetchType = FetchType.LAZY이다.
			// {
			// 	User u1 = em.find(User.class, 1L);
			// 	System.out.println(u1);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}
			// 	catch(Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	// 필요할 때 가지고 온다.
			// 	// Hibernate: select u1_0.id,u1_0.name from users u1_0 where u1_0.id=?
			// 	// User{id=1, name='User 1'}
			// 	// Hibernate: select t1_0.user_id,t1_1.id,t1_1.name from teams_users t1_0 join teams t1_1 on t1_1.id=t1_0.team_id where t1_0.user_id=?
			// 	// [Team{id=1, name='Team 1'}, Team{id=2, name='Team 2'}]
			// 	System.out.println(u1.getTeams());
			// }

			// #D
			// User find()
			// @ManyToMany fetchType = FetchType.EAGER로 변경이다.
			{
				User u1 = em.find(User.class, 1L);
				System.out.println(u1);

				try{
					Thread.sleep(5000);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				// 필요할 때 가지고 오지 않는다.한번에 가져옴
				// Hibernate: select u1_0.id,u1_0.name,t1_0.user_id,t1_1.id,t1_1.name from users u1_0 left join teams_users t1_0 on u1_0.id=t1_0.user_id left join teams t1_1 on t1_1.id=t1_0.team_id where u1_0.id=?
				// User{id=1, name='User 1'}
				// [Team{id=1, name='Team 1'}, Team{id=2, name='Team 2'}]
				System.out.println(u1.getTeams());
			}

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
