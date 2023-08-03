package com.yazukov.logrouter.controller;

import com.yazukov.logrouter.dto.LogReport;
import com.yazukov.logrouter.dto.TravelLogDto;
import com.yazukov.logrouter.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("router/v1/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public Flux<TravelLogDto> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/report")
    public Mono<LogReport> logReport(@RequestParam(value = "startDate", required = false) String startDate,
                                     @RequestParam(value = "endDate", required = false) String endDate,
                                     @RequestParam(value = "ownerName", required = false) String ownerName,
                                     @RequestParam(value = "regNumber", required = false) String regNumber) {
        return logService.getLogReport(startDate, endDate, ownerName, regNumber);
    }

    @PostMapping
    public Mono<TravelLogDto> createNewLog(@RequestBody TravelLogDto travelLogDto) {
        return logService.createLog(travelLogDto);
    }

    @PutMapping("/{id}")
    public Mono<TravelLogDto> updateLog(@PathVariable("id") Long logId, @RequestBody TravelLogDto travelLogDto) {
        return logService.updateLog(logId, travelLogDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable("id") Long logId) {
        logService.deleteLog(logId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
