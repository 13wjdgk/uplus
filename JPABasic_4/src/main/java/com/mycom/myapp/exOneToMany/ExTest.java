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
public class ExTest {

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

			// 테이블 및 더미데이터 생성 , 생성 후 주석처리 !
			{
				ClassRoom c = new ClassRoom();
				c.setName("반1");

				Student s1 = new Student();
				s1.setName("학생1");

				Student s2 = new Student();
				s2.setName("학생2");

				c.setStudents(List.of(s1,s2));

				em.persist(c);

				ClassRoom c2 = new ClassRoom();
				c2.setName("반2");

				Student s3 = new Student();
				s3.setName("학생3");

				Student s4 = new Student();
				s4.setName("학생4");
				Student s5 = new Student();
				s5.setName("학생5");

				c2.setStudents(List.of(s3,s4,s5));

				em.persist(c2);
			}

			/**
			 * 1번 문제 : jpql 모든 반을 조회 하세요
			 * */



			/**
			 * 2번 문제 : jpql 모든 학생을 조회 하세요
			 * */


			/**
			 * 3번 문제 : 1반 학생을 조회 하세요
			 * */

			/**
			 * 4번 문제 : 학생3 학생의 반을 조회 하세요 (학생 반 , 학생 이름)
			 * */


			/**
			 * 5번 문제 : 반별 학생 수를 count 하고, 학생 수를 기준으로 내림차순 정렬 하세요 ( 반 이름, 학생 수)
			 * */














			/**
			 * 정답
			 *
			 * */


			//
			// //# 1 jpql 모든 반을 조회 하세요
			// {
			// 	String sql = "select c from ClassRoom c";
			// 	List<ClassRoom> list = em.createQuery(sql, ClassRoom.class).getResultList();
			// 	for(ClassRoom c : list) {
			// 		System.out.println(c);
			// 	}
			// }
			//
			// //# 2 jpql 모든 학생을 조회 하세요
			// {
			// 	String sql = "select s from Student s";
			// 	List<Student> list = em.createQuery(sql, Student.class).getResultList();
			// 	for(Student s : list) {
			// 		System.out.println(s);
			// 	}
			// }

			//# 3 jpql 1반 학생을 조회 하세요
			// {
			// 	String sql = "select c.students from ClassRoom c where c.name = '반1'";
			// 	List<Student> list = em.createQuery(sql, Student.class).getResultList();
			// 	for(Student s : list) {
			// 		System.out.println(s);
			// 	}
			// }

			// # 4 jpql 학생3 학생의 반을 조회 하세요 (학생 반 , 학생 이름)
			// {
			// 	String sql = "select c.name,s.name from ClassRoom c join c.students s where s.name = '학생3'";
			// 	List<Object[]> list = em.createQuery(sql, Object[].class).getResultList();
			// 	for(Object[] obj : list) {
			// 		System.out.println(obj[0] + " " + obj[1]);
			// 	}
			// }

			// # 5 jpql 반별 학생 수를 count 하고, 학생 수를 기준으로 내림차순 정렬 하세요 ( 반 이름, 학생 수)
			// {
			// 	String sql = "select c.name, count(s) from ClassRoom c join c.students s group by c.name order by count(s) desc";
			// 	List<Object[]> list = em.createQuery(sql, Object[].class).getResultList();
			// 	for(Object[] obj : list) {
			// 		System.out.println(obj[0] + " " + obj[1]);
			// 	}
			// }


			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
