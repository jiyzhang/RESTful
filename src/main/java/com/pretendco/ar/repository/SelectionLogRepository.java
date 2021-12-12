package com.pretendco.ar.repository;

import com.pretendco.ar.entity.SelectionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SelectionLogRepository extends JpaRepository<SelectionLog, Long>, JpaSpecificationExecutor<SelectionLog> {
    @Query(value="select * from selection_log sl where sl.serial_number=:SerialNumber", nativeQuery = true)
    List<SelectionLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value="select * from selection_log sl where sl.serial_number=:SerialNumber and sessionid=:SessionID", nativeQuery = true)
    List<SelectionLog> findBySerialNumberAndSessionID(
            @Param("SerialNumber") String serialNumber,
            @Param("SessionID")    String sessionID);
}
