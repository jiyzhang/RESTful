package com.pretendco.ar.repository;

import com.pretendco.ar.entity.AppConfigChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppConfigChangeLogRepository extends JpaRepository<AppConfigChangeLog, Long>, JpaSpecificationExecutor<AppConfigChangeLog> {
    @Query(value="select * from app_config_change_log accl where accl.serial_number=:SerialNumber", nativeQuery = true)
    List<AppConfigChangeLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value="select * from app_config_change_log accl where accl.serial_number=:SerialNumber and accl.sessionid=:SessionID", nativeQuery = true)
    List<AppConfigChangeLog> findBySerialNumberAndSessionID(
            @Param("SerialNumber") String serialNumber,
            @Param("SessionID")    String sessionID);
}
