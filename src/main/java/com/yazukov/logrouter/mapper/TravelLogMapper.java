package com.yazukov.logrouter.mapper;

import com.yazukov.logrouter.dto.TravelLogDto;
import com.yazukov.logrouter.model.TravelLog;
import org.springframework.stereotype.Component;

@Component
public class TravelLogMapper {

    public TravelLog travelLogDtoToTravelLog(TravelLogDto travelLogDto) {
        return new TravelLog(
                travelLogDto.getId(),
                travelLogDto.getTripDate(),
                travelLogDto.getRegNumber(),
                travelLogDto.getOwnerName(),
                travelLogDto.getRoute(),
                travelLogDto.getOdometerStart(),
                travelLogDto.getOdometerEnd(),
                travelLogDto.getDescription()
        );
    }

    public TravelLogDto travelLogToTravelLogDto(TravelLog travelLog) {
        return new TravelLogDto(
                travelLog.getId(),
                travelLog.getTripDate(),
                travelLog.getRegNumber(),
                travelLog.getOwnerName(),
                travelLog.getRoute(),
                travelLog.getOdometerStart(),
                travelLog.getOdometerEnd(),
                travelLog.getDescription()
        );
    }
}
