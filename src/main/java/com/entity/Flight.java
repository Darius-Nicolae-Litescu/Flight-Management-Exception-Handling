package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightId;
	@Column(name = "flight_name")
	private String flightName;
	@Column(name = "city_1")
	private String city1;
	@Column(name = "city_2")
	private String city2;
	@Column(name = "flight_type")
	private String flightType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_schedule")
	private Schedule flightSchedule;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_id")
	private List<Seat> seats;

}















