package com.mycom.myapp.test.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	// 예약 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// 예약 일시
	@Column(name = "reservation_date")
	private LocalDate ReservationDate;
	// 예약 좌석
	private String seat;

	// 콘서트 정보
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Concert concert;

	// 예약자 정보
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Customer customer;

	public Reservation() {
	}

	public Reservation(int id, LocalDate reservationDate, String seat, Concert concert, Customer customer) {
		super();
		this.id = id;
		ReservationDate = reservationDate;
		this.seat = seat;
		this.concert = concert;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getReservationDate() {
		return ReservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		ReservationDate = reservationDate;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Concert getConcert() {
		return concert;
	}

	public void setConcert(Concert concert) {
		this.concert = concert;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", ReservationDate=" + ReservationDate + ", seat=" + seat + "]";
	}

}