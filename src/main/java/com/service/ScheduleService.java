package com.service;

import com.dto.request.insert.ScheduleInsertDTO;
import com.dto.request.update.ScheduleUpdateDTO;
import com.dto.response.ScheduleResponseDTO;
import com.exception.EntityNotFoundException;

public interface ScheduleService {

	ScheduleResponseDTO addSchedule(Long flightId, ScheduleInsertDTO scheduleInsertDTO);

	ScheduleResponseDTO updateSchedule(Long flightId, ScheduleUpdateDTO scheduleUpdateDTO) throws EntityNotFoundException;

}
