package com.yazukov.logrouter.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TravelLogDto {
    private Long id;
    private LocalDate tripDate;
    private String regNumber;
    private String ownerName;
    private String route;
    private BigDecimal odometerStart;
    private BigDecimal odometerEnd;
    private String description;
    private BigDecimal totalRange;
    private BigDecimal resultRange;

    public BigDecimal getResultRange() {
        return resultRange;
    }

    public void setResultRange(BigDecimal resultRange) {
        this.resultRange = resultRange;
    }

    public BigDecimal getTotalRange() {
        return totalRange;
    }

    public void setTotalRange(BigDecimal totalRange) {
        this.totalRange = totalRange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public TravelLogDto(Long id, LocalDate tripDate, String regNumber, String ownerName, String route, BigDecimal odometerStart, BigDecimal odometerEnd, String description) {
        this.tripDate = tripDate;
        this.regNumber = regNumber;
        this.ownerName = ownerName;
        this.route = route;
        this.odometerStart = odometerStart;
        this.odometerEnd = odometerEnd;
        this.description = description;
        this.id = id;
    }

    public TravelLogDto() {
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getOdometerStart() {
        return odometerStart;
    }

    public void setOdometerStart(BigDecimal odometerStart) {
        this.odometerStart = odometerStart;
    }

    public BigDecimal getOdometerEnd() {
        return odometerEnd;
    }

    public void setOdometerEnd(BigDecimal odometerEnd) {
        this.odometerEnd = odometerEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
