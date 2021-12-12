package com.pretendco.ar.repository;

import com.pretendco.ar.entity.SelectionLog;
import com.pretendco.ar.entity.SelectionLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SelectionLogRepository extends JpaRepository<SelectionLog, SelectionLogId> {
    @Query(value="select * from selection_log el where el.serial_number=:SerialNumber", nativeQuery = true)
    List<SelectionLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value="select * from selection_log el where el.serial_number=:SerialNumber and el.sessionid=:SessionID", nativeQuery = true)
    SelectionLog findByPK(@Param("SerialNumber") String serialNumber,
                          @Param("SessionID") String sessionID);

//    List<SelectionLog> findBySerialNumber(String serialNumber);
//    SelectionLog findBySerialNumberAndSessionID(String serialNumber,
//                                                String sessionID);
}