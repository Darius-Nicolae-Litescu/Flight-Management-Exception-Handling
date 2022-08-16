package com.controller;

import com.dto.request.insert.FlightInsertDTO;
import com.dto.request.update.FlightUpdateDTO;
import com.dto.response.FlightResponseDTO;
import com.entity.Flight;
import com.exception.EntityNotFoundException;
import com.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/abcflights")
public class FlightController {

	private final FlightService flightService;

	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@PostMapping("/flight")
	public ResponseEntity<FlightResponseDTO> saveFlight(@RequestBody @Valid FlightInsertDTO flightInsertDTO) {
		FlightResponseDTO flightResponseDTO = flightService.addFlight(flightInsertDTO);
		if (flightResponseDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(flightResponseDTO, HttpStatus.CREATED);
	}


	@PutMapping("/flight")
	public ResponseEntity<FlightResponseDTO> updateFlight(@RequestBody @Valid FlightUpdateDTO flightUpdateDTO) throws EntityNotFoundException {
		FlightResponseDTO flightResponseDTO = flightService.updateFlight(flightUpdateDTO);
		if (flightResponseDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(flightResponseDTO, HttpStatus.CREATED);
	}


	@GetMapping("/flight/{flightId}")
	public Flight getFlightBasedOnId(@PathVariable Long flightId) {
		Optional<Flight> flightOptional = flightService.getFlightById(flightId);
		return flightOptional.orElse(null);
	}


	@GetMapping("/flights")
	public List<Flight> getAllFlights() {
		return flightService.getAllFlight();
	}

	@GetMapping("/flights/name")
	public List<FlightResponseDTO> getFlightsByFlightName(@RequestParam(value = "flightName") String flightName) {
		return flightService.getFlightsByFlightName(flightName);
	}

	@GetMapping("/flights/city1")
	public List<FlightResponseDTO> getFlightsByCity1(@RequestParam(value = "city1") String city1) {
		return flightService.getFlightsByCity1(city1);
	}

	@GetMapping("/flights/city2")
	public List<FlightResponseDTO> getFlightsByCity2(@RequestParam(value = "city2") String city2) {
		return flightService.getFlightsByCity2(city2);
	}

	@GetMapping("/flights/flightType")
	public List<FlightResponseDTO> getFlightsByFlightType(@RequestParam(value = "flightType") String flightType) {
		return flightService.getFlightsByFlightType(flightType);
	}

	@GetMapping("/flights/all")
	public List<FlightResponseDTO> getFlightsByFlightNameAndCity1AndCity2AndFlightType(
			@RequestParam(value = "flightName") String flightName,
			@RequestParam(value = "city1") String city1,
			@RequestParam(value = "city2") String city2,
			@RequestParam(value = "flightType") String flightType) {
		return flightService.getFlightsByFlightNameAndCity1AndCity2AndFlightType(flightName, city1, city2, flightType);
	}

	@GetMapping("/flights/generic-error-message")
	public void getGenericErrorMessage() {
		Flight flight = null;
		flight.getSeats();
	}

}


