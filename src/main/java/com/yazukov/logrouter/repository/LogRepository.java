package com.yazukov.logrouter.repository;

import com.yazukov.logrouter.model.TravelLog;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Repository
public class LogRepository {

    private final DatabaseClient databaseClient;

    public LogRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<TravelLog> findAll() {
        String query = "select * from travel_logs";
        return databaseClient.sql(query)
                .map((row, data) -> new TravelLog(
                        row.get("id", Long.class),
                        row.get("trip_date", LocalDate.class),
                        row.get("reg_number", String.class),
                        row.get("owner_name", String.class),
                        row.get("route", String.class),
                        row.get("odometer_start", BigDecimal.class),
                        row.get("odometer_end", BigDecimal.class),
                        row.get("description", String.class)
                )).all();
    }

    public Mono<TravelLog> findById(Long logId) {
        String query = "select * from travel_logs where travel_logs.id = :logId";
        return databaseClient.sql(query).bind("logId", logId)
                .map((row, data) -> new TravelLog(
                        row.get("id", Long.class),
                        row.get("trip_date", LocalDate.class),
                        row.get("reg_number", String.class),
                        row.get("owner_name", String.class),
                        row.get("route", String.class),
                        row.get("odometer_start", BigDecimal.class),
                        row.get("odometer_end", BigDecimal.class),
                        row.get("description", String.class)
                )).first();
    }

    public Flux<TravelLog> getAllLogsFiltered(Map<String, String> filters) {
        String query = "select * from travel_logs where travel_logs.id > 0";
        if (filters.containsKey("startDate")) {
            query += " and travel_logs.trip_date between TO_DATE(:start, 'yyyy-MM-dd') and TO_DATE(:end, 'yyyy-MM-dd')";
        }
        if (filters.containsKey("ownerName")) {
            query += " and travel_logs.owner_name ilike :owner";
        }
        if (filters.containsKey("regNumber")) {
            query += " and travel_logs.reg_number ilike :number";
        }
        query += " order by travel_logs.odometer_start";
        return databaseClient.sql(query)
                .bind("start", filters.get("startDate"))
                .bind("end", filters.get("endDate"))
                .bind("owner", "%" + filters.get("ownerName") + "%")
                .bind("number", "%" + filters.get("regNumber") + "%")
                .map((row, data) -> new TravelLog(
                        row.get("id", Long.class),
                        row.get("trip_date", LocalDate.class),
                        row.get("reg_number", String.class),
                        row.get("owner_name", String.class),
                        row.get("route", String.class),
                        row.get("odometer_start", BigDecimal.class),
                        row.get("odometer_end", BigDecimal.class),
                        row.get("description", String.class)
                )).all();
    }

    @Transactional(value = "transactionManager")
    public Mono<TravelLog> save(TravelLog travelLog) {
        if (travelLog.getId() == null) {
            String query = "insert into travel_logs (trip_date, reg_number, owner_name, route, odometer_start, " +
                    "odometer_end, description) values (:date, :reg, :name, :route, :st, :end, :desc)";
            return databaseClient.sql(query).bind("date", travelLog.getTripDate())
                    .bind("reg", travelLog.getRegNumber())
                    .bind("name", travelLog.getOwnerName())
                    .bind("route", travelLog.getRoute())
                    .bind("st", travelLog.getOdometerStart())
                    .bind("end", travelLog.getOdometerEnd())
                    .bind("desc", travelLog.getDescription())
                    .filter(st -> st.returnGeneratedValues("id"))
                    .map(row -> row.get("id", Long.class)).first()
                    .map(row -> new TravelLog(
                            row,
                            travelLog.getTripDate(),
                            travelLog.getRegNumber(),
                            travelLog.getOwnerName(),
                            travelLog.getRoute(),
                            travelLog.getOdometerStart(),
                            travelLog.getOdometerEnd(),
                            travelLog.getDescription()
                    ));
        } else {
            String query = "insert into travel_logs (id, trip_date, reg_number, owner_name, route, odometer_start, " +
                    "odometer_end, description) values (:id, :date, :reg, :name, :route, :st, :end, :desc)";
            databaseClient.sql(query).bind("id", travelLog.getId())
                    .bind("date", travelLog.getTripDate())
                    .bind("reg", travelLog.getRegNumber())
                    .bind("name", travelLog.getOwnerName())
                    .bind("route", travelLog.getRoute())
                    .bind("st", travelLog.getOdometerStart())
                    .bind("end", travelLog.getOdometerEnd())
                    .bind("desc", travelLog.getDescription()).fetch().rowsUpdated();
            return Mono.just(travelLog);
        }
    }

    @Transactional(value = "transactionManager")
    public void delete(Long logId) {
        String query = "delete from travel_logs l where l.id = ?";
        databaseClient.sql(query).bind(1, logId).map((row, data) -> row.get(1)).first();
    }
}
