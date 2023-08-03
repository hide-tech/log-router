package com.yazukov.logrouter.service;

import com.yazukov.logrouter.dto.TravelLogDto;
import com.yazukov.logrouter.mapper.TravelLogMapper;
import com.yazukov.logrouter.model.TravelLog;
import com.yazukov.logrouter.repository.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final TravelLogMapper travelLogMapper;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LogService(LogRepository logRepository, TravelLogMapper travelLogMapper) {
        this.logRepository = logRepository;
        this.travelLogMapper = travelLogMapper;
    }

    public Flux<TravelLogDto> getAllLogs() {
        return logRepository.findAll().map(travelLogMapper::travelLogToTravelLogDto);
    }

    public Mono<Map<LocalDate, List<TravelLogDto>>> getLogReport(String startDate, String endDate, String ownerName, String regNumber) {
        Map<String, String> filters = new HashMap<>();
        processFilters(startDate, endDate, ownerName, regNumber, filters);
        return logRepository.getAllLogsFiltered(filters).map(log -> {
                    return new TravelLogDto(log.getId(), log.getTripDate(), log.getRegNumber(), log.getOwnerName(), log.getRoute(),
                            log.getOdometerStart(), log.getOdometerEnd(), log.getDescription());
                }).collectList().map(el -> el.stream()
                        .peek(elem -> elem.setTotalRange(elem.getOdometerEnd().subtract(elem.getOdometerStart())))
                        .collect(Collectors.toList()))
                .map(el -> el.stream().collect(Collectors.groupingBy(TravelLogDto::getTripDate)));
    }

    private static void processFilters(String startDate, String endDate, String ownerName, String regNumber, Map<String, String> filters) {
        if (startDate != null) {
            if (endDate != null) {
                filters.put("startDate", startDate);
                filters.put("endDate", endDate);
            } else {
                filters.put("startDate", startDate);
                filters.put("endDate", LocalDateTime.now().format(formatter));
            }
        } else {
            if (endDate != null) {
                filters.put("startDate", endDate);
                filters.put("endDate", endDate);
            } else {
                filters.put("startDate", LocalDateTime.of(1980, 1, 1, 0, 0).format(formatter));
                filters.put("endDate", LocalDateTime.now().format(formatter));
            }
        }
        if (ownerName != null) {
            filters.put("ownerName", ownerName);
        } else {
            filters.put("ownerName", "");
        }
        if (regNumber != null) {
            filters.put("regNumber", regNumber);
        } else {
            filters.put("regNumber", "");
        }
    }

    @Transactional(value = "transactionManager")
    public Mono<TravelLogDto> createLog(TravelLogDto travelLogDto) {
        return logRepository.save(travelLogMapper.travelLogDtoToTravelLog(travelLogDto))
                .map(travelLogMapper::travelLogToTravelLogDto);
    }

    @Transactional(value = "transactionManager")
    public Mono<TravelLogDto> updateLog(Long logId, TravelLogDto travelLogDto) {
        return logRepository.findById(logId).switchIfEmpty(Mono.error(new RuntimeException("Log with id not found")))
                .map(el -> {
                    return new TravelLog(el.getId(), travelLogDto.getTripDate(), travelLogDto.getRegNumber(),
                            travelLogDto.getOwnerName(), travelLogDto.getRoute(), travelLogDto.getOdometerStart(),
                            travelLogDto.getOdometerEnd(), travelLogDto.getDescription());
                }).flatMap(logRepository::save).map(travelLogMapper::travelLogToTravelLogDto);
    }

    public void deleteLog(Long logId) {
        logRepository.delete(logId);
    }
}
