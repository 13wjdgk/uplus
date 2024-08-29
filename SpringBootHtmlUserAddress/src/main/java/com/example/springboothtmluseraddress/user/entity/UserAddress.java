package com.example.springboothtmluseraddress.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_address")
@Getter
@Setter
@ToString
public class UserAddress {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "zip_code")
	private String zipCode;
	private String addr1;
	private String addr2;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	@ToString.Exclude
//	private User user;


	// User와 연관관계가 없을 때
	@Column(name = "user_id")
	private Long userId;

}
