package com.mycom.myapp.oneToOne;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.oneToOne.config.MyPersistenceUnitInfo;
import com.mycom.myapp.oneToOne.entity.Passport;
import com.mycom.myapp.oneToOne.entity.Person;

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
			//
			// {
			// 	Employee emp = new Employee();
			// 	emp.setName("이길동");
			// 	emp.setAddress("서울 어디");
			// 	em.persist(emp);
			// }


			// 복합키 생성
			// {
			// 	Product p = new Product();
			// 	p.setCode("uplus");
			// 	p.setNumber(1L);
			// 	p.setColor("black");
			// 	em.persist(p);
			//
			// 	ProductKey key = new ProductKey();
			// 	key.setCode("uplus");
			// 	key.setNumber(1L);
			// 	Product p1 = em.find(Product.class, key);
			// }

			// {
			// 	StudentKey key = new StudentKey();
			// 	key.setCode("uplus");
			// 	key.setNumber(1L);
			//
			// 	// Student s = new Student();
			// 	// s.setName("홍길동");
			// 	// s.setId(key);
			// 	//
			// 	// em.persist(s);
			//
			// 	Student s2 = em.find(Student.class, key);
			// 	System.out.println(s2);
			// }


			// {
			// 	OrderKey key = new OrderKey();
			// 	key.setCustomerId(1);
			// 	key.setProductId(1);
			//
			// 	Orders o = new Orders();
			// 	o.setId(key);
			// 	o.setOrderCnt(3);
			// 	//
			// 	// em.persist(o);
			//
			// 	Orders o2 = em.find(Orders.class, key);
			// 	System.out.println(o2.getId().equals(key));
			// }
			// {
			// 	Person person = new Person();
			// 	person.setName("홍길동");
			//
			//
			//
			// 	Passport passport = new Passport();
			// 	passport.setNumber("kor1234");
			// 	person.setPassport(passport);
			// 	em.persist(passport);
			// 	em.persist(person);
			// }

			// one to one 단방향
			// {
			// 	Person person = em.find(Person.class, 2L);
			// 	System.out.println(person);
			//
			//
			// }
			// {
			// 	Person person = new Person();
			// 	person.setName("홍길동");
			//
			// 	Passport passport = new Passport();
			// 	passport.setNumber("kor1234");
			//
			// 	person.setPassport(passport);
			// 	em.persist(passport);
			// 	em.persist(person);
			// }

			//
			// {
			// 	Person person = new Person();
			// 	person.setName("홍길동");
			//
			// 	Passport passport = new Passport();
			// 	passport.setNumber("kor1234");
			//
			// 	person.setPassport(passport);
			// 	em.persist(passport);
			// 	em.persist(person);
			// }


			// {
			// 	// 오너 주인의 반대에서 persist 를 하면 오류가 발생한다.
			// 	Person person = new Person();
			// 	person.setName("홍길동");
			//
			// 	Passport passport = new Passport();
			// 	passport.setNumber("kor1234");
			//
			// 	// person.setPassport(passport);
			// 	passport.setPerson(person);
			// 	// em.persist(passport);
			// 	// em.persist(person);
			// 	em.persist(passport);
			// }

			//#B
			// {
			// 	Person person = em.find(Person.class,1);
			// 	System.out.println(person);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	//영속성 컨텐츠에 있던 값을 가지고 옴
			// 	System.out.println(person.getPassport());
			// }


			// //#C
			// //Person fetch = FetchType.LAZY
			{
				//join 하지 않음
				Person person = em.find(Person.class,2);
				System.out.println(person);

				try{
					Thread.sleep(5000);
				}catch (Exception e) {
					e.printStackTrace();
				}
				//필요할 때만 조인해서 가지고 온다.
				//Hibernate: select p1_0.id,p1_0.name,p1_0.passport from Person p1_0 where p1_0.id=?
				// Hibernate: select p1_0.id,p1_0.number,p2_0.id,p2_0.name from Passport p1_0 left join Person p2_0 on p1_0.id=p2_0.passport where p1_0.id=?
				System.out.println(person.getPassport());
			}

			//
			// //#D
			// //Passport fetch = FetchType.LAZY
			// // Lazy 옵션을 추가해도, Lazy 로딩이 되지 않는다.
			// // Passport를 위한 select 1 건 수행 + pserson과의 join 1건 수행 : 총 2건 수행
			// // 종속 관계의 주인이 아니라면, fetch = FetchType.LAZY 옵션을 추가해도 Lazy 로딩이 되지 않는다.
			// // passport는 person을 찾아갈 수 있는 구조가 없음,
			// // mappedBy에 의해 종속관계의 주인이 아닌 경우, 해당 테이블에 주인 엔티티에 대한 컬럼이 존재하지 않음 -> Passport 1건으로부터 이후 연관된 Person을 찾을 방법이 없다.
			// // 따라서, Lazy 정책을 수행하기 위해서는 미리 Passport와 연관된 person을 찾아서 준비
			//
			// // Hibernate: select p1_0.id,p1_0.number from Passport p1_0 where p1_0.id=?
			// // Hibernate: select p1_0.id,p1_0.name,p2_0.id,p2_0.number from Person p1_0 left join Passport p2_0 on p2_0.id=p1_0.passport where p1_0.passport=?
			// {
			// 	//join 하지 않음
			// 	Passport passport = em.find(Passport.class,1);
			// 	System.out.println(passport);
			//
			// 	try{
			// 		Thread.sleep(5000);
			// 	}catch (Exception e) {
			// 		e.printStackTrace();
			// 	}
			// 	//필요할 때만 조인해서 가지고 온다.
			// 	System.out.println(passport.getPerson());
			// }


			// //#E
			// // passport의 toString()에서 passport 출력
			//Person의 to String()에서 person 출력
			// OneToOne EAGER
			// StackOverFlowError 발생 (ToString()의 순환구조)
			//
			// {
			// 	Passport passport = em.find(Passport.class,1);
			// 	System.out.println(passport);
			//
			// 	// try{
			// 	// 	Thread.sleep(5000);
			// 	// } catch (Exception e) {
			// 	// 	e.printStackTrace();
			// 	// }
			// 	// System.out.println(passport.getPerson());
			// }

			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
