package com.yazukov.logrouter.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class LogReport {
    private Map<LocalDate, List<TravelLogDto>> logs;
    private Double totalDistance;

    public Map<LocalDate, List<TravelLogDto>> getLogs() {
        return logs;
    }

    public void setLogs(Map<LocalDate, List<TravelLogDto>> logs) {
        this.logs = logs;
    }

    public LogReport() {
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
