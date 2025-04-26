package com.example.demo.repository;

import com.example.demo.model.useraudit.UserAuditOutbox;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboxUserAuditRepository extends CassandraRepository<UserAuditOutbox, UUID> {

}
