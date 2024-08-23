package com.mycom.myapp.jpql_criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.jpql_criteria.config.MyPersistenceUnitInfo;
import com.mycom.myapp.jpql_criteria.entity.Customer;
import com.mycom.myapp.jpql_criteria.entity.Orders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

//named 쿼리
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
			// 	Orders o1 = new Orders(c1, p1, 2, LocalDate.of(2024, 3, 10));
			// 	Orders o2 = new Orders(c1, p2, 5, LocalDate.of(2024, 3, 11));
			// 	Orders o3 = new Orders(c2, p2, 3, LocalDate.of(2024, 3, 11));
			// 	Orders o4 = new Orders(c2, p3, 10, LocalDate.of(2024, 3, 10));
			// 	Orders o5 = new Orders(c3, p3, 3, LocalDate.of(2024, 3, 10));
			// 	Orders o6 = new Orders(c1, p1, 2, LocalDate.of(2024, 3, 10));
			// 	Orders o7 = new Orders(c1, p3, 6, LocalDate.of(2024, 3, 12));
			// 	Orders o8 = new Orders(c2, p3, 3, LocalDate.of(2024, 3, 12));
			// 	Orders o9 = new Orders(c2, p1, 4, LocalDate.of(2024, 3, 12));
			// 	Orders o10 = new Orders(c3, p1, 5, LocalDate.of(2024, 3, 13));
			//
			// 	em.persist(o1);
			// 	em.persist(o2);
			// 	em.persist(o3);
			// 	em.persist(o4);
			// 	em.persist(o5);
			// 	em.persist(o6);
			// 	em.persist(o7);
			// 	em.persist(o8);
			// 	em.persist(o9);
			// 	em.persist(o10);
			// }

			// # 2
			// select c form Customer c
			// {
			// 	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			// 	CriteriaQuery<Customer> cquery = criteriaBuilder.createQuery(Customer.class);
			// 	Root<Customer> customerRoot = cquery.from(Customer.class); // from Customer c
			// 	cquery.select(customerRoot); // [select c ] from Customer c
			//
			// 	TypedQuery<Customer> query = em.createQuery(cquery);
			// 	List<Customer> list = query.getResultList();
			// 	for(Customer obj : list){
			// 		System.out.println(obj);
			// 	}
			// }


			// // # 3
			// // select c.name form Customer c
			// {
			// 	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			// 	CriteriaQuery<String> cquery = criteriaBuilder.createQuery(String.class);
			// 	Root<Customer> customerRoot = cquery.from(Customer.class); // from Customer c
			// 	cquery.select(customerRoot.get("name")); // [select c ] from Customer c
			//
			// 	TypedQuery<String> query = em.createQuery(cquery);
			// 	List<String> list = query.getResultList();
			// 	for(String obj : list){
			// 		System.out.println(obj);
			// 	}
			// }

			// # 4
			// select c form Customer c where c.id = 1
			{
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<Customer> cquery = criteriaBuilder.createQuery(Customer.class);
				Root<Customer> customerRoot = cquery.from(Customer.class); // from Customer c
				cquery.select(customerRoot).where(
					criteriaBuilder.equal(customerRoot.get("id"), 1)
				); // [select c ] from Customer c

				TypedQuery<Customer> query = em.createQuery(cquery);
				List<Customer> list = query.getResultList();
				for(Customer obj : list){
					System.out.println(obj);
				}
			}


			//findByOrderProductPriceRange

			// # 5  Named Query
			// {
			// 	// TypedQuery<Orders> q = em.createNamedQuery("Orders.findByCustomerName", Orders.class);
			// 	// TypedQuery<Orders> q = em.createNamedQuery("Orders.findByOrderDate", Orders.class);
			// 	TypedQuery<Orders> q = em.createNamedQuery("Orders.findByOrderProductPriceRange", Orders.class);
			//
			// 	q.setParameter("startPrice", 2000);
			// 	q.setParameter("endPrice", 7000);
			//
			// 	List<Orders> list = q.getResultList();
			// 	for(Orders obj : list){
			// 		System.out.println(obj);
			// 	}
			// }


			// // #6 native Query
			// {
			// 	String sql = """
			// 		select o.* from orders o, customer c
			// 		where o.customer_id = c.id
			// 		and c.name = :customerName
			// 		""";
			// 	Query q = em.createNativeQuery(sql, Orders.class);
			// 	q.setParameter("customerName", "고객1");
			//
			// 	List<?> list = q.getResultList();
			// 	for(Object obj : list){
			// 		System.out.println(obj);
			// 	}
			// }
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
