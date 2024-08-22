package com.mycom.myapp.jpql_Nplus1;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;


import com.mycom.myapp.jpql_Nplus1.config.MyPersistenceUnitInfo;
import com.mycom.myapp.jpql_Nplus1.entity.Customer;
import com.mycom.myapp.jpql_Nplus1.entity.Orders;
import com.mycom.myapp.jpql_Nplus1.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

//JPQL
//insert 안됨,select,update,delete 가능
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
			// {
			// 	Product p1 = new Product("제품1", 1000L, 15, "korea");
			// 	Product p2 = new Product("제품2", 3000L, 10, "usa");
			// 	Product p3 = new Product("제품3", 5000L, 20, "korea");
			// 	Customer c1 = new Customer("고객1", 'M', "010-1111-1111");
			// 	Customer c2 = new Customer("고객2", 'F', "010-2222-2222");
			// 	Customer c3 = new Customer("고객3", 'F', "010-3333-3333");
			//
			// 	Orders o1 = new Orders(c1, p1, 2, LocalDate.now());
			// 	Orders o2 = new Orders(c1, p2, 5, LocalDate.now());
			// 	Orders o3 = new Orders(c2, p2, 3, LocalDate.now());
			// 	Orders o4 = new Orders(c2, p3, 10, LocalDate.now());
			// 	Orders o5 = new Orders(c3, p3, 5, LocalDate.now());
			//
			// 	em.persist(o1);
			// 	em.persist(o2);
			// 	em.persist(o3);
			// 	em.persist(o4);
			// 	em.persist(o5);
			// }

			// #2
			// N+1 이슈 발생
			// ManyToOne 기본 값이 EAGER 이기 때문에 select 1건 + 연관관계를 가지는 Entity N개를 조회하게 된다.
			// 해결책 : 같이 가져와야하는 것인지 고민
			// 1. 연관관계를 없앤다.
			// 2. LAZY로 설정한다. -> 문제는 해결되지만, 상세 1건을 가져올 때도 함께 가져오지 않는다.
			// 3. fetch join을 사용한다. (목록 fetch할 때 EAGER로 가져와, 1개의 join query로 수행)
			{
				String jpql = "select o from Orders o";
				//String jpql = "select o from Orders o join fetch o.customer join fetch o.product";
				TypedQuery<Orders> query = em.createQuery(jpql, Orders.class);
				List<Orders> list = query.getResultList();
				for(Orders o : list) {
					System.out.println(o);
					// System.out.println(o.getCustomer());
				}
			}


			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
