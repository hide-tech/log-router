package com.yazukov.logrouter.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LogReport {
    private List<TravelLogDto> logs = new ArrayList<>();
    private BigDecimal totalDistance;

    public LogReport() {
    }

    public LogReport(List<TravelLogDto> logs) {
        this.logs = logs;
    }

    public List<TravelLogDto> getLogs() {
        return logs;
    }

    public void setLogs(List<TravelLogDto> logs) {
        this.logs = logs;
    }

    public BigDecimal getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(BigDecimal totalDistance) {
        this.totalDistance = totalDistance;
    }
}
