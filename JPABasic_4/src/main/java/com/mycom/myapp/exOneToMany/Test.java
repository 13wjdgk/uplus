package com.mycom.myapp.exOneToMany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.exOneToMany.config.MyPersistenceUnitInfo;
import com.mycom.myapp.exOneToMany.entity.ClassRoom;
import com.mycom.myapp.exOneToMany.entity.Student;

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

			// 테이블 생성
			{
				// Hibernate: create table Class (id integer not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
				// Hibernate: create table Class_Student (Class_id integer not null, students_id integer not null) engine=InnoDB
				// Hibernate: create table Student (id integer not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
				// Hibernate: alter table Class_Student drop index UKeb78dwd12ylfrg1ufbo967wtc
				// Hibernate: alter table Class_Student add constraint UKeb78dwd12ylfrg1ufbo967wtc unique (students_id)
				// Hibernate: alter table Class_Student add constraint FKhcxqus7alhtujwkt9vrskl96u foreign key (students_id) references Student (id)
				// Hibernate: alter table Class_Student add constraint FKek62i927b3gaiyp3r0nda4eeu foreign key (Class_id) references Class (id)
			}

			// #2
			// Class Entity 1개 생성
			// {
			// 	ClassRoom c = new ClassRoom();
			// 	c.setName("반1");
			//
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			//
			// 	em.persist(c);
			// }
			//
			//
			//
			// // #3
			// // Student Entity 2개 생성
			// {
			// 	Student s1 = new Student();
			// 	s1.setName("학생1");
			//
			// 	Student s2 = new Student();
			// 	s2.setName("학생2");
			//
			// 	em.persist(s1);
			// 	em.persist(s2);
			//
			// 	// Hibernate: insert into Comment (content) values (?)
			// 	// Hibernate: insert into Comment (content) values (?)
			//
			// }

			// // #4
			// // Post 1,Comment Entity 2개 persist
			// // Post - Comment 연결
			// {
			// 	ClassRoom c = new ClassRoom();
			// 	c.setName("반1");
			//
			// 	Student s1 = new Student();
			// 	s1.setName("학생1");
			//
			// 	Student s2 = new Student();
			// 	s2.setName("학생2");
			//
			// 	c.setStudents(List.of(s1,s2));
			//
			// 	em.persist(c);
			//
			// 	em.persist(s1);
			// 	em.persist(s2);

				// Hibernate: insert into Post (content,title) values (?,?)
				// Hibernate: insert into Comment (content) values (?)
				// Hibernate: insert into Comment (content) values (?)
				// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
				// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)

			// }


			// #5
			// Post 1,Comment Entity 2개 persist
			// Post - Comment 연결
			// Post만 persist
			// {
			// 	ClassRoom c = new ClassRoom();
			// 	c.setName("반1");
			//
			// 	Student s1 = new Student();
			// 	s1.setName("학생1");
			//
			// 	Student s2 = new Student();
			// 	s2.setName("학생2");
			//
			// 	c.setStudents(List.of(s1,s2));
			//
			// 	em.persist(c);
			// }


			// #6
			// Post 1,Comment Entity 2개 persist
			// Post - Comment 연결
			// Post만 persist
			// {
			// 	ClassRoom c = new ClassRoom();
			// 	c.setName("반1");
			//
			// 	Student s1 = new Student();
			// 	s1.setName("학생1");
			//
			// 	Student s2 = new Student();
			// 	s2.setName("학생2");
			//
			// 	c.setStudents(List.of(s1,s2));
			//
			// 	em.persist(c);
			//
			// 	// Hibernate: insert into Post (content,title) values (?,?)
			// 	// Hibernate: insert into Comment (content) values (?)
			// 	// Hibernate: insert into Comment (content) values (?)
			// 	// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			// 	// Hibernate: insert into Post_Comment (Post_id,comments_id) values (?,?)
			// }

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
