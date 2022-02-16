package com.pretendco.ar.repository;

import com.pretendco.ar.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long>, JpaSpecificationExecutor<ActivityLog> {
    @Query(value="select * from activity_log al where al.serial_number=:SerialNumber", nativeQuery = true)
    List<ActivityLog> findBySerialNumber(@Param("SerialNumber") String serialNumber);

    @Query(value="select * from activity_log al where al.serial_number=:SerialNumber and sessionid=:SessionID", nativeQuery = true)
    List<ActivityLog> findBySerialNumberAndSessionID(
            @Param("SerialNumber") String serialNumber,
            @Param("SessionID")    String sessionID);

    @Query(value="select * from activity_log al where al.sessionid=:SessionID", nativeQuery = true)
    List<ActivityLog> findBySessionID(
            @Param("SessionID")    String sessionID);

    @Modifying
    @Query(value="update activity_log al set al.session_termination_time =:SessionTerminationTime where al.sessionid=:SessionID", nativeQuery = true)
    int updateSessonTerminationTime(
            @Param("SessionID")    String sessionID,
            @Param("SessionTerminationTime") String sessionTerminationTime);


}