package com.mycom.myapp.test.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Concert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private long price;
	@Column(name = "concert_date")
	private LocalDate ConcertDate;

	@OneToMany(mappedBy = "concert")
	private List<Reservation> reservation;

	public Concert() {
	}

	public Concert(int id, String title, long price, LocalDate concertDate, List<Reservation> reservation) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		ConcertDate = concertDate;
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public LocalDate getConcertDate() {
		return ConcertDate;
	}

	public void setConcertDate(LocalDate concertDate) {
		ConcertDate = concertDate;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Concert [id=" + id + ", title=" + title + ", price=" + price + ", ConcertDate=" + ConcertDate + "]";
	}

}