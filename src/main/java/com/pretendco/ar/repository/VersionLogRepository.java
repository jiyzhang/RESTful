package com.pretendco.ar.repository;

import com.pretendco.ar.entity.VersionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VersionLogRepository extends JpaRepository<VersionLog, Long>, JpaSpecificationExecutor<VersionLog> {
    @Query(value="select * from version_log vl where vl.serial_number=:SerialNumber", nativeQuery = true)
    List<VersionLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);
//
//    @Query(value="select * from version_log vl where vl.serial_number=:SerialNumber and vl.log_time=:SessionID", nativeQuery = true)
//    List<VersionLog> findBySerialNumberAndLogTime(
//            @Param("SerialNumber") String serialNumber,
//            @Param("SessionID")    String sessionID);
}
