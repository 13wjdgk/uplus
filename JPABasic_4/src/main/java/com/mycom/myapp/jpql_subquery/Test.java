package com.mycom.myapp.jpql_subquery;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.jpa.HibernatePersistenceProvider;
import com.mycom.myapp.jpql_subquery.config.MyPersistenceUnitInfo;
import com.mycom.myapp.jpql_subquery.entity.Customer;
import com.mycom.myapp.jpql_subquery.entity.Orders;
import com.mycom.myapp.jpql_subquery.entity.Product;

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
			// 	Product p4 = new Product("제품4", 7000L, 20, "korea");
			//
			// 	Customer c1 = new Customer("고객1", 'M', "010-1111-1111");
			// 	Customer c2 = new Customer("고객2", 'F', "010-2222-2222");
			// 	Customer c3 = new Customer("고객3", 'F', "010-3333-3333");
			// 	Customer c4 = new Customer("고객4", 'F', "010-3333-3333");
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
			// 	em.persist(p4);
			// 	em.persist(c4);
			// }

			// // #2 join
			// {
			// 	//String jpql = "select p from Product p inner join p.orders o ";
			// 	//String jpql = "select p from Product p join p.orders o ";
			// 	//String jpql = "select p from Product p ,Orders o where p.id = o.product.id"; // orders 먼저
			// 	// String jpql = "select o,p from Product p ,Orders o where p = o.product"; // orders 먼저 + from orders 먼저
			// 	String jpql = """
			// 			select o,p ,c from Orders o ,Product p,Customer c where p = o.product and c = o.customer
			// 			""";
			//
			// 	TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			// 	List<Object[]> list = query.getResultList();
			// 	for(Object[] product : list) {
			// 		System.out.println(product[0]+" "+product[1]+" "+product[2]);
			// 		// System.out.println(o.getCustomer());
			// 	}
			// }

			// // #3 outer join
			// {
			// 	// String jpql = """
			// 	// 		select p,o from Product p left join p.orders o
			// 	// 		""";
			// 	String jpql = """
			// 			select c,o from Customer c left join c.orders o
			// 			""";
			// 	TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			// 	List<Object[]> list = query.getResultList();
			// 	for(Object[] product : list) {
			// 		System.out.println(product[0]+" "+product[1]);
			// 		// System.out.println(o.getCustomer());
			// 	}
			// }

			// // #4 left outer join
			// {
			// 	// String jpql = """
			// 	// 		select p,o from Product p left join p.orders o
			// 	// 		""";
			// 	String jpql = """
			// 			select c,count(o) from Customer c left join c.orders o group by c
			// 			""";
			// 	TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			// 	List<Object[]> list = query.getResultList();
			// 	for(Object[] product : list) {
			// 		System.out.println(product[0]+" "+product[1]);
			// 		// System.out.println(o.getCustomer());
			// 	}
			// }

			// // #5 join + 조건
			// {
			// 	String jpql = """
			// 			select p,o from Product p join p.orders o
			// 			where p.price > 1000 and p.quantity > 10 and o.orderQuantity = 10
			// 			""";
			// 	TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			// 	List<Object[]> list = query.getResultList();
			// 	for(Object[] product : list) {
			// 		System.out.println(product[0]+" "+product[1]);
			// 		// System.out.println(o.getCustomer());
			// 	}
			// }

			// // #6 join + 조건 + 일부 필드
			// {
			// 	String jpql = """
			// 			select p.id, p.name ,p.price,o.orderQuantity,o.orderDate
			// 			from Product p join p.orders o
			// 			where p.price > 1000
			// 			and p.quantity > 10
			// 			and o.orderQuantity = 10
			// 			""";
			// 	TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
			// 	List<Object[]> list = query.getResultList();
			// 	for(Object[] product : list) {
			// 		System.out.println(product[0]+" "+product[1]+" "+product[2]+" "+product[3]+" "+product[4]);
			// 		// System.out.println(o.getCustomer());
			// 	}
			// }

			// // #7 Subquery
			// {
			// 	String jpql = """
			// 			select o
			// 			from Orders o
			// 			where o.product in (select p from Product p where p.price<5000)
			// 			""";
			// 	TypedQuery<Orders> query = em.createQuery(jpql, Orders.class);
			// 	List<Orders> list = query.getResultList();
			// 	for(Orders obj : list) {
			// 		System.out.println(obj);
			// 		// System.out.println(o.getCustomer());
			// 	}
			// }

			// #8 Subquery - select
			{
				String jpql = """ 
						select o , ( select c.name from Customer c where o.customer = c) as customerName
						from Orders o
						""";
					TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
					List<Object[]> list = query.getResultList();
					for(Object[] product : list) {
						System.out.println(product[0]+" "+product[1]);
						// System.out.println(o.getCustomer());
					}
			}
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
