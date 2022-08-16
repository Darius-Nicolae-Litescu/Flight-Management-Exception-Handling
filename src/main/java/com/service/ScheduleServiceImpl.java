package com.service;

import com.dto.request.insert.ScheduleInsertDTO;
import com.dto.request.update.ScheduleUpdateDTO;
import com.dto.response.ScheduleResponseDTO;
import com.entity.Flight;
import com.entity.Schedule;
import com.exception.EntityNotFoundException;
import com.repository.FlightRepository;
import com.util.ScheduleDTOConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final FlightRepository flightRepository;

	private ScheduleDTOConvertor scheduleDTOConvertor;

	@Autowired
	public ScheduleServiceImpl(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Autowired
	public void setScheduleDTOConvertor(ScheduleDTOConvertor scheduleDTOConvertor) {
		this.scheduleDTOConvertor = scheduleDTOConvertor;
	}

	@Override
	public ScheduleResponseDTO addSchedule(Long flightId, ScheduleInsertDTO scheduleInsertDTO) {
		Optional<Flight> flightOptional = flightRepository.findById(flightId);
		if (flightOptional.isPresent()) {
			Flight flight = flightOptional.get();
			Schedule schedule = scheduleDTOConvertor.scheduleInsertDTOToSchedule(scheduleInsertDTO);
			flight.setFlightSchedule(schedule);
			flightRepository.save(flight);
			ScheduleResponseDTO scheduleResponseDTO = scheduleDTOConvertor
					.convertScheduleToScheduleResponseDTO(flight.getFlightSchedule(), flightId);
			return scheduleResponseDTO;
		}
		return null;
	}

	@Override
	public ScheduleResponseDTO updateSchedule(Long flightId, ScheduleUpdateDTO scheduleUpdateDTO) throws EntityNotFoundException {
		Optional<Flight> flightOptional = flightRepository.findById(flightId);
		if (flightOptional.isPresent()) {
			Flight flight = flightOptional.get();
			Schedule schedule = scheduleDTOConvertor.scheduleUpdateDTOToSchedule(scheduleUpdateDTO);
			Schedule scheduleToUpdate = flight.getFlightSchedule();
			scheduleToUpdate.setDepartureTime(schedule.getDepartureTime());
			scheduleToUpdate.setLandingTime(schedule.getLandingTime());
			scheduleToUpdate.setStop(schedule.getStop());

			flightRepository.save(flight);

			ScheduleResponseDTO scheduleResponseDTO = scheduleDTOConvertor.convertScheduleToScheduleResponseDTO(scheduleToUpdate, flightId);
			return scheduleResponseDTO;
		} else {
			throw new EntityNotFoundException(scheduleUpdateDTO.getId());
		}
	}


}
