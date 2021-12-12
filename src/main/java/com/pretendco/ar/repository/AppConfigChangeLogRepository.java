package com.pretendco.ar.repository;

import com.pretendco.ar.entity.AppConfigChangeLog;
import com.pretendco.ar.entity.AppConfigChangeLogId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppConfigChangeLogRepository extends JpaRepository<AppConfigChangeLog, AppConfigChangeLogId> {
    @Query(value="select * from app_config_change_log accl where accl.serial_number=:SerialNumber", nativeQuery = true)
    List<AppConfigChangeLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value="select * from app_config_change_log accl where accl.serial_number=:SerialNumber and accl.sessionid=:SessionID and accl.parameter_change_time=:ParameterChangeTime", nativeQuery = true)
    AppConfigChangeLog findByPK(@Param("SerialNumber") String serialNumber,
                                @Param("SessionID") String sessionID,
                                @Param("ParameterChangeTime") String parameterChangeTime);

//    List<AppConfigChangeLog> findBySerialNumber(String serialNumber);
//    AppConfigChangeLog findBySerialNumberAndSessionIDAndParameterChangeTime(
//            String serialNumber,
//            String sessionID,
//            String parameterChangeTime
//    );


}
