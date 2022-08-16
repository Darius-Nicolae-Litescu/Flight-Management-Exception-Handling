package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seatId;
	@Column(name = "passenger_name")
	private String passengerName;
	private String gender;
	private int age;
	@Column(name = "mobile_number")
	private long mobileNumber;

}
