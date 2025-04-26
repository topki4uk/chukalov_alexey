package com.example.demo.model.useraudit;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(value = "outbox")
public class UserAuditOutbox {

  @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private UUID userId;

  @PrimaryKeyColumn(name = "event_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
  private Timestamp eventTime;

  @Column(value = "event_details")
  private String eventDetails;

  @Column(value = "event_type")
  private String eventType;

  public UserAudit toUserAudit() {
    UserAudit userAudit = new UserAudit();
    userAudit.setUserId(userId);
    userAudit.setEventTime(eventTime);
    userAudit.setEventDetails(eventDetails);
    userAudit.setEventType(eventType);
    return userAudit;
  }
}
