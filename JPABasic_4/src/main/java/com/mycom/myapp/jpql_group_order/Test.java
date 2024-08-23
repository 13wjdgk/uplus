package com.mycom.myapp.jpql_group_order;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.jpql_group_order.config.MyPersistenceUnitInfo;
import com.mycom.myapp.jpql_group_order.entity.Customer;
import com.mycom.myapp.jpql_group_order.entity.Orders;
import com.mycom.myapp.jpql_group_order.entity.Product;

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
			// {
			// 	String jpql = """
			// 		select o.orderDate,count(o.id) as cnt
			// 		from Orders o
			// 		group by o.orderDate
			// 		""";
			//
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			// 	List<Object[]> list = q.getResultList();
			// 	for(Object[] obj : list){
			// 		System.out.println(obj[0]+" "+obj[1]);
			// 	}
			// }

			// #3 Group by having - orderDate,count()
			//having에 alias 사용하면 안됨
			// {
			// 	String jpql = """
			// 		select o.orderDate,count(o.id) as cnt
			// 		from Orders o
			// 		group by o.orderDate
			// 		having  count(o.id)>2
			// 		""";
			//
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			// 	List<Object[]> list = q.getResultList();
			// 	for(Object[] obj : list){
			// 		System.out.println(obj[0]+" "+obj[1]);
			// 	}
			// }


			// #4 Group by having - orderDate,count() + orderDate 조건
			// having에 alias 사용하면 안됨
			// {
			// 	// 아래 jpql은 버릴 2024-03-11 이하의 데이터를 가져와서 groupby 연산을 수행 후 버린다.
			// 	// String jpql = """
			// 	// 	select o.orderDate,count(o.id) as cnt
			// 	// 	from Orders o
			// 	// 	group by o.orderDate
			// 	// 	having  o.orderDate > :orderDate
			// 	// 	""";
			//
			// 	String jpql = """
			// 		select o.orderDate,count(o.id) as cnt
			// 		from Orders o
			// 		where  o.orderDate > :orderDate
			// 		group by o.orderDate
			// 		""";
			//
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			// 	q.setParameter("orderDate",LocalDate.of(2024,03,11));
			// 	List<Object[]> list = q.getResultList();
			// 	for(Object[] obj : list){
			// 		System.out.println(obj[0]+" "+obj[1]);
			// 	}
			// }


			// // #5 Order by
			// {
			// 	// 아래 jpql은 버릴 2024-03-11 이하의 데이터를 가져와서 groupby 연산을 수행 후 버린다.
			// 	// String jpql = """
			// 	// 	select o.orderDate,count(o.id) as cnt
			// 	// 	from Orders o
			// 	// 	group by o.orderDate
			// 	// 	having  o.orderDate > :orderDate
			// 	// 	""";
			//
			// 	String jpql = """
			// 		select o
			// 		from Orders o
			// 		order by o.orderQuantity desc , o.orderDate asc
			// 		""";
			//
			// 	TypedQuery<Orders> q = em.createQuery(jpql,Orders.class);
			//
			// 	List<Orders> list = q.getResultList();
			// 	for(Orders obj : list){
			// 		System.out.println(obj);
			// 	}
			// }

			// #6
			// 주문 날짜별, 총 주문금액을 구해야함
			// 조건 : 여성이 주문, 제품 가격이 2000원 이상
			// {
			// 	// 아래 jpql은 버릴 2024-03-11 이하의 데이터를 가져와서 groupby 연산을 수행 후 버린다.
			// 	// String jpql = """
			// 	// 	select o.orderDate,count(o.id) as cnt
			// 	// 	from Orders o
			// 	// 	group by o.orderDate
			// 	// 	having  o.orderDate > :orderDate
			// 	// 	""";
			//
			// 	String jpql = """
			// 		select o.orderDate , sum(p.price*o.orderQuantity) as price
			// 		from Orders o left join Product p on o.product = p left join Customer c on o.customer = c
			// 		where c.gender like 'F' and p.price>= 2000
			// 		group by o.orderDate
			// 		order by sum(p.price*o.orderQuantity) asc
			//
			// 		""";
			//
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			//
			// 	List<Object[]> list = q.getResultList();
			// 	for(Object[] obj : list){
			// 		System.out.println(obj);
			// 	}
			// }
			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
