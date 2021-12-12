package com.pretendco.ar.repository;

import com.pretendco.ar.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long>, JpaSpecificationExecutor<ErrorLog> {
    @Query(value="select * from error_log el where el.serial_number=:SerialNumber", nativeQuery = true)
    List<ErrorLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value="select * from error_log el where el.serial_number=:SerialNumber and el.sessionid=:SessionID", nativeQuery = true)
    List<ErrorLog> findBySerialNumberAndSessionID(
            @Param("SerialNumber") String serialNumber,
            @Param("SessionID")    String sessionID);
}
