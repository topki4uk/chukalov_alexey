package com.example.demo.repository;

import com.example.demo.model.useraudit.UserAudit;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface UserAuditsRepository extends CassandraRepository<UserAudit, UUID> {

  List<UserAudit> getByUserId(UUID id);
}
