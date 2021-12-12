package com.pretendco.ar.repository;

import com.pretendco.ar.entity.ErrorLog;
import com.pretendco.ar.entity.ErrorLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, ErrorLogId> {
    @Query(value="select * from error_log el where el.serial_number=:SerialNumber", nativeQuery = true)
    List<ErrorLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value = "select * from error_log el where el.serial_number=:SerialNumber and el.sessionid=:SessionID and el.event_time=:EventTime", nativeQuery = true)
    ErrorLog findByPK(@Param("SerialNumber") String serialNumber,
                      @Param("SessionID") String sessionID,
                      @Param("EventTime") String eventTime);

//    List<ErrorLog> findBySerialNumber(String serialNumber);
//    ErrorLog findBySerialNumberAndSessionIDAndEventTime(String serialNumber,
//                                                        String sessionID,
//                                                        String eventTime);
}
