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

			// // #2
			// // User Entity 생성, persist
			// // User 2건 삽입
			// {
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	// Hibernate: insert into users (name) values (?)
			// 	// Hibernate: insert into users (name) values (?)
			// 	em.persist(u1);
			// 	em.persist(u2);
			// }

			// // #3
			// // Team Entity 생성, persist
			// // Team 2건 삽입
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// // Hibernate: insert into teams (name) values (?)
			// // Hibernate: insert into teams (name) values (?)
			// 	em.persist(t1);
			// 	em.persist(t2);
			// }

			// // #4
			// // Team 2, User 2 Entity 생성, persist 4개
			// // Team 2건 삽입
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	// Hibernate: insert into teams (name) values (?)
			// 	// Hibernate: insert into teams (name) values (?)
			// 	// Hibernate: insert into users (name) values (?)
			// 	// Hibernate: insert into users (name) values (?)
			//
			// 	em.persist(t1);
			// 	em.persist(t2);
			// 	em.persist(u1);
			// 	em.persist(u2);
			// }

			// // #5
			// // Team 2, User 2 Entity 생성,
			// // Team 중심 연결, persist 4개
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	t1.setUsers(List.of(u1, u2));
			// 	t2.setUsers(List.of(u1, u2));
			// 	em.persist(t1);
			// 	em.persist(t2);
			//
			// 	em.persist(u1);
			// 	em.persist(u2);
			//
			// }

			// // #6
			// // Team 2, User 2 Entity 생성,
			// // Team 중심 연결, persist 4개
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	t1.setUsers(List.of(u1, u2));
			// 	t2.setUsers(List.of(u1, u2));
			// 	em.persist(t1);
			// 	em.persist(t2);
			// 	// save the transient instance before flushing: com.mycom.myapp.ManyToMany.entity.User 에러 발생
			//
			// }

			// // #7
			// // Team 2, User 2 Entity 생성,
			// // Team 중심 연결,
			// // persist User만
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	t1.setUsers(List.of(u1, u2));
			// 	t2.setUsers(List.of(u1, u2));
			// 	em.persist(u1);
			// 	em.persist(u2);
			// 	// Hibernate: insert into users (name) values (?)
			// 	// Hibernate: insert into users (name) values (?)
			//
			// }

			// // #8
			// // Team 2, User 2 Entity 생성,
			// // User 중심 연결,
			// // persist User만
			// // non owner entity를 persist할 때 오너엔티티가 연결되어 있ㅇ서 오류가 발생 안함
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	u1.setTeams(List.of(t1, t2));
			// 	u2.setTeams(List.of(t1, t2));
			// 	em.persist(u1);
			// 	em.persist(u2);
			// 	// Hibernate: insert into users (name) values (?)
			// 	// Hibernate: insert into users (name) values (?)
			//
			// }

			// #9
			// Team 2, User 2 Entity 생성,
			// Team 중심 연결, persist 2개만
			// Team이 owner이기 때문에 User를 persist하지 않아도 Team만 persist하면 User도 같이 persist됨
			{
				Team t1 = new Team();
				t1.setName("Team 1");

				Team t2 = new Team();
				t2.setName("Team 2");

				User u1 = new User();
				u1.setName("User 1");

				User u2 = new User();
				u2.setName("User 2");

				t1.setUsers(List.of(u1, u2));
				t2.setUsers(List.of(u1, u2));
				em.persist(t1);
				em.persist(t2);
				// Hibernate: insert into teams (name) values (?)
				// Hibernate: insert into users (name) values (?)
				// Hibernate: insert into users (name) values (?)
				// Hibernate: insert into teams (name) values (?)
				// Hibernate: insert into teams_users (team_id,user_id) values (?,?)
				// Hibernate: insert into teams_users (team_id,user_id) values (?,?)
				// Hibernate: insert into teams_users (team_id,user_id) values (?,?)
				// Hibernate: insert into teams_users (team_id,user_id) values (?,?)

			}

			// // #10
			// // User에 persist 추가, Team에서는 persist하지 않음
			// // Team 2, User 2 Entity 생성,
			// // User 중심 연결,
			// // persist User만
			// // Non Owner Entity 를 persist 할 때 CascadeType 이 PERSIST 이 경우, 개별 관계를 맺는 Entity 는 persist 되는데
			// // @ManyToMany 에 대응하는 Join Table 에는 처리 X
			// {
			// 	Team t1 = new Team();
			// 	t1.setName("Team 1");
			//
			// 	Team t2 = new Team();
			// 	t2.setName("Team 2");
			//
			// 	User u1 = new User();
			// 	u1.setName("User 1");
			//
			// 	User u2 = new User();
			// 	u2.setName("User 2");
			//
			// 	u1.setTeams(List.of(t1, t2));
			// 	u2.setTeams(List.of(t1, t2));
			// 	em.persist(u1);
			// 	em.persist(u2);
			// 	// List<Team> teams 까지는 영속성으로 같이 삽입되었지만, 관계테이블에는 삽입되지 않음
			// 	// Hibernate: insert into users (name) values (?)
			// 	// Hibernate: insert into teams (name) values (?)
			// 	// Hibernate: insert into teams (name) values (?)
			// 	// Hibernate: insert into users (name) values (?)
			//
			// }


			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
