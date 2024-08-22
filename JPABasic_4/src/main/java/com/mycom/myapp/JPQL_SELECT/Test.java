package com.mycom.myapp.JPQL_SELECT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.JPQL_SELECT.config.MyPersistenceUnitInfo;
import com.mycom.myapp.JPQL_SELECT.entity.Product;

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
			// 테스트 엔티티
			// {
			// 	Product p1 = new Product();
			// 	p1.setName("galaxy");
			// 	p1.setPrice(1000L);
			// 	p1.setQuantity(15);
			// 	p1.setCountry("korea");
			//
			// 	Product p2 = new Product();
			// 	p2.setName("iphone");
			// 	p2.setPrice(3000L);
			// 	p2.setQuantity(10);
			// 	p2.setCountry("usa");
			//
			// 	Product p3 = new Product();
			// 	p3.setName("galaxy note");
			// 	p3.setPrice(5000L);
			// 	p3.setQuantity(20);
			// 	p3.setCountry("korea");
			//
			// 	em.persist(p1);
			// 	em.persist(p2);
			// 	em.persist(p3);
			// }


			// // #2 Query
			// // SQL : select * from Product
			// // JPQL : select p from Product p : 모든 Prudct Entity를 가져온다. p: p entity의 모든 필드
			// {
			// 	String jpql = "select p from Product p";
			// 	Query q = em.createQuery(jpql);
			// 	List<?> productList=  q.getResultList();
			//
			// 	for(Object object : productList) {
			// 		Product product = (Product)object;
			// 		System.out.println(product);
			// 	}
			// }

			// #3 TypedQuery
			// {
			// 	String jpql = "select p from Product p";
			// 	TypedQuery<Product> q = em.createQuery(jpql,Product.class);
			// 	List<Product> productList=  q.getResultList();
			//
			// 	for(Product product : productList) {
			// 		System.out.println(product);
			// 	}
			// }

			// #4 TypedQuery + 개별 컬럼
			// #3 번 방식으로 Product 타입으로 입력 받으면 아래 오류가 발생
			// Result type is 'Product' but the query returned a 'Integer'
			// {
			// 	String jpql = "select p.id, p.name, p.price from Product p";
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			// 	List<Object[]> productList=  q.getResultList();
			//
			// 	for(Object[] product : productList) {
			// 		System.out.println(product[0] + " " + product[1] + " " + product[2]);
			// 	}
			// }

			// #5 TypedQuery + where
			// {
			// 	String jpql = "select p from Product p where p.price > 2000";
			// 	TypedQuery<Product> q = em.createQuery(jpql,Product.class);
			// 	List<Product> productList=  q.getResultList();
			//
			// 	for(Product product : productList) {
			// 		System.out.println(product);
			// 	}
			// }

			//# 6
			// {
			// 	String jpql = "select p from Product p where p.price > :price and p.quantity > :quantity";
			// 	TypedQuery<Product> q = em.createQuery(jpql,Product.class);
			// 	q.setParameter("price", 2000);
			// 	q.setParameter("quantity", 10);
			// 	List<Product> productList=  q.getResultList();
			//
			// 	for(Product product : productList) {
			// 		System.out.println(product);
			// 	}
			// }

			//# 7

			// {
			// 	String jpql = "select p from Product p where p.price > ?1 and p.quantity > ?2";
			// 	TypedQuery<Product> q = em.createQuery(jpql,Product.class);
			// 	q.setParameter(1, 2000);
			// 	q.setParameter(2, 10);
			// 	List<Product> productList=  q.getResultList();
			//
			// 	for(Product product : productList) {
			// 		System.out.println(product);
			// 	}
			// }


			//# 8 TypedQuery + where  + param + like

			// {
			// 	String jpql = "select p from Product p where p.name like :name";
			// 	TypedQuery<Product> q = em.createQuery(jpql,Product.class);
			// 	q.setParameter("name", "ga%");
			// 	q.setParameter("name", "%o%");
			// 	q.setParameter("name", "%note%");
			// 	List<Product> productList=  q.getResultList();
			//
			// 	for(Product product : productList) {
			// 		System.out.println(product);
			// 	}
			// }

			// // #9 TypedQuery + aggregation function - count() + getSingleResult()
			// //Long return
			// {
			// 	String jpql = "select count(p) from Product p ";
			// 	TypedQuery<Long> q = em.createQuery(jpql,Long.class);
			// 	Long count = q.getSingleResult();
			// 	System.out.println(count);
			// }

			// #10 TypedQuery + aggregation function - count() + getSingleResult()
			//Double return
			// {
			// 	String jpql = "select avg(p.price) from Product p ";
			// 	TypedQuery<Double> q = em.createQuery(jpql,Double.class);
			// 	Double avg = q.getSingleResult();
			// 	System.out.println(avg);
			// }

			// #11 TypedQuery + aggregation function - count() + getSingleResult()
			//Double return
			// {
			// 	String jpql = "select sum(p.quantity), min(p.quantity), max(p.quantity) from Product p ";
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			// 	Object[] result = q.getSingleResult();
			// 	System.out.println(result[0] + " " + result[1] + " " + result[2]);
			// }

			// #12 TypedQuery + aggregation function - count() + getSingleResult()
			//List return
			// {
			// 	String jpql = "select p.country ,sum(p.quantity), min(p.quantity), max(p.quantity) from Product p group by p.country";
			// 	TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
			// 	List<Object[]> result = q.getResultList();
			// 	for(Object[] row : result) {
			// 		System.out.println(row[0] + " " + row[1] + " " + row[2]+ " " + row[3]);
			// 	}
			// }

			// #13 Update,Delete
			// Query,executeUpdate()
			{
				//update
				//String jpql = "update Product p set p.name ='kProduct' where p.country = 'korea'";
				//delete
				String jpql = "delete Product p where p.country = 'korea'";
				Query q = em.createQuery(jpql);
				int result = q.executeUpdate();
				System.out.println(result);
			}


			em.getTransaction().commit();
		}finally {
			em.close();
		}
	}

}
