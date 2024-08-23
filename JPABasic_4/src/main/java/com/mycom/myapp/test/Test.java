package com.mycom.myapp.test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mycom.myapp.test.config.MyPersistenceUnitInfo;
import com.mycom.myapp.test.entity.Concert;
import com.mycom.myapp.test.entity.Customer;
import com.mycom.myapp.test.entity.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

// JPQL
// insert (X), select, update, delete
public class Test {

	public static void main(String[] args) {

		Map<String, String> props = new HashMap<>();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create"); // create (항상 새로 만든다), update ( 없으면 만들고, 있으면 변경되어야 하면 변경 )

		EntityManagerFactory emf = new HibernatePersistenceProvider()
			.createContainerEntityManagerFactory(new MyPersistenceUnitInfo(), props);
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			// #1 테이블 생성, 테스트 용도 entity 데이터 등록
			{
				//콘서트
				Concert concert1 = new Concert();
				concert1.setTitle("스테이씨 콘서트");
				concert1.setPrice(99000);
				concert1.setConcertDate(LocalDate.of(2024, 8, 31));

				Concert concert2 = new Concert();
				concert2.setTitle("보이넥스트도어 콘서트");
				concert2.setPrice(130000);
				concert2.setConcertDate(LocalDate.of(2024, 9, 14));

				Concert concert3 = new Concert();
				concert3.setTitle("김필 콘서트");
				concert3.setPrice(110000);
				concert3.setConcertDate(LocalDate.of(2024, 10, 2));

				Customer customer1 = new Customer();
				customer1.setName("홍길동");
				customer1.setPhone("010-1111-1111");

				Customer customer2 = new Customer();
				customer2.setName("이길동");
				customer2.setPhone("010-2222-2222");

				Customer customer3 = new Customer();
				customer3.setName("삼길동");
				customer3.setPhone("010-3333-3333");

				Customer customer4 = new Customer();
				customer4.setName("사길동");
				customer4.setPhone("010-4444-4444");

				Customer customer5 = new Customer();
				customer5.setName("오길동");
				customer5.setPhone("010-5555-5555");

				Customer customer6 = new Customer();
				customer6.setName("육길동");
				customer6.setPhone("010-6666-6666");

				Customer customer7 = new Customer();
				customer7.setName("칠길동");
				customer7.setPhone("010-7777-7777");

				Customer customer8 = new Customer();
				customer8.setName("팔길동");
				customer8.setPhone("010-8888-8888");

				Reservation reservation1 = new Reservation();
				reservation1.setConcert(concert1);
				reservation1.setCustomer(customer1);
				reservation1.setSeat("a2");
				reservation1.setReservationDate(LocalDate.now());

				Reservation reservation2 = new Reservation();
				reservation2.setConcert(concert2);
				reservation2.setCustomer(customer2);
				reservation2.setSeat("a2");
				reservation2.setReservationDate(LocalDate.now());

				Reservation reservation3 = new Reservation();
				reservation3.setConcert(concert3);
				reservation3.setCustomer(customer3);
				reservation3.setSeat("g3");
				reservation3.setReservationDate(LocalDate.now());

				Reservation reservation4 = new Reservation();
				reservation4.setConcert(concert2);
				reservation4.setCustomer(customer1);
				reservation4.setSeat("b11");
				reservation4.setReservationDate(LocalDate.now());

				Reservation reservation5 = new Reservation();
				reservation5.setConcert(concert1);
				reservation5.setCustomer(customer8);
				reservation5.setSeat("h8");
				reservation5.setReservationDate(LocalDate.now());

				Reservation reservation6 = new Reservation();
				reservation6.setConcert(concert1);
				reservation6.setCustomer(customer6);
				reservation6.setSeat("a8");
				reservation6.setReservationDate(LocalDate.now());

				Reservation reservation7 = new Reservation();
				reservation7.setConcert(concert3);
				reservation7.setCustomer(customer1);
				reservation7.setSeat("c2");
				reservation7.setReservationDate(LocalDate.now());

				Reservation reservation8 = new Reservation();
				reservation8.setConcert(concert2);
				reservation8.setCustomer(customer4);
				reservation8.setSeat("d2");
				reservation8.setReservationDate(LocalDate.now());

				em.persist(concert1);
				em.persist(concert2);
				em.persist(concert3);


				em.persist(customer1);
				em.persist(customer2);
				em.persist(customer3);
				em.persist(customer4);
				em.persist(customer5);
				em.persist(customer6);
				em.persist(customer7);
				em.persist(customer8);

				em.persist(reservation1);
				em.persist(reservation2);
				em.persist(reservation3);
				em.persist(reservation4);
				em.persist(reservation5);
				em.persist(reservation6);
				em.persist(reservation7);
				em.persist(reservation8);

			}

			//1) 모든 예약 정보를 조회하세요
				{
					String sql = "select c from Reservation c";
					List<Reservation> list = em.createQuery(sql, Reservation.class).getResultList();
					for(Reservation c : list) {
						System.out.println(c);
					}
				}

			 //2) 전화번호가 010-5555-5555인 고객을 조회하세요
			{
				String sql = "select c from Customer c where c.phone = '010-5555-5555'";
				List<Customer> list = em.createQuery(sql, Customer.class).getResultList();
				for(Customer c : list) {
					System.out.println(c);
				}
			}
			 //3) 가격이 110,000원 이상인 콘서트 정보를 조회하세요

			{
				String sql = "select c from Concert c where c.price >=110000";
				List<Concert> list = em.createQuery(sql, Concert.class).getResultList();
				for(Concert c : list) {
					System.out.println(c);
				}
			}
			 //4) "보이넥스트도어 콘서트"의 총 수익을 조회하세요
			{
				String sql = "select c.title , sum(c.price) from Concert c left join c.reservation s where c.title = '보이넥스트도어 콘서트' group by c.title";
				List<Object[]> list = em.createQuery(sql, Object[].class).getResultList();
				for(Object[] c : list) {
					System.out.println(c[0] + " : " + c[1]);
				}
			}

			 //5) "스테이씨 콘서트" 예매 횟수를 조회하세요

			{
				String sql = "select c.title , count(c.title) from Concert c left join c.reservation s where c.title = '스테이씨 콘서트' group by c.title";
				List<Object[]> list = em.createQuery(sql, Object[].class).getResultList();
				for(Object[] c : list) {
					System.out.println(c[0] + " : " + c[1]);
				}
			}





			//			{
			//				//1) 모든 예약 정보를 조회하세요
			//				String sql = "select r from Reservation r";
			//				List<Reservation> list = em.createQuery(sql, Reservation.class).getResultList();
			//				for (Reservation r : list) {
			//					System.out.println(r);
			//				}
			//			}

			//			{
			//				//2) 전화번호가 010-5555-5555인 고객을 조회하세요
			//				String sql = "select c from Customer c where c.phone = '010-5555-5555'";
			//				List<Customer> list = em.createQuery(sql, Customer.class).getResultList();
			//				for (Customer c : list) {
			//					System.out.println(c);
			//				}
			//			}

			//			{
			//				//3) 가격이 110,000원 이상인 콘서트 정보를 조회하세요
			//				String sql = "select c from Concert c where c.price >= 110000";
			//				List<Concert> list = em.createQuery(sql, Concert.class).getResultList();
			//				for (Concert c : list) {
			//					System.out.println(c);
			//				}
			//			}

			//			{
			//				//4) "보이넥스트도어 콘서트"의 총 수익을 조회하세요
			//				String sql = "select sum(c.price) from Reservation r join r.concert c where c.title = '보이넥스트도어 콘서트'";
			//				List<Object[]> list = em.createQuery(sql, Object[].class).getResultList();
			//				for (Object[] o : list) {
			//					System.out.println(o[0]);
			//				}
			//			}

			//			{
			//				//5) "스테이씨 콘서트" 예매 횟수를 조회하세요
			//				String sql = "select count(r) from Reservation r join r.concert c where c.title = '스테이씨 콘서트'";
			//				List<Object[]> list = em.createQuery(sql, Object[].class).getResultList();
			//				for (Object[] o : list) {
			//					System.out.println(o[0]);
			//				}
			//			}

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}