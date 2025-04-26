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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(value = "user_audit")
public class UserAudit {

  @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
  private UUID userId;

  @PrimaryKeyColumn(name = "event_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
  private Timestamp eventTime;

  @Column(value = "event_details")
  private String eventDetails;

  @Column(value = "event_type")
  private String eventType;

  public UserAuditOutbox toOutbox() {
    UserAuditOutbox userAuditOutbox = new UserAuditOutbox();
    userAuditOutbox.setUserId(userId);
    userAuditOutbox.setEventTime(eventTime);
    userAuditOutbox.setEventDetails(eventDetails);
    userAuditOutbox.setEventType(eventType);
    return userAuditOutbox;
  }
}
