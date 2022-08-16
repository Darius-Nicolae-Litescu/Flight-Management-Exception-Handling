package com.repository;

import com.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFlightName(String flightName);

    List<Flight> findByCity1(String city1);

    List<Flight> findByCity2(String city2);

    List<Flight> findByFlightType(String flightType);

    List<Flight> findByFlightNameAndCity1AndCity2AndFlightType(String flightName, String city1, String city2, String flightType);
}
