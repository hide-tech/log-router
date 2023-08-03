package com.yazukov.logrouter.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TravelLog {
    private Long id;
    private LocalDate tripDate;
    private String regNumber;
    private String ownerName;
    private String route;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    private BigDecimal odometerStart;
    private BigDecimal odometerEnd;
    private String description;

    public TravelLog(Long id,
                     LocalDate tripDate,
                     String regNumber,
                     String ownerName,
                     String route,
                     BigDecimal odometerStart,
                     BigDecimal odometerEnd,
                     String description) {
        this.id = id;
        this.tripDate = tripDate;
        this.regNumber = regNumber;
        this.ownerName = ownerName;
        this.route = route;
        this.odometerStart = odometerStart;
        this.odometerEnd = odometerEnd;
        this.description = description;
    }

    public TravelLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
