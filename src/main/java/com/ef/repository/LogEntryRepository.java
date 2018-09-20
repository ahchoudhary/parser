package com.ef.repository;

import com.ef.entity.LogEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ahtesham on 9/18/18.
 */
public interface LogEntryRepository extends CrudRepository<LogEntry, Long> {
    @Query("select le from LogEntry le where le.entryDateTime > ?1 and le.entryDateTime < ?2 and le.httpStatus > 399 and le.httpStatus < 500")
    List<LogEntry> findBlockedIp(Timestamp startDate, Timestamp endDate);

}
